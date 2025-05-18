package com.example.postech_tc4_pedido.usecases;

import com.example.postech_tc4_pedido.dto.PedidoDTO;
import com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.ProdutoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessarPedidoUseCase {

    private final BuscarProdutosUseCase buscarProdutosUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;
    private final BuscarEstoqueUseCase buscarEstoqueUseCase;
    private final BaixarEstoqueUseCase baixarEstoqueUseCase;
    private final SalvarPedidoSemEstoqueUseCase salvarPedidoSemEstoqueUseCase;
    private final SalvarPedidoPendentePagamentoUseCase salvarPedidoPendentePagamentoUseCase;
    private final CalcularValorTotalPedidoUseCase calcularValorTotalPedidoUseCase;
    private final SolicitarPagamentoUseCase solicitarPagamentoUseCase;

    public ProcessarPedidoUseCase(
            BuscarProdutosUseCase buscarProdutosUseCase,
            BuscarClienteUseCase buscarClienteUseCase,
            BuscarEstoqueUseCase buscarEstoqueUseCase,
            BaixarEstoqueUseCase baixarEstoqueUseCase,
            SalvarPedidoSemEstoqueUseCase salvarPedidoSemEstoqueUseCase,
            SalvarPedidoPendentePagamentoUseCase salvarPedidoPendentePagamentoUseCase,
            CalcularValorTotalPedidoUseCase calcularValorTotalPedidoUseCase,
            SolicitarPagamentoUseCase solicitarPagamentoUseCase) {
        this.buscarProdutosUseCase = buscarProdutosUseCase;
        this.buscarClienteUseCase = buscarClienteUseCase;
        this.buscarEstoqueUseCase = buscarEstoqueUseCase;
        this.baixarEstoqueUseCase = baixarEstoqueUseCase;
        this.salvarPedidoSemEstoqueUseCase = salvarPedidoSemEstoqueUseCase;
        this.salvarPedidoPendentePagamentoUseCase = salvarPedidoPendentePagamentoUseCase;
        this.calcularValorTotalPedidoUseCase = calcularValorTotalPedidoUseCase;
        this.solicitarPagamentoUseCase = solicitarPagamentoUseCase;
    }

    public void processar(PedidoDTO pedidoDTO) {
        try {
            var valorTotal = calcularValorTotalPedidoUseCase.calcular(pedidoDTO);

            // #1: obtém dados do produto
            List<ProdutoEntity> produtos = buscarProdutosUseCase.buscarPorSkus(pedidoDTO.produtos());

            // #2: obtém dados do cliente
            String cpf = pedidoDTO.cliente().cpf();
            //ClienteEntity cliente = new ClienteEntity("Ana", "99999999999");
            ClienteEntity cliente = buscarClienteUseCase.buscar(cpf);

            // #3: baixa estoque
            boolean temEstoque = buscarEstoqueUseCase.verificarDisponibilidade(pedidoDTO);

            if (!temEstoque) {
                salvarPedidoSemEstoqueUseCase.salvar(pedidoDTO, cliente, produtos, valorTotal);
                return;
            }
            baixarEstoqueUseCase.baixar(pedidoDTO);

            // #4: solicita pagamento
            solicitarPagamentoUseCase.solicitar(pedidoDTO, valorTotal);

            // #5: save
            // se tudo der certo nos passos anteriores, o pedido é salvo com o status PENDENTE_PAGAMENTO
            salvarPedidoPendentePagamentoUseCase.salvar(pedidoDTO, cliente, produtos, valorTotal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}