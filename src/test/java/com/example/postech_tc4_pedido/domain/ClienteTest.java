package com.example.postech_tc4_pedido.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    @Test
    void deveCriarClienteComNomeECpf() {
        Cliente cliente = new Cliente("Ana", "12345678900");

        assertEquals("Ana", cliente.getNome());
        assertEquals("12345678900", cliente.getCpf());
    }
}