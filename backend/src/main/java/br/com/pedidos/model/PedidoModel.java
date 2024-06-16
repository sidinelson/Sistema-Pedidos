package br.com.pedidos.model;


import br.com.pedidos.dto.PedidoDTO;
import br.com.pedidos.dto.PedidoRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "PEDIDO")
@EqualsAndHashCode(of="numeroPedido")
public class PedidoModel {

    @Column(name = "NUMERO_PEDIDO", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numeroPedido;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "VALOR_TOTAL")
    private Double  valorTotal;


    public PedidoModel(PedidoDTO data){
        this.numeroPedido = data.numeroPedido();
        this.dataCadastro = data.dataCadastro();
        this.valorTotal   = data.valorTotal();
    }

    public static PedidoModel of(PedidoRequest request){
        var pedido = new PedidoModel();
        BeanUtils.copyProperties(request,pedido);
        return  pedido;
    }
}
