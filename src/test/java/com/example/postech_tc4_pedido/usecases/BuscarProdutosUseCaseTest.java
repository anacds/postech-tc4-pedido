package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.Produto;
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
    private BuscarProdutosUseCase useCase;

    @BeforeEach
    void setUp() {
        produtoGateway = mock(IProdutoGateway.class);
        useCase = new BuscarProdutosUseCase(produtoGateway);
    }

    @Test
    void deveRetornarProdutosComQuantidadeCorreta() {
        ProdutoDTO dto1 = new ProdutoDTO("SKU1", 2, null, null, null, null, null, null);
        ProdutoDTO dto2 = new ProdutoDTO("SKU2", 1, null, null, null, null, null, null);

        when(produtoGateway.buscarProdutoPorSku("SKU1"))
                .thenReturn(new ProdutoDTO("SKU1", 0, "Nome1", "123", "Desc1", "Fab1", BigDecimal.TEN, "Cat1"));
        when(produtoGateway.buscarProdutoPorSku("SKU2"))
                .thenReturn(new ProdutoDTO("SKU2", 0, "Nome2", "456", "Desc2", "Fab2", BigDecimal.ONE, "Cat2"));

        List<Produto> produtos = useCase.buscarPorSkus(List.of(dto1, dto2));

        assertEquals(2, produtos.size());
        assertEquals("SKU1", produtos.get(0).getSku());
        assertEquals(2, produtos.get(0).getQuantidade());
        assertEquals("SKU2", produtos.get(1).getSku());
        assertEquals(1, produtos.get(1).getQuantidade());

        verify(produtoGateway).buscarProdutoPorSku("SKU1");
        verify(produtoGateway).buscarProdutoPorSku("SKU2");
    }

    @Test
    void deveRetornarListaVaziaQuandoGatewayLancarExcecao() {
        ProdutoDTO dto = new ProdutoDTO("SKU-ERR", 1, null, null, null, null, null, null);
        when(produtoGateway.buscarProdutoPorSku("SKU-ERR")).thenThrow(new RuntimeException("Erro"));

        List<Produto> produtos = useCase.buscarPorSkus(List.of(dto));

        assertTrue(produtos.isEmpty());
    }
}