package br.com.pedidos.dto;

import java.time.LocalDate;

public record PedidoDTO(Integer numeroPedido, LocalDate dataCadastro, Double valorTotal) {
}
