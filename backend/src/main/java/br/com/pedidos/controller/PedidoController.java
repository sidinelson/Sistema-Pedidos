package br.com.pedidos.controller;



import br.com.pedidos.infra.SuccessResponse;
import br.com.pedidos.dto.PedidoRequest;
import br.com.pedidos.dto.PedidoResponse;
import br.com.pedidos.service.PedidoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import static java.lang.String.format;

@RestController
@Slf4j
@RequestMapping("api/pedido")
@AllArgsConstructor
public class PedidoController {

    @Autowired
    private PedidoServiceImpl pedidoService;

    @Operation(summary = "Realizar cadastro de PedidoModel", method = "POST")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de PedidoModel realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar cadastro de PedidoModel"),
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PedidoResponse save(@RequestBody PedidoRequest pedidoRequest){

        return pedidoService.save(pedidoRequest);
    }

    @Operation(summary = "Calcular total Pedidos", method = "GET")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar calculo dos dados"),
    })
    @GetMapping(path = "{numeroPedido}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SuccessResponse findById(@PathVariable Integer numeroPedido) {
        log.info(format("Calcular total Pedidos"));
        return pedidoService.calcularPedido(numeroPedido);
    }

}
