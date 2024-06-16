package br.com.pedidos.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "itenspedido")
public class ItensPedidoRequest{

    private Integer   idItensPedido;
    private Integer   numeroPedido;
    private Integer   numeroControle;
    private LocalDate dataCadastro;
    private String    nome;
    private Double    valor;
    private Integer   quantidade;
    private Integer   idcliente;
}
