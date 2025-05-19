package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.Cliente;
import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BuscarClienteUseCaseTest {

    private IClienteGateway clienteGateway;
    private BuscarClienteUseCase useCase;

    @BeforeEach
    void setUp() {
        clienteGateway = mock(IClienteGateway.class);
        useCase = new BuscarClienteUseCase(clienteGateway);
    }

    @Test
    void deveRetornarClienteQuandoCpfExistir() {
        String cpf = "12345678900";
        ClienteDTO clienteDTO = new ClienteDTO("Maria", cpf);
        when(clienteGateway.buscarClientePorCpf(cpf)).thenReturn(clienteDTO);

        Cliente cliente = useCase.buscar(cpf);

        assertNotNull(cliente);
        assertEquals("Maria", cliente.getNome());
        assertEquals(cpf, cliente.getCpf());
        verify(clienteGateway).buscarClientePorCpf(cpf);
    }

    @Test
    void deveRetornarNullQuandoOcorreExcecao() {
        String cpf = "12345678900";
        when(clienteGateway.buscarClientePorCpf(cpf)).thenThrow(new RuntimeException("Erro"));

        Cliente cliente = useCase.buscar(cpf);

        assertNull(cliente);
        verify(clienteGateway).buscarClientePorCpf(cpf);
    }
}