package com.example.postech_tc4_pedido.gateway.external.interfaces.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "estoqueClient", url = "${estoque.service.url}")
public interface EstoqueClient {

    @GetMapping("/api/verifica/quantidade-disponivel/{sku}")
    int verificarQuantidadeDisponivel(@PathVariable("sku") String sku);


    @PutMapping("/api/remove-produtos-no-estoque")
    void removerDoEstoque(@RequestParam("sku") String sku,
                          @RequestParam("quantidade") int quantidade);


    @PutMapping("/admin/adiciona-produtos-no-estoque")
    void reporEstoque(@RequestParam("sku") String sku,
                      @RequestParam("quantidade") int quantidade);
}
