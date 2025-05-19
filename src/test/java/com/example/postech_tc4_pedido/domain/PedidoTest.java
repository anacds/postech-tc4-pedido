package com.example.postech_tc4_pedido.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void deveCriarPedidoComDadosCorretos() {
        Cliente cliente = new Cliente("Ana", "12345678900");
        Produto produto = new Produto("sku1", 2, "Produto A", "123456", "Descrição", "Fabricante", BigDecimal.TEN, "Categoria");
        Pagamento pagamento = new Pagamento("4111111111111111");
        BigDecimal valorTotal = BigDecimal.valueOf(20);

        Pedido pedido = new Pedido(
                "123",
                "evt-001",
                cliente,
                List.of(produto),
                pagamento,
                StatusPedidoEnum.PENDENTE_PAGAMENTO,
                valorTotal
        );

        assertEquals("123", pedido.getId());
        assertEquals("evt-001", pedido.getIdEvento());
        assertEquals(cliente, pedido.getCliente());
        assertEquals(1, pedido.getProdutos().size());
        assertEquals(produto, pedido.getProdutos().get(0));
        assertEquals(pagamento, pedido.getPagamento());
        assertEquals(StatusPedidoEnum.PENDENTE_PAGAMENTO, pedido.getStatus());
        assertEquals(valorTotal, pedido.getValorTotalPedido());
    }

    @Test
    void devePermitirAtualizarStatusDoPedido() {
        Pedido pedido = new Pedido(
                "123",
                "evt-001",
                new Cliente("Ana", "12345678900"),
                List.of(new Produto("sku1", 1, "Produto", "123", "desc", "fab", BigDecimal.TEN, "cat")),
                new Pagamento("4111111111111111"),
                StatusPedidoEnum.PENDENTE_PAGAMENTO,
                BigDecimal.TEN
        );

        pedido.setStatus(StatusPedidoEnum.FECHADO_COM_SUCESSO);

        assertEquals(StatusPedidoEnum.FECHADO_COM_SUCESSO, pedido.getStatus());
    }
}