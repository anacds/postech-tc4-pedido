package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.*;
import com.example.postech_tc4_pedido.dto.AtualizacaoPagamentoDTO;
import com.example.postech_tc4_pedido.exception.PedidoNaoEncontradoException;
import com.example.postech_tc4_pedido.gateway.database.interfaces.IPedidoGateway;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IEstoqueGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarPagamentoUseCaseTest {

    private IPedidoGateway pedidoGateway;
    private IEstoqueGateway estoqueGateway;
    private AtualizarPagamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        pedidoGateway = mock(IPedidoGateway.class);
        estoqueGateway = mock(IEstoqueGateway.class);
        useCase = new AtualizarPagamentoUseCase(pedidoGateway, estoqueGateway);
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoForEncontrado() {
        var dto = new AtualizacaoPagamentoDTO("123", "PROCESSADO_SUCESSO");
        when(pedidoGateway.buscarPorId("123")).thenReturn(Optional.empty());

        assertThrows(PedidoNaoEncontradoException.class, () -> useCase.atualizar(dto));
        verify(pedidoGateway, never()).salvar(any());
    }

    @Test
    void deveAtualizarPedidoComSucesso() {
        var pedido = buildPedido();
        var dto = new AtualizacaoPagamentoDTO("123", "PROCESSADO_SUCESSO");
        when(pedidoGateway.buscarPorId("123")).thenReturn(Optional.of(pedido));

        useCase.atualizar(dto);

        assertEquals(StatusPedidoEnum.FECHADO_COM_SUCESSO, pedido.getStatus());
        verify(pedidoGateway).salvar(pedido);
        verifyNoInteractions(estoqueGateway);
    }

    @Test
    void deveReporEstoqueEAtualizarStatusQuandoPagamentoFalhar() {
        var pedido = buildPedido();
        var dto = new AtualizacaoPagamentoDTO("123", "PROCESSADO_SEM_CREDITO");
        when(pedidoGateway.buscarPorId("123")).thenReturn(Optional.of(pedido));

        useCase.atualizar(dto);

        verify(estoqueGateway).reporEstoque("sku-1", 2);
        verify(estoqueGateway).reporEstoque("sku-2", 1);
        assertEquals(StatusPedidoEnum.FECHADO_SEM_CREDITO, pedido.getStatus());
        verify(pedidoGateway).salvar(pedido);
    }

    private Pedido buildPedido() {
        return new Pedido(
                "123",
                "evt-1",
                new Cliente("Maria", "11122233344"),
                List.of(
                        new Produto("sku-1", 2, "Produto A", "123", "desc", "fab", new BigDecimal("10.00"), "cat"),
                        new Produto("sku-2", 1, "Produto B", "456", "desc2", "fab2", new BigDecimal("20.00"), "cat2")
                ),
                new Pagamento("4111111111111111"),
                StatusPedidoEnum.ABERTO,
                new BigDecimal("40.00")
        );
    }
}