package br.com.pedidos.repository;


import br.com.pedidos.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {
    @Query("FROM PedidoModel p WHERE p.numeroPedido =:numeropedido")//JPQL
    List<PedidoModel> findBynumeroPedidos(Integer numeropedido);

    @Query("FROM PedidoModel p WHERE p.dataCadastro =:dataCadastro")//JPQL
    List<PedidoModel> findBydataCadastro(LocalDate dataCadastro);


}
