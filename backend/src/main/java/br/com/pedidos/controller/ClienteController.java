package br.com.pedidos.controller;

import br.com.pedidos.dto.ClienteRequest;
import br.com.pedidos.dto.ClienteResponse;
import br.com.pedidos.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import static java.lang.String.format;
import java.util.List;




@Slf4j
@RestController
@RequestMapping("api/cliente")
@AllArgsConstructor
public class ClienteController {

        @Autowired
        private ClienteService clienteService;

        @Operation(summary = "Realiza cadastro de cliente", method = "POST")

        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de ClienteModel realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar cadastro de ClienteModel"),
        })
        @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                     consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
        public ClienteResponse save(@RequestBody ClienteRequest clienteRequest){
                log.info(format("Inicio método ClienteModel!"));
                return clienteService.save(clienteRequest);
        }

        @Operation(summary = "Listar todos os clientes", method = "GET")

        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
                @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
                @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
        })
        @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
        public List<ClienteResponse> findAll() {
                log.info(format("Listar todos os clientes!"));
                return clienteService.findAll();
        }

}
