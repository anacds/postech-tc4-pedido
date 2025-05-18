package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.SolicitacaoPagamentoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IPagamentoGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SolicitarPagamentoUseCase {
    private final IPagamentoGateway pagamentoGateway;

    public SolicitarPagamentoUseCase(IPagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    public void solicitar(PedidoDTO pedidoDTO, BigDecimal valorTotal){
        try {
            var solicitacao = new SolicitacaoPagamentoDTO(
                    pedidoDTO.id(),
                    valorTotal,
                    pedidoDTO.dadosPagamento().numeroCartao()
            );

            pagamentoGateway.solicitarPagamento(solicitacao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
