package com.example.postech_tc4_pedido.gateway.database.interfaces;

import com.example.postech_tc4_pedido.domain.Pedido;
import com.example.postech_tc4_pedido.gateway.database.entity.PedidoEntity;

import java.util.Optional;

public interface IPedidoGateway {
    Optional<Pedido> buscarPorId(String id);
    Pedido salvar(Pedido pedido);
}