package com.example.postech_tc4_pedido.gateway.external.interfaces;

import com.example.postech_tc4_pedido.dto.ClienteDTO;

public interface IClienteGateway {
    ClienteDTO buscarClientePorCpf(String cpf);
}