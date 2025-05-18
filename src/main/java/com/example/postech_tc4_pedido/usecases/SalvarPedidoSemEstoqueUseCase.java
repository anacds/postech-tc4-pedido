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
public class SalvarPedidoSemEstoqueUseCase {

    private final IPedidoGateway pedidoGateway;

    public SalvarPedidoSemEstoqueUseCase(IPedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public void salvar(PedidoDTO pedidoDTO, ClienteEntity cliente, List<ProdutoEntity> produtos, BigDecimal valorTotal) {
        try {

            PagamentoEntity pagamentoEntity = new PagamentoEntity(
                    pedidoDTO.dadosPagamento().numeroCartao()
            );

            var pedido = new PedidoEntity(
                    pedidoDTO.id(),
                    pedidoDTO.idEvento(),
                    cliente,
                    produtos,
                    pagamentoEntity,
                    StatusPedidoEnum.FECHADO_SEM_ESTOQUE,
                    valorTotal
            );
            pedidoGateway.salvar(pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}