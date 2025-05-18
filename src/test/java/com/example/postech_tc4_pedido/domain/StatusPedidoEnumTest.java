package com.example.postech_tc4_pedido.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusPedidoEnumTest {

    @Test
    void deveConterTodosOsStatusEsperados() {
        assertEquals(6, StatusPedidoEnum.values().length);

        assertNotNull(StatusPedidoEnum.valueOf("ABERTO"));
        assertNotNull(StatusPedidoEnum.valueOf("FECHADO_COM_SUCESSO"));
        assertNotNull(StatusPedidoEnum.valueOf("FECHADO_SEM_ESTOQUE"));
        assertNotNull(StatusPedidoEnum.valueOf("FECHADO_SEM_CREDITO"));
        assertNotNull(StatusPedidoEnum.valueOf("PENDENTE_PAGAMENTO"));
        assertNotNull(StatusPedidoEnum.valueOf("FECHADO_ERRO_PAGAMENTO"));
    }
}