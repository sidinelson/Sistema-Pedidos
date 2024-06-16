package br.com.pedidos.dto;


import br.com.pedidos.model.ItensPedidoModel;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

import static org.springframework.util.ObjectUtils.isEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "itenspedidos")
public class ListarPedidosResponse {

    private Integer numeroPedido;
    private Double valorTotal;
    private Integer idItensPedido;
    private Integer numeroControle;
    private LocalDate dataCadastro;
    private String nome;
    private Double valor;
    private Integer quantidade;
    private String clientenome;

    public static ListarPedidosResponse of(ItensPedidoModel itensPedido){
        var response = new ListarPedidosResponse();
        BeanUtils.copyProperties(itensPedido,response);
        response.setNumeroPedido(itensPedido.getPedido().getNumeroPedido());
        response.setClientenome(itensPedido.getCliente().getNome());
        if(isEmpty(itensPedido.getPedido().getValorTotal())){
            response.setValorTotal((Double) 0.00);
        }else{
            response.setValorTotal(itensPedido.getPedido().getValorTotal());
        }

        return response;
    }

}
