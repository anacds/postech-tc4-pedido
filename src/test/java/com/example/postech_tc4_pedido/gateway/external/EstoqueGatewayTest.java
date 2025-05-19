package com.example.postech_tc4_pedido.gateway.external;

import com.example.postech_tc4_pedido.gateway.external.interfaces.client.EstoqueClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EstoqueGatewayTest {

    private EstoqueClient estoqueClient;
    private EstoqueGateway estoqueGateway;

    @BeforeEach
    void setUp() {
        estoqueClient = mock(EstoqueClient.class);
        estoqueGateway = new EstoqueGateway(estoqueClient);
    }

    @Test
    void deveVerificarQuantidadeDisponivel() {
        String sku = "SKU123";
        when(estoqueClient.verificarQuantidadeDisponivel(sku)).thenReturn(5);
        estoqueGateway.verificarQuantidadeDisponivel(sku);
        verify(estoqueClient, times(1)).verificarQuantidadeDisponivel(sku);
    }

    @Test
    void deveRemoverDoEstoque() {
        String sku = "SKU123";
        int quantidade = 2;
        estoqueGateway.removerDoEstoque(sku, quantidade);
        verify(estoqueClient, times(1)).removerDoEstoque(sku, quantidade);
    }

    @Test
    void deveReporEstoque() {
        String sku = "SKU123";
        int quantidade = 3;
        estoqueGateway.reporEstoque(sku, quantidade);
        verify(estoqueClient, times(1)).reporEstoque(sku, quantidade);
    }
}