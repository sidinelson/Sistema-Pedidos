package br.com.pedidos.service;


import br.com.pedidos.infra.SuccessResponse;
import br.com.pedidos.dto.PedidoRequest;
import br.com.pedidos.dto.PedidoResponse;
import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import br.com.pedidos.repository.ItensPedidoRepository;
import br.com.pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    public PedidoResponse save(PedidoRequest pedidoRequest){
        if(isEmpty(pedidoRequest.getDataCadastro())){
            pedidoRequest.setDataCadastro(LocalDate.now());
        }
        var pedido = pedidoRepository.save(PedidoModel.of(pedidoRequest));
        return PedidoResponse.of(pedido);
    }

    public List<PedidoResponse> findAll() {
        return pedidoRepository
                .findAll()
                .stream()
                .map(PedidoResponse::of)
                .collect(Collectors.toList());
    }

    public SuccessResponse calcularPedido(Integer numeroPedido){
        var pedidoRequest = new PedidoRequest();
        var pedido = new PedidoModel();
        pedido.setNumeroPedido(numeroPedido);

        List<ItensPedidoModel> listarItens = itensPedidoRepository.findByItensPedidosPedidos(pedido);

        Double totalItens = (double) 0;
        Double totalDesconto = (double) 0;
        Double total = (double) 0;


        if(listarItens.size() > 0){
            for(ItensPedidoModel itens : listarItens)
            {
                totalItens = totalItens + itens.getValor();
            }

            if(listarItens.size() >= 10){
                totalDesconto = totalItens * ((double) 10/100);
                total =  totalItens - totalDesconto;
            }else if(listarItens.size() > 5){
                totalDesconto = totalItens * ((double) 5/100);
                total =  totalItens - totalDesconto;
            }else{
                total =   totalItens;
            }

            pedidoRequest.setNumeroPedido(listarItens.get(0).getPedido().getNumeroPedido());
            pedidoRequest.setDataCadastro(listarItens.get(0).getPedido().getDataCadastro());
            pedidoRequest.setValorTotal(total);
            var atualizarTotal = pedidoRepository.save(PedidoModel.of(pedidoRequest));
        }
        return SuccessResponse.create("PedidoModel calculado com sucesso! R$ "+total.toString());
    }




}
