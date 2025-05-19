package com.example.postech_tc4_pedido.gateway.external;

import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.client.ProdutoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProdutoGatewayTest {

    private ProdutoClient produtoClient;
    private ProdutoGateway produtoGateway;

    @BeforeEach
    void setUp() {
        produtoClient = mock(ProdutoClient.class);
        produtoGateway = new ProdutoGateway(produtoClient);
    }

    @Test
    void deveBuscarProdutoPorSku() {
        ProdutoDTO produtoEsperado = new ProdutoDTO("sku123", 2, "Produto A", "789456", "desc", "fab", new BigDecimal("10.00"), "cat");
        when(produtoClient.buscarProdutoPorSku("sku123")).thenReturn(produtoEsperado);

        ProdutoDTO resultado = produtoGateway.buscarProdutoPorSku("sku123");

        assertEquals(produtoEsperado, resultado);
        verify(produtoClient, times(1)).buscarProdutoPorSku("sku123");
    }
}