package br.com.pedidos.repository;

import br.com.pedidos.dto.PedidoDTO;
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
public class PedidoRepositoryTest {
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Consulta findBynumeroPedidos com Sucesso! ")
    void findBynumeroPedidos() {

        PedidoDTO data = new PedidoDTO(null,LocalDate.now(), (Double) 2000.0);
        this.createPedido(data);

        List<PedidoModel> result = this.pedidoRepository.findBynumeroPedidos(123);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Consulta findBydataCadastro com Sucesso!")
    void findBydataCadastro() {

        List<PedidoModel> result = this.pedidoRepository.findBydataCadastro(LocalDate.now());

        assertThat(result.isEmpty()).isTrue();
    }

    private PedidoModel createPedido(PedidoDTO data){
        PedidoModel pedido = new PedidoModel(data);
        this.entityManager.persist(pedido);
        return pedido;
    }
}


