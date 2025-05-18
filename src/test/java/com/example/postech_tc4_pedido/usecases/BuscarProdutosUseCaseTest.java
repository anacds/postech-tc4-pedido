package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.database.entity.ProdutoEntity;
import com.example.postech_tc4_pedido.gateway.external.ProdutoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarProdutosUseCaseTest {

    private ProdutoClient produtoClient;
    private BuscarProdutosUseCase buscarProdutosUseCase;

    @BeforeEach
    void setUp() {
        produtoClient = mock(ProdutoClient.class);
        buscarProdutosUseCase = new BuscarProdutosUseCase(produtoClient);
    }

    @Test
    void deveBuscarProdutosPorSkusComSucesso() {
        String sku1 = "ABC123";
        String sku2 = "DEF456";

        ProdutoDTO produto1 = new ProdutoDTO(sku1, 2, "Produto 1", "123456789", "Descrição 1", "Fabricante 1", new BigDecimal("10.50"), "Categoria 1");
        ProdutoDTO produto2 = new ProdutoDTO(sku2, 1, "Produto 2", "987654321", "Descrição 2", "Fabricante 2", new BigDecimal("20.00"), "Categoria 2");

        when(produtoClient.buscarProdutoPorSku(sku1)).thenReturn(produto1);
        when(produtoClient.buscarProdutoPorSku(sku2)).thenReturn(produto2);

        List<ProdutoEntity> resultado = buscarProdutosUseCase.buscarPorSkus(List.of(sku1, sku2));

        assertEquals(2, resultado.size());
        assertEquals("Produto 1", resultado.get(0).getNome());
        assertEquals("Produto 2", resultado.get(1).getNome());

        verify(produtoClient, times(1)).buscarProdutoPorSku(sku1);
        verify(produtoClient, times(1)).buscarProdutoPorSku(sku2);
    }

    @Test
    void deveRetornarListaVaziaEmCasoDeErro() {
        when(produtoClient.buscarProdutoPorSku(anyString())).thenThrow(new RuntimeException("Erro"));
        List<ProdutoEntity> resultado = buscarProdutosUseCase.buscarPorSkus(List.of("SKU-ERRO"));
        assertTrue(resultado.isEmpty());
        verify(produtoClient, times(1)).buscarProdutoPorSku("SKU-ERRO");
    }
}