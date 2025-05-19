package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.dto.PagamentoDTO;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IPagamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

class SolicitarPagamentoUseCaseTest {

    private IPagamentoGateway pagamentoGateway;
    private SolicitarPagamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        pagamentoGateway = mock(IPagamentoGateway.class);
        useCase = new SolicitarPagamentoUseCase(pagamentoGateway);
    }

    @Test
    void deveChamarGatewayComSolicitacaoDePagamento() {
        PedidoDTO pedidoDTO = new PedidoDTO(
                "123",
                "evento-456",
                new ClienteDTO("Joana", "12345678900"),
                List.of(new ProdutoDTO("sku1", 2, "nome", "cod", "desc", "fab", BigDecimal.TEN, "cat")),
                new PagamentoDTO("4111111111111111"),
                StatusPedidoEnum.ABERTO,
                BigDecimal.valueOf(20)
        );

        useCase.solicitar(pedidoDTO, BigDecimal.valueOf(20));

        verify(pagamentoGateway, times(1)).solicitarPagamento(any());
    }
}