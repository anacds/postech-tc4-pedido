package com.example.postech_tc4_pedido.gateway.database;

import com.example.postech_tc4_pedido.gateway.database.entity.PedidoEntity;
import com.example.postech_tc4_pedido.gateway.database.interfaces.IPedidoGateway;
import com.example.postech_tc4_pedido.gateway.database.repository.PedidoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PedidoGateway implements IPedidoGateway {

    private final PedidoRepository pedidoRepository;

    public PedidoGateway(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Optional<PedidoEntity> buscarPorId(String id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public PedidoEntity salvar(PedidoEntity pedido) {
        return pedidoRepository.save(pedido);
    }
}