package com.example.postech_tc4_pedido.gateway.external;

import com.example.postech_tc4_pedido.domain.Cliente;
import com.example.postech_tc4_pedido.domain.Pedido;
import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.PedidoEntity;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IClienteGateway;
import com.example.postech_tc4_pedido.gateway.external.interfaces.client.ClienteClient;
import org.springframework.stereotype.Component;

@Component
public class ClienteGateway implements IClienteGateway {

    private final ClienteClient clienteClient;

    public ClienteGateway(ClienteClient clienteClient) {
        this.clienteClient = clienteClient;
    }

    @Override
    public ClienteDTO buscarClientePorCpf(String cpf) {
        return clienteClient.buscarClientePorCpf(cpf);
    }

    private Cliente entityParaDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getNome(),
                entity.getCpf()
        );
    }

    private ClienteEntity domainParaEntity(Cliente domain) {
        return new ClienteEntity(
                domain.getNome(),
                domain.getCpf()
        );
    }

}