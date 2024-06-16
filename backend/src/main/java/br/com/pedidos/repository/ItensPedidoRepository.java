package br.com.pedidos.repository;

import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedidoModel, Integer> {
    @Query("FROM ItensPedidoModel i WHERE i.pedido =:pedido AND i.numeroControle =:numeroControle ")//JPQL
    List<ItensPedidoModel> existeNumeroControle(PedidoModel pedido, Integer numeroControle);
    @Query("FROM ItensPedidoModel i WHERE i.pedido =:pedido")//JPQL
    List<ItensPedidoModel> findByItensPedidosPedidos(PedidoModel pedido);

    @Query(" SELECT i, p.dataCadastro, p.valorTotal, c.nome FROM ItensPedidoModel i "
            + " INNER JOIN i.pedido p"
            + " INNER JOIN i.cliente c"
            + " WHERE i.pedido.numeroPedido =:numeroPedido  "
            + " ORDER BY i.dataCadastro ASC")//JPQL
    List<ItensPedidoModel> findBylistaNumeroPedidosItens(Integer numeroPedido);

    @Query(" SELECT i, p.dataCadastro, p.valorTotal, c.nome from ItensPedidoModel i "
            + " INNER JOIN i.pedido p"
            + " INNER JOIN i.cliente c"
            + " WHERE i.pedido.dataCadastro =:dataCadastro  "
            + " ORDER BY i.dataCadastro ASC")//JPQL
    List<ItensPedidoModel> findBylistaDataPedidosItens(LocalDate dataCadastro);

    @Query(" select i, p.dataCadastro, p.valorTotal, c.nome from ItensPedidoModel i "
        + " inner join i.pedido p"
        + " inner join i.cliente c"
        + " order by i.dataCadastro asc")//JPQL
    List<ItensPedidoModel> findBylistaTodosPedidosItens();


}
