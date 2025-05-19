package com.example.postech_tc4_pedido.gateway.external;

import com.example.postech_tc4_pedido.domain.Cliente;
import com.example.postech_tc4_pedido.gateway.database.entity.ClienteEntity;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IEstoqueGateway;
import com.example.postech_tc4_pedido.gateway.external.interfaces.client.EstoqueClient;
import org.springframework.stereotype.Service;

@Service
public class EstoqueGateway implements IEstoqueGateway {

    private final EstoqueClient feignClient;

    public EstoqueGateway(EstoqueClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public int verificarQuantidadeDisponivel(String sku) {
        return feignClient.verificarQuantidadeDisponivel(sku);
    }

    @Override
    public void removerDoEstoque(String sku, int quantidade) {
        feignClient.removerDoEstoque(sku, quantidade);
    }

    @Override
    public void reporEstoque(String sku, int quantidade) {
        feignClient.reporEstoque(sku, quantidade);
    }


}