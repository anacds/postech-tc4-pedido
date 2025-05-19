package com.example.postech_tc4_pedido.gateway.external;

import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.client.ClienteClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClienteGatewayTest {

    private ClienteClient clienteClient;
    private ClienteGateway clienteGateway;

    @BeforeEach
    void setUp() {
        clienteClient = mock(ClienteClient.class);
        clienteGateway = new ClienteGateway(clienteClient);
    }

    @Test
    void deveBuscarClientePorCpf() {
        String cpf = "12345678900";
        ClienteDTO clienteDTO = new ClienteDTO("Ana", cpf);
        when(clienteClient.buscarClientePorCpf(cpf)).thenReturn(clienteDTO);

        ClienteDTO resultado = clienteGateway.buscarClientePorCpf(cpf);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.nome());
        assertEquals(cpf, resultado.cpf());
        verify(clienteClient, times(1)).buscarClientePorCpf(cpf);
    }
}