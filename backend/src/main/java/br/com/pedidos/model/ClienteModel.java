package br.com.pedidos.model;


import br.com.pedidos.dto.ClienteDTO;
import br.com.pedidos.dto.ClienteRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "CLIENTE")
public class ClienteModel {

    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;


    public ClienteModel(ClienteDTO data){
        this.id     = data.id();
        this.nome   = data.nome();
    }

    public static ClienteModel of(ClienteRequest request){
        var cliente = new ClienteModel();
        BeanUtils.copyProperties(request, cliente);
        return cliente;
    }
}
