package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.database.entity.ProdutoEntity;
import com.example.postech_tc4_pedido.gateway.external.ProdutoClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarProdutosUseCase {

    private final ProdutoClient produtoClient;

    public BuscarProdutosUseCase(ProdutoClient produtoClient) {
        this.produtoClient = produtoClient;
    }

    public List<ProdutoEntity> buscarPorSkus(List<ProdutoDTO> produtosDTO) {
        try {
            return produtosDTO.stream()
                    .map(produtoDTO -> {
                        var p = produtoClient.buscarProdutoPorSku(produtoDTO.sku());
                        return new ProdutoEntity(
                                p.sku(),
                                produtoDTO.quantidade(),
                                p.nome(),
                                p.codigoBarras(),
                                p.descricao(),
                                p.fabricante(),
                                p.preco(),
                                p.categoria()
                        );
                    }).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}