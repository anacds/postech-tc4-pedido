package com.example.postech_tc4_pedido.gateway.database.entity;

import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection="pedido")
public class PedidoEntity {

    @Id
    public String id;
    public String idEvento;
    private ClienteEntity cliente;
    private List<ProdutoEntity> produtos;
    private PagamentoEntity pagamento;
    private StatusPedidoEnum status;
    private BigDecimal valorTotalPedido;

    public PedidoEntity() {
        super();
    }

    public PedidoEntity(String id,
                        String idEvento,
                        ClienteEntity cliente,
                        List<ProdutoEntity> produtos,
                        PagamentoEntity pagamento,
                        StatusPedidoEnum status,
                        BigDecimal valorTotalPedido) {
        super();
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

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<ProdutoEntity> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoEntity> produtos) {
        this.produtos = produtos;
    }

    public PagamentoEntity getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoEntity pagamento) {
        this.pagamento = pagamento;
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

    public void setValorTotalPedido(BigDecimal valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }
}
