package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.PagamentoEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.PedidoEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.ProdutoEntity;
import com.example.postech_tc4_pedido.gateway.database.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SalvarPedidoSemEstoqueUseCase {

    private final PedidoRepository pedidoRepository;

    public SalvarPedidoSemEstoqueUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void salvar(PedidoDTO pedidoDTO, ClienteEntity cliente, List<ProdutoEntity> produtos, BigDecimal valorTotal) {
        try {

            PagamentoEntity pagamentoEntity = new PagamentoEntity(
                    pedidoDTO.pagamento().numeroCartao()
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
            pedidoRepository.save(pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}