package br.com.pedidos.service;

import br.com.pedidos.dto.ListarPedidosResponse;
import br.com.pedidos.dto.ItensPedidoRequest;
import br.com.pedidos.dto.ItensPedidoResponse;
import br.com.pedidos.infra.ValidationException;
import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.repository.ItensPedidoRepository;
import br.com.pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ItensPedidoServiceImpl  implements ItensPedidoService{
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    public ItensPedidoResponse save(ItensPedidoRequest itensPedidoRequest){
        if(findByItensPedidosPedidos(itensPedidoRequest.getNumeroPedido()).size() > 9){
            throw new ValidationException("Você ultrapassou o limite 10 pedidos!");
        }

        if(!isEmpty(existeNumControler(itensPedidoRequest))){
            throw new ValidationException("Número de Controler já cadastrado nesse PedidoModel!");
        }

        if(isEmpty(itensPedidoRequest.getDataCadastro())){
            itensPedidoRequest.setDataCadastro(LocalDate.now());
        }

        if(isEmpty(itensPedidoRequest.getQuantidade())){
            itensPedidoRequest.setQuantidade(1);
        }

        var itensPedido = itensPedidoRepository.save(ItensPedidoModel.of(itensPedidoRequest));
        return ItensPedidoResponse.of(itensPedido);
    }

    public List<ItensPedidoResponse> findAll() {
        return itensPedidoRepository
                .findAll()
                .stream()
                .map(ItensPedidoResponse::of)
                .collect(Collectors.toList());
    }

    public List<ItensPedidoResponse> findByItensPedidosPedidos(Integer numeroPedido) {
        var pedido = new PedidoModel();
        pedido.setNumeroPedido(numeroPedido);
        return itensPedidoRepository
                .findByItensPedidosPedidos(pedido)
                .stream()
                .map(ItensPedidoResponse::of)
                .collect(Collectors.toList());
    }

    public List<ItensPedidoResponse> existeNumControler(ItensPedidoRequest itensPedidoRequest){
        var pedido = new PedidoModel();
        pedido.setNumeroPedido(itensPedidoRequest.getNumeroPedido());

        return itensPedidoRepository
                .existeNumeroControle(pedido, itensPedidoRequest.getNumeroControle())
                .stream()
                .map(ItensPedidoResponse::of)
                .collect(Collectors.toList());
    }
    public List<ListarPedidosResponse> filtroConsulta(Integer numeroPedido, String dataCadastro, String todos){

        List<ListarPedidosResponse> result = null;


        if(!numeroPedido.equals(0)){
            result =  itensPedidoRepository
                    .findBylistaNumeroPedidosItens(numeroPedido)
                    .stream()
                    .map(ListarPedidosResponse::of)
                    .collect(Collectors.toList());

        }else if(!isEmpty(dataCadastro)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dataCadastro,formatter);

            result =  itensPedidoRepository
                    .findBylistaDataPedidosItens(date)
                    .stream()
                    .map(ListarPedidosResponse::of)
                    .collect(Collectors.toList());

        }else if(!isEmpty(todos)){
            result =  itensPedidoRepository
                    .findBylistaTodosPedidosItens()
                    .stream()
                    .map(ListarPedidosResponse::of)
                    .collect(Collectors.toList());
        }

        return result;
    }

}
