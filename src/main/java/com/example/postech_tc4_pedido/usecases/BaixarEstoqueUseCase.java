package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.gateway.external.EstoqueClient;
import org.springframework.stereotype.Service;

@Service
public class BaixarEstoqueUseCase {

    private final EstoqueClient estoqueClient;

    public BaixarEstoqueUseCase(EstoqueClient estoqueClient) {
        this.estoqueClient = estoqueClient;
    }

    public void baixar(PedidoDTO pedidoDTO) {
        try {
            pedidoDTO.produtos().forEach(produto ->
                    estoqueClient.removerDoEstoque(produto.sku(), produto.quantidade())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}