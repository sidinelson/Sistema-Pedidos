package br.com.pedidos.model;

import br.com.pedidos.dto.ItensPedidoDTO;
import br.com.pedidos.dto.ItensPedidoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "ITENS_PEDIDO")
public class ItensPedidoModel {

    @Column(name = "ID_ITENS", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItensPedido ;

    @ManyToOne
    @JoinColumn(name = "NUMERO_PEDIDO")
    private PedidoModel pedido;

    @Column(name = "NUMERO_CONTROLE")
    private Integer   numeroControle;

    @Column(name = "DATA_CADASTRO")
    private LocalDate dataCadastro;

    @Column(name = "NOME", nullable = false)
    private String  nome;

    @Column(name = "VALOR")
    private Double  valor;

    @Column(name = "QUANTIDADE")
    private Integer  quantidade;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private ClienteModel cliente;


    public ItensPedidoModel(ItensPedidoDTO data){
        this.idItensPedido  = data.idItensPedido();
        this.pedido         = data.numeroPedido();
        this.numeroControle = data.numeroControle();
        this.dataCadastro   = data.dataCadastro();
        this.nome           = data.nome();
        this.valor          = data.valor();
        this.quantidade     = data.quantidade();
        this.cliente        = data.idcliente();
    }


    public static ItensPedidoModel of(ItensPedidoRequest request){
        var itensPedido = new ItensPedidoModel();
        BeanUtils.copyProperties(request,itensPedido);

        PedidoModel pedido = new PedidoModel();
        pedido.setNumeroPedido(request.getNumeroPedido());
        itensPedido.setPedido(pedido);
        ClienteModel cliente = new ClienteModel();
        cliente.setId(request.getIdcliente());
        itensPedido.setCliente(cliente);
        return  itensPedido;
    }
}
