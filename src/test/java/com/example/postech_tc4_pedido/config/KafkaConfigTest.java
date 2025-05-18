package com.example.postech_tc4_pedido.config;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import org.apache.kafka.clients.consumer.Consumer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(KafkaConfig.class)
class KafkaConfigTest {

    private final KafkaConfig kafkaConfig = new KafkaConfig();

    @Test
    @Disabled
    void deveCriarBeanConsumerFactory() {
        ConsumerFactory<String, PedidoDTO> consumerFactory = kafkaConfig.pedidoConsumerFactory();
        assertNotNull(consumerFactory);

        Consumer<String, PedidoDTO> consumer = consumerFactory.createConsumer();
        assertNotNull(consumer);
    }

    @Test
    void deveCriarBeanListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PedidoDTO> factory = kafkaConfig.pedidoKafkaListenerFactory();
        assertNotNull(factory);
        assertNotNull(factory.getConsumerFactory());
    }
}