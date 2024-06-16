package br.com.pedidos.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "pedido")
public class PedidoRequest {

    private Integer numeroPedido;
    private LocalDate dataCadastro;
    private Double valorTotal;

}
