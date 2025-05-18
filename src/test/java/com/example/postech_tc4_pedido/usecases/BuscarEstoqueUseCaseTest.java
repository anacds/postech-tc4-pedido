package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.dto.PagamentoDTO;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IEstoqueGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarEstoqueUseCaseTest {

    private IEstoqueGateway estoqueGateway;
    private BuscarEstoqueUseCase buscarEstoqueUseCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(IEstoqueGateway.class);
        buscarEstoqueUseCase = new BuscarEstoqueUseCase(estoqueGateway);
    }

    @Test
    void deveRetornarTrueQuandoTodosOsProdutosTiveremEstoqueDisponivel() {
        var produto1 = new ProdutoDTO("SKU1", 2, "Nome1", "123", "desc", "fab", BigDecimal.TEN, "cat");
        var produto2 = new ProdutoDTO("SKU2", 1, "Nome2", "456", "desc", "fab", BigDecimal.ONE, "cat");

        when(estoqueGateway.verificarQuantidadeDisponivel("SKU1")).thenReturn(5);
        when(estoqueGateway.verificarQuantidadeDisponivel("SKU2")).thenReturn(2);

        var pedido = new PedidoDTO(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                new ClienteDTO("João", "12345678900"),
                List.of(produto1, produto2),
                new PagamentoDTO("4111111111111111"),
                StatusPedidoEnum.ABERTO,
                BigDecimal.ZERO
        );

        boolean resultado = buscarEstoqueUseCase.verificarDisponibilidade(pedido);

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoAlgumProdutoNaoTiverEstoqueSuficiente() {
        var produto1 = new ProdutoDTO("SKU1", 2, "Nome1", "123", "desc", "fab", BigDecimal.TEN, "cat");
        var produto2 = new ProdutoDTO("SKU2", 3, "Nome2", "456", "desc", "fab", BigDecimal.ONE, "cat");

        when(estoqueGateway.verificarQuantidadeDisponivel("SKU1")).thenReturn(5);
        when(estoqueGateway.verificarQuantidadeDisponivel("SKU2")).thenReturn(2); // insuficiente

        var pedido = new PedidoDTO(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                new ClienteDTO("João", "12345678900"),
                List.of(produto1, produto2),
                new PagamentoDTO("4111111111111111"),
                StatusPedidoEnum.ABERTO,
                BigDecimal.ZERO
        );

        boolean resultado = buscarEstoqueUseCase.verificarDisponibilidade(pedido);

        assertFalse(resultado);
    }

    @Test
    void deveRetornarFalseQuandoOcorreExcecao() {
        var produto = new ProdutoDTO("SKU_ERRADO", 1, "Nome", "123", "desc", "fab", BigDecimal.ONE, "cat");

        when(estoqueGateway.verificarQuantidadeDisponivel("SKU_ERRADO"))
                .thenThrow(new RuntimeException("Erro no estoque"));

        var pedido = new PedidoDTO(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                new ClienteDTO("Erro", "00000000000"),
                List.of(produto),
                new PagamentoDTO("4111111111111111"),
                StatusPedidoEnum.ABERTO,
                BigDecimal.ZERO
        );

        boolean resultado = buscarEstoqueUseCase.verificarDisponibilidade(pedido);

        assertFalse(resultado);
    }
}