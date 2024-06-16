package br.com.pedidos.dto;

import br.com.pedidos.model.ItensPedidoModel;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "itenspedido")
public class ItensPedidoResponse {

    private Integer idItensPedido;
    private Integer numeroControle;
    private Integer numeroPedido;
    private LocalDate dataCadastro;
    private String nome;
    private Double valor;
    private Integer quantidade;
    private Integer idcliente;

    public static ItensPedidoResponse of(ItensPedidoModel itensPedido){
        var response = new ItensPedidoResponse();
        BeanUtils.copyProperties(itensPedido,response);
        response.setNumeroPedido(itensPedido.getPedido().getNumeroPedido());
        response.setIdcliente(itensPedido.getCliente().getId());

        return response;
    }
}
