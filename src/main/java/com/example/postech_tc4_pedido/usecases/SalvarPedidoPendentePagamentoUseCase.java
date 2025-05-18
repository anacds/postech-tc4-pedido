package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.PagamentoEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.PedidoEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.ProdutoEntity;
import com.example.postech_tc4_pedido.gateway.database.interfaces.IPedidoGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SalvarPedidoPendentePagamentoUseCase {

    private final IPedidoGateway pedidoGateway;

    public SalvarPedidoPendentePagamentoUseCase(IPedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public void salvar(PedidoDTO pedidoDTO, ClienteEntity cliente, List<ProdutoEntity> produtos, BigDecimal valorTotal) {
        try {
            var pagamentoEntity = new PagamentoEntity(pedidoDTO.dadosPagamento().numeroCartao());
            var pedidoEntity = new PedidoEntity(
                    pedidoDTO.id(),
                    pedidoDTO.idEvento(),
                    cliente,
                    produtos,
                    pagamentoEntity,
                    StatusPedidoEnum.PENDENTE_PAGAMENTO,
                    valorTotal
            );
            pedidoGateway.salvar(pedidoEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}