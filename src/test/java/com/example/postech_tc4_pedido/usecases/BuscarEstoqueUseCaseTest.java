package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.external.EstoqueClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarEstoqueUseCaseTest {

    private EstoqueClient estoqueClient;
    private BuscarEstoqueUseCase buscarEstoqueUseCase;

    @BeforeEach
    void setUp() {
        estoqueClient = mock(EstoqueClient.class);
        buscarEstoqueUseCase = new BuscarEstoqueUseCase(estoqueClient);
    }

    @Test
    void deveRetornarTrueQuandoTiverEstoqueDisponivel() {
        var produto1 = new ProdutoDTO("SKU123", 2, "", "", "", "", new BigDecimal("0.00"), "");
        var produto2 = new ProdutoDTO("SKU456", 1, "", "", "", "", new BigDecimal("0.00"), "");
        var pedidoDTO = new PedidoDTO(null, null, null, List.of(produto1, produto2), null, null, null);

        when(estoqueClient.verificarQuantidadeDisponivel("SKU123")).thenReturn(3);
        when(estoqueClient.verificarQuantidadeDisponivel("SKU456")).thenReturn(2);

        boolean disponivel = buscarEstoqueUseCase.verificarDisponibilidade(pedidoDTO);

        assertTrue(disponivel);
    }

    @Test
    void deveRetornarFalseQuandoNaoTiverEstoqueDisponivel() {
        var produto1 = new ProdutoDTO("SKU123", 2, "", "", "", "", new BigDecimal("0.00"), "");
        var produto2 = new ProdutoDTO("SKU456", 5, "", "", "", "", new BigDecimal("0.00"), "");
        var pedidoDTO = new PedidoDTO(null, null, null, List.of(produto1, produto2), null, null, null);

        when(estoqueClient.verificarQuantidadeDisponivel("SKU123")).thenReturn(2);
        when(estoqueClient.verificarQuantidadeDisponivel("SKU456")).thenReturn(3);

        boolean disponivel = buscarEstoqueUseCase.verificarDisponibilidade(pedidoDTO);

        assertFalse(disponivel);
    }

    @Test
    void deveRetornarFalseQuandoHouverExcecao() {
        var produto = new ProdutoDTO("SKU123", 1, "", "", "", "", new BigDecimal("0.00"), "");
        var pedidoDTO = new PedidoDTO(null, null, null, List.of(produto), null, null, null);

        when(estoqueClient.verificarQuantidadeDisponivel("SKU123"))
                .thenThrow(new RuntimeException("Erro inesperado"));

        boolean disponivel = buscarEstoqueUseCase.verificarDisponibilidade(pedidoDTO);

        assertFalse(disponivel);
    }
}