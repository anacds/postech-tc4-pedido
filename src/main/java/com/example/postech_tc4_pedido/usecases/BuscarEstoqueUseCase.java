package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IEstoqueGateway;
import org.springframework.stereotype.Service;

@Service
public class BuscarEstoqueUseCase {

    private final IEstoqueGateway estoqueGateway;

    public BuscarEstoqueUseCase(IEstoqueGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public boolean verificarDisponibilidade(PedidoDTO pedidoDTO) {
        try {
            return pedidoDTO.produtos().stream().allMatch(produto ->
                    estoqueGateway.verificarQuantidadeDisponivel(produto.sku()) >= produto.quantidade());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}