package com.example.postech_tc4_pedido.gateway.external.interfaces.client;

import com.example.postech_tc4_pedido.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clienteClient", url = "${cliente.service.url}")
public interface ClienteClient {
    @GetMapping("/clientes/cpf/{cpf}")
    ClienteDTO buscarClientePorCpf(@PathVariable("cpf") String cpf);
}