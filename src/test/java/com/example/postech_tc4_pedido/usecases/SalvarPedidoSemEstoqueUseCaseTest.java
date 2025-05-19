package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.Cliente;
import com.example.postech_tc4_pedido.domain.Pedido;
import com.example.postech_tc4_pedido.domain.Produto;
import com.example.postech_tc4_pedido.dto.PagamentoDTO;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import com.example.postech_tc4_pedido.gateway.database.interfaces.IPedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.example.postech_tc4_pedido.domain.StatusPedidoEnum.ABERTO;
import static org.mockito.Mockito.*;

class SalvarPedidoSemEstoqueUseCaseTest {

    private IPedidoGateway pedidoGateway;
    private SalvarPedidoSemEstoqueUseCase useCase;

    @BeforeEach
    void setUp() {
        pedidoGateway = mock(IPedidoGateway.class);
        useCase = new SalvarPedidoSemEstoqueUseCase(pedidoGateway);
    }

    @Test
    void deveSalvarPedidoComStatusSemEstoque() {
        PedidoDTO pedidoDTO = new PedidoDTO(
                "123",
                "evt-001",
                new com.example.postech_tc4_pedido.dto.ClienteDTO("João", "12345678900"),
                List.of(new ProdutoDTO("sku1", 1, "nome", "codigo", "desc", "fab", BigDecimal.TEN, "cat")),
                new PagamentoDTO("4111111111111111"),
                ABERTO,
                null
        );

        Cliente cliente = new Cliente("João", "12345678900");
        List<Produto> produtos = List.of(
                new Produto("sku1", 1, "nome", "codigo", "desc", "fab", BigDecimal.TEN, "cat")
        );

        useCase.salvar(pedidoDTO, cliente, produtos, BigDecimal.TEN);

        verify(pedidoGateway, times(1)).salvar(any(Pedido.class));
    }
}