package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IClienteGateway;
import org.springframework.stereotype.Service;

@Service
public class BuscarClienteUseCase {

    private final IClienteGateway clienteGateway;

    public BuscarClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public ClienteEntity buscar(String cpf) {
        try {
            var clienteDTO = clienteGateway.buscarClientePorCpf(cpf);
            return new ClienteEntity(
                    clienteDTO.nome(),
                    clienteDTO.cpf()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}