package com.example.postech_tc4_pedido.gateway.kafka;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.usecases.ProcessarPedidoUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    private final ProcessarPedidoUseCase processarPedidoUseCase;

    public PedidoConsumer(ProcessarPedidoUseCase processarPedidoUseCase) {
        this.processarPedidoUseCase = processarPedidoUseCase;
    }

    @KafkaListener(topics = "${kafka.topic.novo-pedido}",
                    groupId = "${spring.kafka.consumer.group-id}")
    public void consumirPedido(PedidoDTO pedidoDTO) {
        processarPedidoUseCase.processar(pedidoDTO);
    }
}
