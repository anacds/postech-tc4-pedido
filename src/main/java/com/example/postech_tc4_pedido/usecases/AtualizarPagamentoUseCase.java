package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import com.example.postech_tc4_pedido.dto.AtualizacaoPagamentoDTO;
import com.example.postech_tc4_pedido.gateway.database.repository.PedidoRepository;
import com.example.postech_tc4_pedido.gateway.external.EstoqueClient;
import org.springframework.stereotype.Service;

@Service
public class AtualizarPagamentoUseCase {

    private final PedidoRepository pedidoRepository;
    private final EstoqueClient estoqueClient;

    public AtualizarPagamentoUseCase(PedidoRepository pedidoRepository,
                                     EstoqueClient estoqueClient
    ) {
        this.pedidoRepository = pedidoRepository;
        this.estoqueClient = estoqueClient;
    }

    public void atualizar(AtualizacaoPagamentoDTO dto) {
        var pedidoBusca = pedidoRepository.findById(dto.pedidoId());

        if (pedidoBusca.isEmpty()) {
            System.err.println("Pedido não encontrado: " + dto.pedidoId());
            return;
        }

        var pedido = pedidoBusca.get();
        if (dto.statusPagamento().equals("PROCESSADO_SUCESSO")) {
            pedido.setStatus(StatusPedidoEnum.FECHADO_COM_SUCESSO);
        } else {
            pedido.getProdutos().forEach(produto ->
                    estoqueClient.reporEstoque(produto.getSku(), produto.getQuantidade())
            );

            if(dto.statusPagamento().equals("PROCESSADO_SEM_CREDITO")) {
                pedido.setStatus(StatusPedidoEnum.FECHADO_SEM_CREDITO);
            } else {
                pedido.setStatus(StatusPedidoEnum.FECHADO_ERRO_PAGAMENTO);
            }
        }

        pedidoRepository.save(pedido);
    }

}
