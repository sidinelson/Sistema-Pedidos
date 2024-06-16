package br.com.pedidos.dto;

import br.com.pedidos.model.ClienteModel;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "cliente")
public class ClienteResponse {
    private Integer id;
    private String nome;

    public static ClienteResponse of(ClienteModel cliente){
        var response = new ClienteResponse();
        BeanUtils.copyProperties(cliente, response);
        return response;
    }

}
