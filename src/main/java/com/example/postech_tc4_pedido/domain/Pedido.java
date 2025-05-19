package com.example.postech_tc4_pedido.domain;

import com.example.postech_tc4_pedido.exception.QuantidadeProdutoException;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {
    private final String id;
    private final String idEvento;
    private final Cliente cliente;
    private final List<Produto> produtos;
    private final Pagamento pagamento;
    private StatusPedidoEnum status;
    private final BigDecimal valorTotalPedido;

    public Pedido(String id, String idEvento, Cliente cliente, List<Produto> produtos,
                  Pagamento pagamento, StatusPedidoEnum status, BigDecimal valorTotalPedido) {

        this.id = id;
        this.idEvento = idEvento;
        this.cliente = cliente;
        this.produtos = produtos;
        this.pagamento = pagamento;
        this.status = status;
        this.valorTotalPedido = valorTotalPedido;
    }

    public String getId() {
        return id;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }

    public BigDecimal getValorTotalPedido() {
        return valorTotalPedido;
    }
}