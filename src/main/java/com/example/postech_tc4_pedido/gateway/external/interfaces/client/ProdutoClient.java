package com.example.postech_tc4_pedido.gateway.external.interfaces.client;

import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produtoClient", url = "${produto.service.url}")
public interface ProdutoClient {

    @GetMapping("/buscar/{sku}")
    ProdutoDTO buscarProdutoPorSku(@PathVariable("sku") String sku);
}