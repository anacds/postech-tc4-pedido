package com.example.postech_tc4_pedido.gateway.database.interfaces;

import com.example.postech_tc4_pedido.gateway.database.entity.PedidoEntity;

import java.util.Optional;

public interface IPedidoGateway {
    Optional<PedidoEntity> buscarPorId(String id);
    PedidoEntity salvar(PedidoEntity pedido);
}