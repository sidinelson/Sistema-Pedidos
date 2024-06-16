package br.com.pedidos.repository;

import br.com.pedidos.model.ItensPedidoModel;
import br.com.pedidos.model.PedidoModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ItensPedidoRepositoryTest {

    @Autowired
    ItensPedidoRepository itensPedidoRepository;

    @Autowired
    EntityManager entityManager;


    @Test

    @DisplayName("Consulta findByItensPedidosPedidos com Sucesso! ")
    void findByItensPedidosPedidos() {
        PedidoModel pedido = new PedidoModel();
        pedido.setNumeroPedido(1);

        List<ItensPedidoModel> result = this.itensPedidoRepository.findByItensPedidosPedidos(pedido);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Consulta existeNumeroControle com Sucesso! ")
    void existeNumeroControle() {

        PedidoModel pedido = new PedidoModel();
        pedido.setNumeroPedido(12);
        List<ItensPedidoModel> result = this.itensPedidoRepository.existeNumeroControle(pedido,123);

        assertThat(result.isEmpty()).isTrue();
    }



    @Test
    @DisplayName("Consulta findBylistaNumeroPedidosItens com Sucesso! ")
    void findBylistaNumeroPedidosItens() {

        List<ItensPedidoModel> result = this.itensPedidoRepository.findBylistaNumeroPedidosItens(1);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Consulta findBylistaDataPedidosItens com Sucesso! ")
    void findBylistaDataPedidosItens() {
        LocalDate dataCAdastro = LocalDate.now();
        List<ItensPedidoModel> result = this.itensPedidoRepository.findBylistaDataPedidosItens(dataCAdastro);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Consulta findBylistaTodosPedidosItens com Sucesso! ")
    void findBylistaTodosPedidosItens() {

        List<ItensPedidoModel> result = this.itensPedidoRepository.findBylistaTodosPedidosItens();

        assertThat(result.isEmpty()).isTrue();
    }
    






}
