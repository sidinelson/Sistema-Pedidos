package br.com.pedidos.dto;

import br.com.pedidos.model.ClienteModel;
import br.com.pedidos.model.PedidoModel;

import java.time.LocalDate;

public record ItensPedidoDTO(Integer idItensPedido,
                             PedidoModel numeroPedido,
                             Integer numeroControle,
                             LocalDate dataCadastro,
                             String nome,
                             Double valor,
                             Integer quantidade,
                             ClienteModel idcliente){
}
