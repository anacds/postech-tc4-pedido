package com.example.postech_tc4_pedido.gateway.external.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IEstoqueClient {

    int verificarQuantidadeDisponivel(@PathVariable("sku") String sku);
    void removerDoEstoque(@RequestParam("sku") String sku,
                          @RequestParam("quantidade") int quantidade);
    void reporEstoque(@RequestParam("sku") String sku,
                      @RequestParam("quantidade") int quantidade);
}
