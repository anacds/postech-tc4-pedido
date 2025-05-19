package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.dto.PagamentoDTO;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.example.postech_tc4_pedido.domain.StatusPedidoEnum.ABERTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CalcularValorTotalPedidoUseCaseTest {

    private IProdutoGateway produtoGateway;
    private CalcularValorTotalPedidoUseCase useCase;

    @BeforeEach
    void setUp() {
        produtoGateway = mock(IProdutoGateway.class);
        useCase = new CalcularValorTotalPedidoUseCase(produtoGateway);
    }

    @Test
    void deveCalcularValorTotalCorretamente() {
        ProdutoDTO p1 = new ProdutoDTO("sku1", 2, null, null, null, null, null, null);
        ProdutoDTO p2 = new ProdutoDTO("sku2", 1, null, null, null, null, null, null);
        List<ProdutoDTO> produtos = List.of(p1, p2);

        PedidoDTO pedido = new PedidoDTO("1", "evt1", new ClienteDTO("Ana", "123"),
                produtos, new PagamentoDTO("4111111111111111"), ABERTO, null);

        when(produtoGateway.buscarProdutoPorSku("sku1"))
                .thenReturn(new ProdutoDTO("sku1", 0, "nome", "cod", "desc", "fab", BigDecimal.TEN, "cat"));
        when(produtoGateway.buscarProdutoPorSku("sku2"))
                .thenReturn(new ProdutoDTO("sku2", 0, "nome", "cod", "desc", "fab", BigDecimal.valueOf(5), "cat"));

        BigDecimal total = useCase.calcular(pedido);

        assertEquals(BigDecimal.valueOf(25), total);
        verify(produtoGateway, times(1)).buscarProdutoPorSku("sku1");
        verify(produtoGateway, times(1)).buscarProdutoPorSku("sku2");
    }

    @Test
    void deveRetornarZeroQuandoGatewayLancaExcecao() {
        ProdutoDTO p = new ProdutoDTO("sku1", 2, null, null, null, null, null, null);
        PedidoDTO pedido = new PedidoDTO("1", "evt1", new ClienteDTO("Ana", "123"),
                List.of(p), new PagamentoDTO("4111111111111111"), ABERTO, null);

        when(produtoGateway.buscarProdutoPorSku("sku1")).thenThrow(new RuntimeException("Erro"));

        BigDecimal total = useCase.calcular(pedido);

        assertEquals(BigDecimal.ZERO, total);
        verify(produtoGateway, times(1)).buscarProdutoPorSku("sku1");
    }
}