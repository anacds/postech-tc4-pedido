package com.example.postech_tc4_pedido.gateway.external;

import com.example.postech_tc4_pedido.domain.Cliente;
import com.example.postech_tc4_pedido.domain.Produto;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.ProdutoEntity;
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

    private Produto entityParaDomain(ProdutoEntity entity) {
        return new Produto(
                entity.getSku(),
                entity.getQuantidade(),
                entity.getNome(),
                entity.getCodigoDeBarras(),
                entity.getDescricao(),
                entity.getFabricante(),
                entity.getPreco(),
                entity.getCategoria()
        );
    }

    private ProdutoEntity domainParaEntity(Produto domain) {
        return new ProdutoEntity(
                domain.getSku(),
                domain.getQuantidade(),
                domain.getNome(),
                domain.getCodigoDeBarras(),
                domain.getDescricao(),
                domain.getFabricante(),
                domain.getPreco(),
                domain.getCategoria()
        );
    }
}