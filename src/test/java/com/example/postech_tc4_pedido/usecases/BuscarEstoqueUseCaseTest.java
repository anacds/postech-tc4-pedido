package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.dto.PagamentoDTO;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IEstoqueGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarEstoqueUseCaseTest {

    private IEstoqueGateway estoqueGateway;
    private BuscarEstoqueUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(IEstoqueGateway.class);
        useCase = new BuscarEstoqueUseCase(estoqueGateway);
    }

    @Test
    void deveRetornarTrueQuandoTodosProdutosTemEstoqueSuficiente() {
        ProdutoDTO produto1 = new ProdutoDTO("SKU1", 2, null, null, null, null, null, null);
        ProdutoDTO produto2 = new ProdutoDTO("SKU2", 1, null, null, null, null, null, null);
        PedidoDTO pedido = new PedidoDTO("1", "evt1", new ClienteDTO("Ana", "123"), List.of(produto1, produto2), new PagamentoDTO("1234"), StatusPedidoEnum.ABERTO, BigDecimal.ZERO);

        when(estoqueGateway.verificarQuantidadeDisponivel("SKU1")).thenReturn(5);
        when(estoqueGateway.verificarQuantidadeDisponivel("SKU2")).thenReturn(1);

        boolean resultado = useCase.verificarDisponibilidade(pedido);

        assertTrue(resultado);
        verify(estoqueGateway).verificarQuantidadeDisponivel("SKU1");
        verify(estoqueGateway).verificarQuantidadeDisponivel("SKU2");
    }

    @Test
    void deveRetornarFalseQuandoAlgumProdutoNaoTemEstoque() {
        ProdutoDTO produto1 = new ProdutoDTO("SKU1", 2, null, null, null, null, null, null);
        ProdutoDTO produto2 = new ProdutoDTO("SKU2", 3, null, null, null, null, null, null);
        PedidoDTO pedido = new PedidoDTO("1", "evt1", new ClienteDTO("Ana", "123"), List.of(produto1, produto2), new PagamentoDTO("1234"), StatusPedidoEnum.ABERTO, BigDecimal.ZERO);

        when(estoqueGateway.verificarQuantidadeDisponivel("SKU1")).thenReturn(2);
        when(estoqueGateway.verificarQuantidadeDisponivel("SKU2")).thenReturn(1);

        boolean resultado = useCase.verificarDisponibilidade(pedido);

        assertFalse(resultado);
    }

    @Test
    void deveRetornarFalseQuandoLancaExcecao() {
        ProdutoDTO produto = new ProdutoDTO("SKU1", 1, null, null, null, null, null, null);
        PedidoDTO pedido = new PedidoDTO("1", "evt1", new ClienteDTO("Ana", "123"), List.of(produto), new PagamentoDTO("1234"), StatusPedidoEnum.ABERTO, BigDecimal.ZERO);

        when(estoqueGateway.verificarQuantidadeDisponivel("SKU1")).thenThrow(new RuntimeException("Falha"));

        boolean resultado = useCase.verificarDisponibilidade(pedido);

        assertFalse(resultado);
    }
}