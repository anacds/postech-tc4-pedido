package com.example.postech_tc4_pedido.domain;

import com.example.postech_tc4_pedido.exception.QuantidadeProdutoException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void deveCriarProdutoComDadosValidos() {
        Produto produto = new Produto(
                "sku1",
                5,
                "Produto A",
                "123456789",
                "Descrição do produto",
                "Fabricante X",
                BigDecimal.valueOf(99.90),
                "Categoria A"
        );

        assertEquals("sku1", produto.getSku());
        assertEquals(5, produto.getQuantidade());
        assertEquals("Produto A", produto.getNome());
        assertEquals("123456789", produto.getCodigoDeBarras());
        assertEquals("Descrição do produto", produto.getDescricao());
        assertEquals("Fabricante X", produto.getFabricante());
        assertEquals(BigDecimal.valueOf(99.90), produto.getPreco());
        assertEquals("Categoria A", produto.getCategoria());
    }

    @Test
    void deveLancarExcecaoQuandoQuantidadeNegativa() {
        Produto produto = new Produto("sku", 1, "nome", "123", "desc", "fab", BigDecimal.TEN, "cat");

        QuantidadeProdutoException ex = assertThrows(
                QuantidadeProdutoException.class,
                () -> produto.validaQuantidadeProduto(-1)
        );

        assertEquals("A quantidade do produto não pode ser zerada ou negativa.", ex.getMessage());
    }
}