package br.com.pedidos.dto;

import br.com.pedidos.model.PedidoModel;
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
@XmlRootElement(name = "pedido")
public class PedidoResponse {

    private Integer numeroPedido;
    private LocalDate dataCadastro;
    private Double valorTotal;

    public static PedidoResponse of(PedidoModel pedido){
        var response = new PedidoResponse();
        BeanUtils.copyProperties(pedido,response);
        return response;
    }



}
