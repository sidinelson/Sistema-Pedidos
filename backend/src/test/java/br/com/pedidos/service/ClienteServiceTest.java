package br.com.pedidos.service;

import br.com.pedidos.dto.ClienteRequest;
import br.com.pedidos.dto.ClienteResponse;
import br.com.pedidos.model.ClienteModel;
import br.com.pedidos.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceTest {

    private static final Integer ID      = 1;
    private static final Integer INDEX   = 0;
    private static final String NOME     = "Maria Souza";
    @Mock
    private ClienteRepository clienteRepository;

    @Autowired
    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    private ClienteModel cliente;
    private ClienteRequest clienteRequest;
    private ClienteResponse clienteResponse;

    @Test
    @DisplayName("whenCreateThenReturnSuccess transação com sucesso OK")
    void whenCreateThenReturnSuccess() throws Exception {
        when(clienteRepository.save(any())).thenReturn(cliente);

        ClienteResponse response = clienteService.save(clienteRequest);

        Assertions.assertNotNull(response);
        assertEquals(ClienteResponse.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());

    }


    @Test
    @DisplayName("whenValidarClienteNome transação com sucesso OK")
    void whenValidarClienteNome() {
        clienteService.validarClienteNome(clienteRequest);
    }


    @Test
    @DisplayName("whenFindAllThenReturnAnListOfCliente transação com sucesso OK")
    void whenFindAllThenReturnAnListOfCliente() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteResponse> response = clienteService.findAll();

        Assertions.assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(ClienteResponse.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
    }



    private void startCliente() {
        cliente = new ClienteModel(ID, NOME);
        clienteRequest = new ClienteRequest(ID,NOME);
        clienteResponse = new ClienteResponse(ID,NOME);
    }
}
