package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity;
import com.example.postech_tc4_pedido.gateway.external.ClienteClient;
import org.springframework.stereotype.Service;

@Service
public class BuscarClienteUseCase {

    private final ClienteClient clienteClient;

    public BuscarClienteUseCase(ClienteClient clienteClient) {
        this.clienteClient = clienteClient;
    }

    public ClienteEntity buscar(String cpf) {
        try {
            var clienteDTO = clienteClient.buscarClientePorCpf(cpf);
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