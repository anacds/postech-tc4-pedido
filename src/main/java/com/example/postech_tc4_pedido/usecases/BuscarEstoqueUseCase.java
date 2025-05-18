package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.gateway.external.EstoqueClient;
import org.springframework.stereotype.Service;

@Service
public class BuscarEstoqueUseCase {

    private final EstoqueClient estoqueClient;

    public BuscarEstoqueUseCase(EstoqueClient estoqueClient) {
        this.estoqueClient = estoqueClient;
    }

    public boolean verificarDisponibilidade(PedidoDTO pedidoDTO) {
        try {
            return pedidoDTO.produtos().stream().allMatch(produto ->
                    estoqueClient.verificarQuantidadeDisponivel(produto.sku()) >= produto.quantidade());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}