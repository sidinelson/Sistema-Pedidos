package br.com.pedidos.service;

import br.com.pedidos.dto.ClienteRequest;
import br.com.pedidos.dto.ClienteResponse;
import br.com.pedidos.infra.ValidationException;
import br.com.pedidos.model.ClienteModel;
import br.com.pedidos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponse save(ClienteRequest clienteRequest){
        validarClienteNome(clienteRequest);
        var cliente = clienteRepository.save(ClienteModel.of(clienteRequest));

        return ClienteResponse.of(cliente);
    }


    public void validarClienteNome(ClienteRequest clienteRequest){
        if(isEmpty(clienteRequest.getNome())){
            throw new ValidationException("O Cliente deve conter um Nome informado.");
        }
    }


    public List<ClienteResponse> findAll() {
        return clienteRepository
                .findAll()
                .stream()
                .map(ClienteResponse::of)
                .collect(Collectors.toList());
    }
}
