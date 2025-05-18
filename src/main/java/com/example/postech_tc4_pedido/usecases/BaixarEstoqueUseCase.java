    package com.example.postech_tc4_pedido.usecases;

    import com.example.postech_tc4_pedido.dto.PedidoDTO;
    import com.example.postech_tc4_pedido.gateway.external.interfaces.IEstoqueGateway;
    import org.springframework.stereotype.Service;

    @Service
    public class BaixarEstoqueUseCase {

        private final IEstoqueGateway estoqueGateway;

        public BaixarEstoqueUseCase(IEstoqueGateway estoqueGateway) {
            this.estoqueGateway = estoqueGateway;
        }

        public void baixar(PedidoDTO pedidoDTO) {
            try {
                pedidoDTO.produtos().forEach(produto ->
                        estoqueGateway.removerDoEstoque(produto.sku(), produto.quantidade())
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }