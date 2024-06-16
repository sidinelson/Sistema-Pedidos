package br.com.pedidos.service;

import br.com.pedidos.dto.PedidoRequest;
import br.com.pedidos.dto.PedidoResponse;
import br.com.pedidos.infra.SuccessResponse;
import java.util.List;

public interface PedidoService {

    public PedidoResponse save(PedidoRequest pedidoRequest);

    public List<PedidoResponse> findAll();
    public SuccessResponse calcularPedido(Integer numeroPedido);
}
