package com.example.postech_tc4_pedido.domain;

public enum StatusPedidoEnum {
    ABERTO,
    FECHADO_COM_SUCESSO,
    FECHADO_SEM_ESTOQUE,
    FECHADO_SEM_CREDITO,
    PENDENTE_PAGAMENTO,
    FECHADO_ERRO_PAGAMENTO
}
