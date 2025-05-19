package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.dto.PagamentoDTO;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IEstoqueGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

class BaixarEstoqueUseCaseTest {

    private IEstoqueGateway estoqueGateway;
    private BaixarEstoqueUseCase baixarEstoqueUseCase;

    @BeforeEach
    void setup() {
        estoqueGateway = Mockito.mock(IEstoqueGateway.class);
        baixarEstoqueUseCase = new BaixarEstoqueUseCase(estoqueGateway);
    }

    @Test
    void deveBaixarEstoqueDosProdutos() {
        ProdutoDTO produto1 = new ProdutoDTO("SKU123", 2, null, null, null, null, BigDecimal.TEN, null);
        ProdutoDTO produto2 = new ProdutoDTO("SKU456", 1, null, null, null, null, BigDecimal.ONE, null);
        PedidoDTO pedidoDTO = new PedidoDTO("id", "evt", new ClienteDTO("Ana", "111"),
                List.of(produto1, produto2), new PagamentoDTO("card"), null, null);

        baixarEstoqueUseCase.baixar(pedidoDTO);

        verify(estoqueGateway).removerDoEstoque("SKU123", 2);
        verify(estoqueGateway).removerDoEstoque("SKU456", 1);
    }

    @Test
    void deveTratarExcecaoAoBaixarEstoque() {
        ProdutoDTO produto1 = new ProdutoDTO("SKU_ERRADO", 2, null, null, null, null, BigDecimal.TEN, null);
        PedidoDTO pedidoDTO = new PedidoDTO("id", "evt", new ClienteDTO("Ana", "111"),
                List.of(produto1), new PagamentoDTO("card"), null, null);

        doThrow(new RuntimeException()).when(estoqueGateway).removerDoEstoque("SKU_ERRADO", 2);

        baixarEstoqueUseCase.baixar(pedidoDTO);

        verify(estoqueGateway).removerDoEstoque("SKU_ERRADO", 2);
    }
}