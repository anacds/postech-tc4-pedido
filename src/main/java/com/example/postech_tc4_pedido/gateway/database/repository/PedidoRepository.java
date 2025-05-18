package com.example.postech_tc4_pedido.gateway.database.repository;

import com.example.postech_tc4_pedido.gateway.database.entity.PedidoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<PedidoEntity, String> {
}
