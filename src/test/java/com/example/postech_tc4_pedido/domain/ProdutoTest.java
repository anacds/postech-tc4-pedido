package com.example.postech_tc4_pedido.domain;

import com.example.postech_tc4_pedido.exception.QuantidadeProdutoException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void deveCriarProdutoValido() {
        Produto produto = new Produto(
                "sku123",
                5,
                "Produto Teste",
                "123456789",
                "Descrição",
                "Fabricante",
                BigDecimal.TEN,
                "Categoria"
        );

        assertEquals("sku123", produto.getSku());
        assertEquals(5, produto.getQuantidade());
        assertEquals("Produto Teste", produto.getNome());
        assertEquals("123456789", produto.getCodigoDeBarras());
        assertEquals("Descrição", produto.getDescricao());
        assertEquals("Fabricante", produto.getFabricante());
        assertEquals(BigDecimal.TEN, produto.getPreco());
        assertEquals("Categoria", produto.getCategoria());
    }

    @Test
    void deveLancarExcecaoQuandoQuantidadeForZeroOuNegativa() {
        assertThrows(QuantidadeProdutoException.class, () ->
                new Produto("sku123", 0, "nome", "123", "desc", "fab", BigDecimal.ONE, "cat"));

        assertThrows(QuantidadeProdutoException.class, () ->
                new Produto("sku123", -5, "nome", "123", "desc", "fab", BigDecimal.ONE, "cat"));
    }
}