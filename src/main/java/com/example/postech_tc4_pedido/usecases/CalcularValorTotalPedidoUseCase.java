package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.gateway.external.ProdutoClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalcularValorTotalPedidoUseCase {

    private final ProdutoClient produtoClient;

    public CalcularValorTotalPedidoUseCase(ProdutoClient produtoClient) {
        this.produtoClient = produtoClient;
    }

    public BigDecimal calcular(PedidoDTO pedidoDTO) {
        try {
            return pedidoDTO.produtos().stream()
                    .map(produto -> {
                        var produtoDTO = produtoClient.buscarProdutoPorSku(produto.sku());
                        return produtoDTO.preco().multiply(BigDecimal.valueOf(produto.quantidade()));
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}