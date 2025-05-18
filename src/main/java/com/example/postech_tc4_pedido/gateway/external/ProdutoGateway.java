package com.example.postech_tc4_pedido.gateway.external;

import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IProdutoGateway;
import com.example.postech_tc4_pedido.gateway.external.interfaces.client.ProdutoClient;
import org.springframework.stereotype.Component;

@Component
public class ProdutoGateway implements IProdutoGateway {

    private final ProdutoClient produtoClient;

    public ProdutoGateway(ProdutoClient produtoClient) {
        this.produtoClient = produtoClient;
    }

    @Override
    public ProdutoDTO buscarProdutoPorSku(String sku) {
        return produtoClient.buscarProdutoPorSku(sku);
    }
}