package br.com.pedidos.service;

import br.com.pedidos.dto.ItensPedidoRequest;
import br.com.pedidos.dto.ItensPedidoResponse;
import br.com.pedidos.dto.ListarPedidosResponse;

import java.util.List;

public interface ItensPedidoService {

    public ItensPedidoResponse save(ItensPedidoRequest itensPedidoRequest);

    public List<ItensPedidoResponse> findAll();
    public List<ItensPedidoResponse> findByItensPedidosPedidos(Integer numeroPedido);
    public List<ItensPedidoResponse> existeNumControler(ItensPedidoRequest itensPedidoRequest);
    public List<ListarPedidosResponse> filtroConsulta(Integer numeroPedido, String dataCadastro, String todos);
}
