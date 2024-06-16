package br.com.pedidos.service;

import br.com.pedidos.dto.ClienteRequest;
import br.com.pedidos.dto.ClienteResponse;


import java.util.List;


public interface ClienteService {

    public ClienteResponse save(ClienteRequest clienteRequest);
    public void validarClienteNome(ClienteRequest clienteRequest);
    public List<ClienteResponse> findAll();
}
