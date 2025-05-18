package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import com.example.postech_tc4_pedido.dto.AtualizacaoPagamentoDTO;
import com.example.postech_tc4_pedido.exception.PedidoNaoEncontradoException;
import com.example.postech_tc4_pedido.gateway.database.interfaces.IPedidoGateway;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IEstoqueGateway;
import org.springframework.stereotype.Service;

@Service
public class AtualizarPagamentoUseCase {

    private final IPedidoGateway pedidoGateway;
    private final IEstoqueGateway estoqueGateway;

    public AtualizarPagamentoUseCase(IPedidoGateway pedidoGateway,
                                     IEstoqueGateway estoqueGateway
    ) {
        this.pedidoGateway = pedidoGateway;
        this.estoqueGateway = estoqueGateway;
    }

    public void atualizar(AtualizacaoPagamentoDTO dto) {
        var pedidoBusca = pedidoGateway.buscarPorId(dto.pedidoId());

        if (pedidoBusca.isEmpty()) {
            throw new PedidoNaoEncontradoException(dto.pedidoId());
        }

        var pedido = pedidoBusca.get();
        if (dto.statusPagamento().equals("PROCESSADO_SUCESSO")) {
            pedido.setStatus(StatusPedidoEnum.FECHADO_COM_SUCESSO);
        } else {
            pedido.getProdutos().forEach(produto ->
                    estoqueGateway.reporEstoque(produto.getSku(), produto.getQuantidade())
            );

            if(dto.statusPagamento().equals("PROCESSADO_SEM_CREDITO")) {
                pedido.setStatus(StatusPedidoEnum.FECHADO_SEM_CREDITO);
            } else {
                pedido.setStatus(StatusPedidoEnum.FECHADO_ERRO_PAGAMENTO);
            }
        }

        pedidoGateway.salvar(pedido);
    }

}
