package com.example.postech_tc4_pedido.gateway.external;

import com.example.postech_tc4_pedido.dto.SolicitacaoPagamentoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IPagamentoGateway;
import com.example.postech_tc4_pedido.gateway.external.interfaces.client.PagamentoClient;
import org.springframework.stereotype.Component;

@Component
public class PagamentoGateway implements IPagamentoGateway {

    private final PagamentoClient pagamentoClient;

    public PagamentoGateway(PagamentoClient pagamentoClient) {
        this.pagamentoClient = pagamentoClient;
    }

    @Override
    public void solicitarPagamento(SolicitacaoPagamentoDTO solicitacaoPagamentoDTO) {
        pagamentoClient.solicitarPagamento(solicitacaoPagamentoDTO);
    }
}