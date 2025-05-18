package com.example.postech_tc4_pedido.gateway.external.interfaces;

import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import org.springframework.web.bind.annotation.PathVariable;

public interface IProdutoClient {

    ProdutoDTO buscarProdutoPorSku(@PathVariable("sku") String sku);
}
