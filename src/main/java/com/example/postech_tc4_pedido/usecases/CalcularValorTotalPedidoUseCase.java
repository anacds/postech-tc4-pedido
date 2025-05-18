package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IProdutoGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalcularValorTotalPedidoUseCase {

    private final IProdutoGateway produtoGateway;

    public CalcularValorTotalPedidoUseCase(IProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public BigDecimal calcular(PedidoDTO pedidoDTO) {
        try {
            return pedidoDTO.produtos().stream()
                    .map(produto -> {
                        var produtoDTO = produtoGateway.buscarProdutoPorSku(produto.sku());
                        return produtoDTO.preco().multiply(BigDecimal.valueOf(produto.quantidade()));
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}