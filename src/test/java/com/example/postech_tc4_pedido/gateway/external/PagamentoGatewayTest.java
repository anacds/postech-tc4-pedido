package com.example.postech_tc4_pedido.gateway.external;

import com.example.postech_tc4_pedido.dto.SolicitacaoPagamentoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.client.PagamentoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class PagamentoGatewayTest {

    private PagamentoClient pagamentoClient;
    private PagamentoGateway pagamentoGateway;

    @BeforeEach
    void setUp() {
        pagamentoClient = mock(PagamentoClient.class);
        pagamentoGateway = new PagamentoGateway(pagamentoClient);
    }

    @Test
    void deveSolicitarPagamento() {
        SolicitacaoPagamentoDTO dto = new SolicitacaoPagamentoDTO("pedido123", new BigDecimal("150.00"), "4111111111111111");
        pagamentoGateway.solicitarPagamento(dto);
        verify(pagamentoClient, times(1)).solicitarPagamento(dto);
    }
}