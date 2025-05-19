package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.domain.Cliente;
import com.example.postech_tc4_pedido.domain.Produto;
import com.example.postech_tc4_pedido.dto.ClienteDTO;
import com.example.postech_tc4_pedido.dto.PagamentoDTO;
import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.dto.ProdutoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;

class ProcessarPedidoUseCaseTest {

    private BuscarProdutosUseCase buscarProdutosUseCase;
    private BuscarClienteUseCase buscarClienteUseCase;
    private BuscarEstoqueUseCase buscarEstoqueUseCase;
    private BaixarEstoqueUseCase baixarEstoqueUseCase;
    private SalvarPedidoSemEstoqueUseCase salvarPedidoSemEstoqueUseCase;
    private SalvarPedidoPendentePagamentoUseCase salvarPedidoPendentePagamentoUseCase;
    private CalcularValorTotalPedidoUseCase calcularValorTotalPedidoUseCase;
    private SolicitarPagamentoUseCase solicitarPagamentoUseCase;

    private ProcessarPedidoUseCase useCase;

    @BeforeEach
    void setUp() {
        buscarProdutosUseCase = mock(BuscarProdutosUseCase.class);
        buscarClienteUseCase = mock(BuscarClienteUseCase.class);
        buscarEstoqueUseCase = mock(BuscarEstoqueUseCase.class);
        baixarEstoqueUseCase = mock(BaixarEstoqueUseCase.class);
        salvarPedidoSemEstoqueUseCase = mock(SalvarPedidoSemEstoqueUseCase.class);
        salvarPedidoPendentePagamentoUseCase = mock(SalvarPedidoPendentePagamentoUseCase.class);
        calcularValorTotalPedidoUseCase = mock(CalcularValorTotalPedidoUseCase.class);
        solicitarPagamentoUseCase = mock(SolicitarPagamentoUseCase.class);

        useCase = new ProcessarPedidoUseCase(
                buscarProdutosUseCase,
                buscarClienteUseCase,
                buscarEstoqueUseCase,
                baixarEstoqueUseCase,
                salvarPedidoSemEstoqueUseCase,
                salvarPedidoPendentePagamentoUseCase,
                calcularValorTotalPedidoUseCase,
                solicitarPagamentoUseCase
        );
    }

    @Test
    void deveProcessarPedidoQuandoHaEstoque() {
        var produtoDTO = new ProdutoDTO("sku1", 2, null, null, null, null, BigDecimal.TEN, null);
        var pedidoDTO = new PedidoDTO("123", "evt1",
                new ClienteDTO("Ana", "111"),
                List.of(produtoDTO),
                new PagamentoDTO("4111111111111111"),
                null,
                null);

        var produto = new Produto("sku1", 2, null, null, null, null, BigDecimal.TEN, null);
        var cliente = new Cliente("Ana", "111");
        var valorTotal = BigDecimal.valueOf(20);

        when(buscarProdutosUseCase.buscarPorSkus(pedidoDTO.produtos())).thenReturn(List.of(produto));
        when(buscarClienteUseCase.buscar("111")).thenReturn(cliente);
        when(buscarEstoqueUseCase.verificarDisponibilidade(pedidoDTO)).thenReturn(true);
        when(calcularValorTotalPedidoUseCase.calcular(pedidoDTO)).thenReturn(valorTotal);

        useCase.processar(pedidoDTO);

        verify(baixarEstoqueUseCase).baixar(pedidoDTO);
        //verify(solicitarPagamentoUseCase).solicitar(eq(pedidoDTO), eq(valorTotal));
        verify(salvarPedidoPendentePagamentoUseCase).salvar(eq(pedidoDTO), refEq(cliente), refEq(List.of(produto)), eq(valorTotal));
    }

    @Test
    void deveSalvarPedidoSemEstoqueQuandoNaoHaEstoque() {
        var produtoDTO = new ProdutoDTO("sku1", 2, null, null, null, null, BigDecimal.TEN, null);
        var pedidoDTO = new PedidoDTO("123", "evt1",
                new ClienteDTO("Ana", "111"),
                List.of(produtoDTO),
                new PagamentoDTO("4111111111111111"),
                null,
                null);

        var produto = new Produto("sku1", 2, null, null, null, null, BigDecimal.TEN, null);
        var cliente = new Cliente("Ana", "111");
        var valorTotal = BigDecimal.valueOf(20);

        when(buscarProdutosUseCase.buscarPorSkus(pedidoDTO.produtos())).thenReturn(List.of(produto));
        when(buscarClienteUseCase.buscar("111")).thenReturn(cliente);
        when(buscarEstoqueUseCase.verificarDisponibilidade(pedidoDTO)).thenReturn(false);
        when(calcularValorTotalPedidoUseCase.calcular(pedidoDTO)).thenReturn(valorTotal);

        useCase.processar(pedidoDTO);

        ArgumentCaptor<Cliente> clienteCaptor = ArgumentCaptor.forClass(Cliente.class);
        ArgumentCaptor<List<Produto>> produtosCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<BigDecimal> valorCaptor = ArgumentCaptor.forClass(BigDecimal.class);

        verify(salvarPedidoSemEstoqueUseCase).salvar(eq(pedidoDTO), clienteCaptor.capture(), produtosCaptor.capture(), valorCaptor.capture());

        Cliente capturedCliente = clienteCaptor.getValue();
        List<Produto> capturedProdutos = produtosCaptor.getValue();
        BigDecimal capturedValor = valorCaptor.getValue();

        assertEquals("Ana", capturedCliente.getNome());
        assertEquals("111", capturedCliente.getCpf());
        assertEquals(1, capturedProdutos.size());
        assertEquals("sku1", capturedProdutos.get(0).getSku());
        assertEquals(2, capturedProdutos.get(0).getQuantidade());
        assertEquals(BigDecimal.valueOf(20), capturedValor);
    }
}