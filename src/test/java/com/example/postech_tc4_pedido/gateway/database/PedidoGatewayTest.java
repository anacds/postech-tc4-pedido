package com.example.postech_tc4_pedido.gateway.database;

import com.example.postech_tc4_pedido.domain.*;
import com.example.postech_tc4_pedido.gateway.database.entity.PedidoEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.ProdutoEntity;
import com.example.postech_tc4_pedido.gateway.database.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoGatewayTest {

    private PedidoRepository pedidoRepository;
    private PedidoGateway pedidoGateway;

    @BeforeEach
    void setUp() {
        pedidoRepository = mock(PedidoRepository.class);
        pedidoGateway = new PedidoGateway(pedidoRepository);
    }

    @Test
    void deveBuscarPedidoPorId() {
        PedidoEntity entity = new PedidoEntity(
                "id123", "evento1",
                new com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity("Ana", "123"),
                List.of(new ProdutoEntity("sku1", 1, "Produto", "123", "desc", "fab", BigDecimal.TEN, "cat")),
                new com.example.postech_tc4_pedido.gateway.database.entity.PagamentoEntity("4111111111111111"),
                StatusPedidoEnum.PENDENTE_PAGAMENTO,
                BigDecimal.TEN
        );

        when(pedidoRepository.findById("id123")).thenReturn(Optional.of(entity));

        Optional<Pedido> result = pedidoGateway.buscarPorId("id123");

        assertTrue(result.isPresent());
        assertEquals("id123", result.get().getId());
        assertEquals("Ana", result.get().getCliente().getNome());
        verify(pedidoRepository, times(1)).findById("id123");
    }

    @Test
    void deveSalvarPedido() {
        Pedido pedido = new Pedido(
                "id456", "evento2",
                new Cliente("João", "321"),
                List.of(new Produto("sku2", 2, "Produto2", "456", "desc2", "fab2", BigDecimal.valueOf(20), "cat2")),
                new Pagamento("4222222222222222"),
                StatusPedidoEnum.PENDENTE_PAGAMENTO,
                BigDecimal.valueOf(40)
        );

        when(pedidoRepository.save(any(PedidoEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Pedido salvo = pedidoGateway.salvar(pedido);

        assertNotNull(salvo);
        assertEquals("id456", salvo.getId());
        assertEquals("João", salvo.getCliente().getNome());
        verify(pedidoRepository, times(1)).save(any(PedidoEntity.class));
    }
}