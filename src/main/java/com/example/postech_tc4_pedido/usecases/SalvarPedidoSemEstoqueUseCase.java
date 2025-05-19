package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.*;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.gateway.database.interfaces.IPedidoGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SalvarPedidoSemEstoqueUseCase {

    private final IPedidoGateway pedidoGateway;

    public SalvarPedidoSemEstoqueUseCase(IPedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public void salvar(PedidoDTO pedidoDTO, Cliente cliente, List<Produto> produtos, BigDecimal valorTotal) {
        try {

            Pagamento pagamento = new Pagamento(
                    pedidoDTO.dadosPagamento().numeroCartao()
            );

            var pedido = new Pedido(
                    pedidoDTO.id(),
                    pedidoDTO.idEvento(),
                    cliente,
                    produtos,
                    pagamento,
                    StatusPedidoEnum.FECHADO_SEM_ESTOQUE,
                    valorTotal
            );
            pedidoGateway.salvar(pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}