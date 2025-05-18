package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.SolicitacaoPagamentoDTO;
import com.example.postech_tc4_pedido.gateway.external.PagamentoClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SolicitarPagamentoUseCase {
    private final PagamentoClient pagamentoClient;

    public SolicitarPagamentoUseCase(PagamentoClient pagamentoClient) {
        this.pagamentoClient = pagamentoClient;
    }

    public void solicitar(PedidoDTO pedidoDTO, BigDecimal valorTotal){
        try {
            var solicitacao = new SolicitacaoPagamentoDTO(
                    pedidoDTO.id(),
                    valorTotal,
                    pedidoDTO.pagamento().numeroCartao()
            );

            pagamentoClient.solicitarPagamento(solicitacao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
