package com.example.postech_tc4_pedido.gateway.kafka;

import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.dto.PagamentoDTO;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import com.example.postech_tc4_pedido.usecases.ProcessarPedidoUseCase;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

class PedidoConsumerTest {

    @Test
    void deveConsumirPedidoECaminharParaProcessamento() {
        // Arrange
        ProcessarPedidoUseCase useCase = mock(ProcessarPedidoUseCase.class);
        PedidoConsumer consumer = new PedidoConsumer(useCase);

        PedidoDTO pedido = new PedidoDTO(
                "id123",
                "evento123",
                new ClienteDTO("Maria", "12345678900"),
                List.of(new ProdutoDTO("sku1", 2, null, null, null, null, new BigDecimal("10.00"), null)),
                new PagamentoDTO("4111111111111111"),
                StatusPedidoEnum.ABERTO,
                new BigDecimal("20.00")
        );

        consumer.consumirPedido(pedido);
        verify(useCase, times(1)).processar(pedido);
    }
}