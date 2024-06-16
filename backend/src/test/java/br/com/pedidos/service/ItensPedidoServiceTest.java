package br.com.pedidos.service;


import br.com.pedidos.dto.*;
import br.com.pedidos.model.ClienteModel;
import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.repository.ClienteRepository;
import br.com.pedidos.repository.ItensPedidoRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItensPedidoServiceTest {

    private static final Integer     ID_ITENSPEDIDO  = 1;
    private static final Integer     NUMERO_PEDIDO   = 2;
    private static final Integer     NUMERO_CONTROLE = 133;
    private static final LocalDate   DATA_CADASTRO   = LocalDate.now();
    private static final String      NOME           = "Sofa de Sala";
    private static final Double      VALOR          = (Double) 1000.0;
    private static final Integer     QUANTIDADE     = 1;
    private static final Integer     ID_CLIENTE      = 1;
    private static final Double VALOR_TOTAL = (Double) 500.0;
    private static final Integer ID      = 1;
    private static final Integer INDEX   = 0;
    private static final String NOME_CLIENTE     = "Maria Souza";

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private ItensPedidoRepository itensPedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Autowired
    @InjectMocks
    private ClienteServiceImpl clienteService;
    @Autowired
    @InjectMocks
    private ItensPedidoServiceImpl itensPedidoService;

    @Autowired
    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startItensPedido();
    }

    private ClienteModel cliente;
    private ClienteRequest clienteRequest;
    private PedidoModel pedido;
    private PedidoRequest pedidoRequest;
    private ItensPedidoModel itensPedido;
    private ItensPedidoRequest itensPedidoRequest;
    private ItensPedidoResponse itensPedidoResponse;

    @Test
    @DisplayName("whenCreateThenReturnSuccess transação com sucesso OK")
    void whenCreateThenReturnSuccess() {
        when(itensPedidoRepository.save(any())).thenReturn(itensPedido);

        ItensPedidoResponse response = itensPedidoService.save(itensPedidoRequest);

        assertNotNull(response);
        assertEquals(ItensPedidoResponse.class, response.getClass());
        assertEquals(ID_ITENSPEDIDO, response.getIdItensPedido());
        assertEquals(NUMERO_PEDIDO, response.getNumeroPedido());
        assertEquals(NUMERO_CONTROLE, response.getNumeroControle());
        assertEquals(DATA_CADASTRO, response.getDataCadastro());
        assertEquals(NOME, response.getNome());
        assertEquals(VALOR, response.getValor());
        assertEquals(QUANTIDADE, response.getQuantidade());
        assertEquals(ID_CLIENTE, response.getIdcliente());

    }


    @Test
    @DisplayName("whenFindAllThenReturnAnListOfItensPedido transação com sucesso OK")
    void whenFindAllThenReturnAnListOfItensPedido() {
        when(itensPedidoRepository.findAll()).thenReturn(List.of(itensPedido));

        List<ItensPedidoResponse> response = itensPedidoService.findAll();

        Assertions.assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(ItensPedidoResponse.class, response.get(INDEX).getClass());

        assertEquals(ID_ITENSPEDIDO, response.get(INDEX).getIdItensPedido());
        assertEquals(NUMERO_PEDIDO, response.get(INDEX).getNumeroPedido());
        assertEquals(NUMERO_CONTROLE, response.get(INDEX).getNumeroControle());
        assertEquals(DATA_CADASTRO, response.get(INDEX).getDataCadastro());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(VALOR, response.get(INDEX).getValor());
        assertEquals(QUANTIDADE, response.get(INDEX).getQuantidade());
        assertEquals(ID_CLIENTE, response.get(INDEX).getIdcliente());
    }

    @Test
    @DisplayName("whenFindExisteNumControlerItensPedido transação com sucesso OK")
    void whenFindExisteNumControlerItensPedido() {
        when(itensPedidoRepository.existeNumeroControle(pedido,NUMERO_CONTROLE)).thenReturn(List.of(itensPedido));

        List<ItensPedidoResponse> response = itensPedidoService.existeNumControler(itensPedidoRequest);

        Assertions.assertNotNull(response);
    }


    @Test
    @DisplayName("whenFindAllfiltroConsultaListOfItensPedido transação com sucesso OK")
    void whenFindAllfiltroConsultaListOfItensPedido() {
        when(itensPedidoRepository.findBylistaTodosPedidosItens()).thenReturn(List.of(itensPedido));

        List<ListarPedidosResponse> response = itensPedidoService.filtroConsulta(0,"","T");

        Assertions.assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(ListarPedidosResponse.class, response.get(INDEX).getClass());

        assertEquals(ID_ITENSPEDIDO, response.get(INDEX).getIdItensPedido());
        assertEquals(NUMERO_PEDIDO, response.get(INDEX).getNumeroPedido());
        assertEquals(NUMERO_CONTROLE, response.get(INDEX).getNumeroControle());
        assertEquals(DATA_CADASTRO, response.get(INDEX).getDataCadastro());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(VALOR, response.get(INDEX).getValor());
        assertEquals(QUANTIDADE, response.get(INDEX).getQuantidade());
        assertEquals(NOME_CLIENTE, response.get(INDEX).getClientenome());

    }



    private void startItensPedido() {
        PedidoModel pedido = new PedidoModel(NUMERO_PEDIDO,DATA_CADASTRO,VALOR_TOTAL);
        cliente = new ClienteModel(ID, NOME_CLIENTE);

        clienteRequest = new ClienteRequest(ID,NOME);
        pedidoRequest = new PedidoRequest(NUMERO_PEDIDO, DATA_CADASTRO, VALOR_TOTAL);
        itensPedido = new ItensPedidoModel(ID_ITENSPEDIDO,pedido,NUMERO_CONTROLE,DATA_CADASTRO, NOME, VALOR, QUANTIDADE, cliente);
        itensPedidoRequest = new ItensPedidoRequest(ID_ITENSPEDIDO,NUMERO_PEDIDO,NUMERO_CONTROLE,DATA_CADASTRO, NOME, VALOR, QUANTIDADE, ID_CLIENTE);
        itensPedidoResponse = new ItensPedidoResponse(ID_ITENSPEDIDO,NUMERO_PEDIDO,NUMERO_CONTROLE,DATA_CADASTRO, NOME, VALOR, QUANTIDADE, ID_CLIENTE);
    }
}
