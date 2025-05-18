package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarProdutosUseCaseTest {

    private IProdutoGateway produtoGateway;
    private BuscarProdutosUseCase buscarProdutosUseCase;

    @BeforeEach
    void setUp() {
        produtoGateway = mock(IProdutoGateway.class);
        buscarProdutosUseCase = new BuscarProdutosUseCase(produtoGateway);
    }

    @Test
    void deveRetornarListaDeProdutosEntityQuandoBuscarPorSkus() {
        var produtoDTO1 = new ProdutoDTO("SKU1", 2, "Nome1", "123", "desc", "fab", BigDecimal.TEN, "cat");
        var produtoDTO2 = new ProdutoDTO("SKU2", 1, "Nome2", "456", "desc", "fab", BigDecimal.ONE, "cat");

        when(produtoGateway.buscarProdutoPorSku("SKU1")).thenReturn(produtoDTO1);
        when(produtoGateway.buscarProdutoPorSku("SKU2")).thenReturn(produtoDTO2);

        var resultado = buscarProdutosUseCase.buscarPorSkus(List.of(produtoDTO1, produtoDTO2));

        assertEquals(2, resultado.size());
        assertEquals("SKU1", resultado.get(0).getSku());
        assertEquals(2, resultado.get(0).getQuantidade());
        assertEquals("SKU2", resultado.get(1).getSku());
        assertEquals(1, resultado.get(1).getQuantidade());
    }

    @Test
    void deveRetornarListaVaziaEmCasoDeErro() {
        var produtoDTO = new ProdutoDTO("ERRO", 1, "ProdutoErro", "000", "desc", "fab", BigDecimal.ONE, "cat");

        when(produtoGateway.buscarProdutoPorSku("ERRO")).thenThrow(new RuntimeException("Falha"));

        var resultado = buscarProdutosUseCase.buscarPorSkus(List.of(produtoDTO));

        assertTrue(resultado.isEmpty());
    }
}