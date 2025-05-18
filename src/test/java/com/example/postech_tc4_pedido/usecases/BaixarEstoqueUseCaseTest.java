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

import static org.mockito.Mockito.*;

public class BaixarEstoqueUseCaseTest {

    private IEstoqueGateway estoqueGateway;
    private BaixarEstoqueUseCase baixarEstoqueUseCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(IEstoqueGateway.class);
        baixarEstoqueUseCase = new BaixarEstoqueUseCase(estoqueGateway);
    }

    @Test
    void deveChamarRemoverDoEstoqueParaCadaProduto() {
        // Arrange
        var produto1 = new ProdutoDTO("SKU123", 2, "Produto A", "123", "desc", "fab", BigDecimal.TEN, "categoria1");
        var produto2 = new ProdutoDTO("SKU456", 1, "Produto B", "456", "desc", "fab", BigDecimal.ONE, "categoria2");
        var produtos = List.of(produto1, produto2);

        var pedidoDTO = new PedidoDTO(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                new ClienteDTO("Maria", "12345678900"),
                produtos,
                new PagamentoDTO("4111111111111111"),
                StatusPedidoEnum.ABERTO,
                BigDecimal.ZERO
        );

        baixarEstoqueUseCase.baixar(pedidoDTO);

        verify(estoqueGateway).removerDoEstoque("SKU123", 2);
        verify(estoqueGateway).removerDoEstoque("SKU456", 1);
        verifyNoMoreInteractions(estoqueGateway);
    }
}