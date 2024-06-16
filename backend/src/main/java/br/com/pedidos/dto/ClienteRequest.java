package br.com.pedidos.dto;


import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "cliente")
public class ClienteRequest{
    private Integer id;
    private String nome;

}
