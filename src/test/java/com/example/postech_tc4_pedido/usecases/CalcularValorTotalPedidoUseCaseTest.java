package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.dto.PagamentoDTO;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CalcularValorTotalPedidoUseCaseTest {

    private IProdutoGateway produtoGateway;
    private CalcularValorTotalPedidoUseCase useCase;

    @BeforeEach
    void setUp() {
        produtoGateway = mock(IProdutoGateway.class);
        useCase = new CalcularValorTotalPedidoUseCase(produtoGateway);
    }

    @Test
    void deveCalcularValorTotalCorretamente() {
        var produto1 = new ProdutoDTO("SKU1", 2, null, null, null, null, BigDecimal.TEN, null);
        var produto2 = new ProdutoDTO("SKU2", 1, null, null, null, null, BigDecimal.valueOf(5), null);
        var pedido = new PedidoDTO("1", "evt", new ClienteDTO("Ana", "123"), List.of(produto1, produto2), new PagamentoDTO("1111"), StatusPedidoEnum.ABERTO, null);

        when(produtoGateway.buscarProdutoPorSku("SKU1")).thenReturn(produto1);
        when(produtoGateway.buscarProdutoPorSku("SKU2")).thenReturn(produto2);

        BigDecimal total = useCase.calcular(pedido);

        assertEquals(BigDecimal.valueOf(25), total);
    }

    @Test
    void deveRetornarZeroEmCasoDeErro() {
        var produto = new ProdutoDTO("SKU_ERR", 1, null, null, null, null, BigDecimal.ONE, null);
        var pedido = new PedidoDTO("1", "evt", new ClienteDTO("Ana", "123"), List.of(produto), new PagamentoDTO("1111"), StatusPedidoEnum.ABERTO, null);

        when(produtoGateway.buscarProdutoPorSku("SKU_ERR")).thenThrow(new RuntimeException("Erro"));

        BigDecimal total = useCase.calcular(pedido);

        assertEquals(BigDecimal.ZERO, total);
    }
}