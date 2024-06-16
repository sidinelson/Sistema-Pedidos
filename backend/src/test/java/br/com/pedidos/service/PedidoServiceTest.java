package br.com.pedidos.service;



import br.com.pedidos.dto.PedidoRequest;
import br.com.pedidos.dto.PedidoResponse;
import br.com.pedidos.infra.SuccessResponse;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PedidoServiceTest {

    private static final Integer NUMERO_PEDIDO = 123;
    private static final LocalDate DATA_CADASTRO = LocalDate.now();
    private static final Double VALOR_TOTAL = (Double) 500.0;
    private static final Integer INDEX   = 0;

    @Mock
    private PedidoRepository pedidoRepository;

    @Autowired
    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startPedido();
    }

    private PedidoModel pedido;
    private PedidoRequest pedidoRequest;
    private PedidoResponse pedidoResponse;

    @Test
    @DisplayName("whenCreateThenReturnSuccess transação com sucesso OK")
    void whenCreateThenReturnSuccess() {
        when(pedidoRepository.save(any())).thenReturn(pedido);

        PedidoResponse response = pedidoService.save(pedidoRequest);

        assertNotNull(response);
        assertEquals(PedidoResponse.class, response.getClass());
        assertEquals(NUMERO_PEDIDO, response.getNumeroPedido());
        assertEquals(DATA_CADASTRO, response.getDataCadastro());
        assertEquals(VALOR_TOTAL, response.getValorTotal());

    }

    @Test
    @DisplayName("whenFindAllThenReturnAnListOfPedido transação com sucesso OK")
    void whenFindAllThenReturnAnListOfPedido() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<PedidoResponse> response = pedidoService.findAll();

        Assertions.assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(PedidoResponse.class, response.get(INDEX).getClass());

        assertEquals(NUMERO_PEDIDO, response.get(INDEX).getNumeroPedido());
        assertEquals(DATA_CADASTRO, response.get(INDEX).getDataCadastro());
        assertEquals(VALOR_TOTAL, response.get(INDEX).getValorTotal());
    }


    @Test
    @DisplayName("whenFindcalcularPedido transação com sucesso OK")
    void whenFindcalcularPedido() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        SuccessResponse response = pedidoService.calcularPedido(NUMERO_PEDIDO);

        Assertions.assertNotNull(response);
    }

    private void startPedido() {
        pedido = new PedidoModel(NUMERO_PEDIDO, DATA_CADASTRO, VALOR_TOTAL);
        pedidoRequest = new PedidoRequest(NUMERO_PEDIDO, DATA_CADASTRO, VALOR_TOTAL);
        pedidoResponse = new PedidoResponse(NUMERO_PEDIDO, DATA_CADASTRO, VALOR_TOTAL);
    }


}