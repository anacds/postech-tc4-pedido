package com.example.postech_tc4_pedido.gateway.external.interfaces;

import com.example.postech_tc4_pedido.dto.ClienteDTO;
import org.springframework.web.bind.annotation.PathVariable;

public interface IClienteClient {
    ClienteDTO buscarClientePorCpf(@PathVariable("cpf") String cpf);
}
