package br.com.pedidos.controller;


import br.com.pedidos.dto.*;
import br.com.pedidos.service.ItensPedidoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/itenspedido")
@AllArgsConstructor
public class ItensPedidoController {

    @Autowired
    private ItensPedidoServiceImpl itensPedidoService;


    @Operation(summary = "Realizar cadastro de Itens PedidoModel", method = "POST")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de Itens PedidoModel realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar cadastro de Itens PedidoModel"),
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ItensPedidoResponse save(@RequestBody ItensPedidoRequest itensPedidoRequest){
        return itensPedidoService.save(itensPedidoRequest);
    }


    @Operation(summary = "Listar todos os itens Pedidos", method = "GET")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<ItensPedidoResponse> findAll() {
        return itensPedidoService.findAll();
    }

    @Operation(summary = "Listar filtro de consulta", method = "GET")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping(path = "/pedido/{numeroPedido}/data/{dataCadastro}/filtro/{todos}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<ListarPedidosResponse> listarFiltroPedidos(@PathVariable Integer numeroPedido,
                                                           @PathVariable String dataCadastro,
                                                           @PathVariable String todos){

        if(dataCadastro.equals("0")){
            dataCadastro = null;
        }

        if(todos.equals("0")){
            todos = null;
        }
        return itensPedidoService.filtroConsulta(numeroPedido,dataCadastro,todos);
    }
}
