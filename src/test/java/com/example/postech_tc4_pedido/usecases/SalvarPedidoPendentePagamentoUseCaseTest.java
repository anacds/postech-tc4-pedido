package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.dto.PagamentoDTO;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.ProdutoEntity;
import com.example.postech_tc4_pedido.gateway.database.interfaces.IPedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

class SalvarPedidoPendentePagamentoUseCaseTest {

    private IPedidoGateway pedidoGateway;
    private SalvarPedidoPendentePagamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        pedidoGateway = mock(IPedidoGateway.class);
        useCase = new SalvarPedidoPendentePagamentoUseCase(pedidoGateway);
    }

    @Test
    void deveSalvarPedidoPendentePagamento() {
        var cliente = new ClienteEntity("Maria", "39335454800");
        var produto1 = new ProdutoEntity("SKU-001", 2, null, null, null, null, BigDecimal.TEN, null);
        var produto2 = new ProdutoEntity("SKU-002", 1, null, null, null, null, BigDecimal.TEN, null);
        var produtos = List.of(produto1, produto2);

        var dto = new PedidoDTO(
                "1",
                "evt1",
                new ClienteDTO("Maria", "39335454800"),
                List.of(
                        new ProdutoDTO("SKU-001", 2, null, null, null, null, BigDecimal.TEN, null),
                        new ProdutoDTO("SKU-002", 1, null, null, null, null, BigDecimal.TEN, null)
                ),
                new PagamentoDTO("1234123412341234"),
                StatusPedidoEnum.ABERTO,
                null
        );

        useCase.salvar(dto, cliente, produtos, BigDecimal.valueOf(30));

        verify(pedidoGateway, times(1)).salvar(any());
    }

    @Test
    void naoDeveLancarExcecaoAoSalvarMesmoComErro() {
        var cliente = new ClienteEntity("Maria", "39335454800");
        var produto = new ProdutoEntity("SKU-001", 1, null, null, null, null, BigDecimal.TEN, null);
        var produtos = List.of(produto);

        var dto = new PedidoDTO(
                "1",
                "evt1",
                new ClienteDTO("Maria", "39335454800"),
                List.of(new ProdutoDTO("SKU-001", 1, null, null, null, null, BigDecimal.TEN, null)),
                new PagamentoDTO("1234123412341234"),
                StatusPedidoEnum.ABERTO,
                null
        );

        doThrow(new RuntimeException("erro")).when(pedidoGateway).salvar(any());

        useCase.salvar(dto, cliente, produtos, BigDecimal.TEN);
    }
}