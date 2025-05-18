package com.example.postech_tc4_pedido.gateway.database.entity;

public class ClienteEntity {
    private String nome;
    private String cpf;

    public ClienteEntity() {
        super();
    }

    public ClienteEntity(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
