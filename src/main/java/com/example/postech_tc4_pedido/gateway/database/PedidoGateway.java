package com.example.postech_tc4_pedido.gateway.database;

import com.example.postech_tc4_pedido.domain.Cliente;
import com.example.postech_tc4_pedido.domain.Pagamento;
import com.example.postech_tc4_pedido.domain.Pedido;
import com.example.postech_tc4_pedido.domain.Produto;
import com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.PagamentoEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.PedidoEntity;
import com.example.postech_tc4_pedido.gateway.database.entity.ProdutoEntity;
import com.example.postech_tc4_pedido.gateway.database.interfaces.IPedidoGateway;
import com.example.postech_tc4_pedido.gateway.database.repository.PedidoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PedidoGateway implements IPedidoGateway {

    private final PedidoRepository pedidoRepository;

    public PedidoGateway(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Optional<Pedido> buscarPorId(String id) {
        return pedidoRepository.findById(id).map(this::entityParaDomain);
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        var entity = domainParaEntity(pedido);
        var saved = pedidoRepository.save(entity);
        return entityParaDomain(saved);
    }

    private Pedido entityParaDomain(PedidoEntity entity) {
        return new Pedido(
                entity.getId(),
                entity.getIdEvento(),
                new Cliente(entity.getCliente().getNome(), entity.getCliente().getCpf()),
                entity.getProdutos().stream()
                        .map(p -> new Produto(
                                p.getSku(),
                                p.getQuantidade(),
                                p.getNome(),
                                p.getCodigoDeBarras(),
                                p.getDescricao(),
                                p.getFabricante(),
                                p.getPreco(),
                                p.getCategoria()
                        )).toList(),
                new Pagamento(entity.getPagamento().getNumeroCartao()),
                entity.getStatus(),
                entity.getValorTotalPedido()
        );
    }

    private PedidoEntity domainParaEntity(Pedido domain) {
        return new PedidoEntity(
                domain.getId(),
                domain.getIdEvento(),
                new ClienteEntity(domain.getCliente().getNome(), domain.getCliente().getCpf()),
                domain.getProdutos().stream()
                        .map(p -> new ProdutoEntity(
                                p.getSku(),
                                p.getQuantidade(),
                                p.getNome(),
                                p.getCodigoDeBarras(),
                                p.getDescricao(),
                                p.getFabricante(),
                                p.getPreco(),
                                p.getCategoria()
                        )).toList(),
                new PagamentoEntity(domain.getPagamento().getNumeroCartao()),
                domain.getStatus(),
                domain.getValorTotalPedido()
        );
    }
}