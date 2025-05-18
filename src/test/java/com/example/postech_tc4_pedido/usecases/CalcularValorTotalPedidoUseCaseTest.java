package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.external.ProdutoClient;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalcularValorTotalPedidoUseCaseTest {

    @Test
    void deveCalcularValorTotalCorretamente() {
        ProdutoClient produtoClient = mock(ProdutoClient.class);
        CalcularValorTotalPedidoUseCase useCase = new CalcularValorTotalPedidoUseCase(produtoClient);

        String sku1 = "sku1";
        String sku2 = "sku2";

        when(produtoClient.buscarProdutoPorSku(sku1)).thenReturn(new ProdutoDTO(
                sku1, 0, "nome1", "cb1", "desc1", "fab1", new BigDecimal("10.00"), "cat1"
        ));
        when(produtoClient.buscarProdutoPorSku(sku2)).thenReturn(new ProdutoDTO(
                sku2, 0, "nome2", "cb2", "desc2", "fab2", new BigDecimal("10.00"), "cat2"
        ));

        PedidoDTO pedidoDTO = new PedidoDTO(
                "id1",
                "evento1",
                new com.example.postech_tc4_pedido.dto.ClienteDTO("cliente"),
                List.of(
                        new ProdutoDTO(sku1, 2, null, null, null, null, new BigDecimal("10.00"), null),
                        new ProdutoDTO(sku2, 1, null, null, null, null, new BigDecimal("10.00"), null)
                ),
                new com.example.postech_tc4_pedido.dto.PagamentoDTO("1234"),
                com.example.postech_tc4_pedido.domain.StatusPedidoEnum.ABERTO,
                null
        );

        BigDecimal total = useCase.calcular(pedidoDTO);
        assertEquals(new BigDecimal("30.00"), total);
    }
}