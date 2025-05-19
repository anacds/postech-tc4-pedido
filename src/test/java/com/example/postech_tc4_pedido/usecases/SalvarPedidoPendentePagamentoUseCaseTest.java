package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.Cliente;
import com.example.postech_tc4_pedido.domain.Pagamento;
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

class SalvarPedidoPendentePagamentoUseCaseTest {

    private IPedidoGateway pedidoGateway;
    private SalvarPedidoPendentePagamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        pedidoGateway = mock(IPedidoGateway.class);
        useCase = new SalvarPedidoPendentePagamentoUseCase(pedidoGateway);
    }

    @Test
    void deveSalvarPedidoPendentePagamentoComSucesso() {
        PedidoDTO pedidoDTO = new PedidoDTO(
                "pedido123",
                "evento456",
                new com.example.postech_tc4_pedido.dto.ClienteDTO("Ana", "99999999999"),
                List.of(new ProdutoDTO("sku1", 2, "nome", "cod", "desc", "fab", BigDecimal.TEN, "cat")),
                new PagamentoDTO("4111111111111111"),
                ABERTO,
                null
        );

        Cliente cliente = new Cliente("Ana", "99999999999");
        List<Produto> produtos = List.of(
                new Produto("sku1", 2, "nome", "cod", "desc", "fab", BigDecimal.TEN, "cat")
        );

        useCase.salvar(pedidoDTO, cliente, produtos, BigDecimal.valueOf(20));

        verify(pedidoGateway, times(1)).salvar(any(Pedido.class));
    }
}