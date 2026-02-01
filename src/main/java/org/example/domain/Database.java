package org.example.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.example.domain.CategoriaCardapio.*;

public class Database {

    public List<ItemCardapio> listaDeItensCardapio() {
        List<ItemCardapio> itens = new ArrayList<>();

        var itenCardapio1 = new ItemCardapio(
                1L,
                "Refresco do Chaves",
                "Suco de limão que parece de tamarindo e tem gosto de groselha.",
                BEBIDAS,
                new BigDecimal("2.99"),
            null);
        itens.add(itenCardapio1);

        var itenCardapio2 = new ItemCardapio(
                2L,
                "Sanduíche de Presunto do Chaves",
                "Sanduíche de presunto simples mas feito com muito amor.",
                PRATOS_PRINCIPAIS,
                new BigDecimal("2.99"),
                null);
        itens.add(itenCardapio2);

        var itenCardapio3 = new ItemCardapio(
                3L,
                "Torta de Frango da Dona Florinda",
                "Torta de frango com recheio cremoso e massa crocante.",
                PRATOS_PRINCIPAIS,
                new BigDecimal("10.99"),
                null);
        itens.add(itenCardapio3);

        var itenCardapio4 = new ItemCardapio(
                4L,
                "Pipoca do Quico",
                "Balde de pipoca preparado com carinho pelo Quico.",
                PRATOS_PRINCIPAIS,
                new BigDecimal("4.99"),
                new BigDecimal("3.99"));
        itens.add(itenCardapio4);

        var itenCardapio5 = new ItemCardapio(
                5L,
                "Água de Jamaica",
                "Água aromatizada com hibisco e toque de açúcar.",
                BEBIDAS,
                new BigDecimal("2.50"),
                new BigDecimal("2.00"));
        itens.add(itenCardapio5);

        var itenCardapio6 = new ItemCardapio(
                6L,
                "Churros do Chaves",
                "Churros recheados com doce de leite clássicos e irresistíveis.",
                SOBREMESAS,
                new BigDecimal("4.99"),
                new BigDecimal("3.99"));
        itens.add(itenCardapio6);

        var itenCardapio7 = new ItemCardapio(
                7L,
                "Tacos de Carnitas",
                "Tacos recheados com carne tenra",
                PRATOS_PRINCIPAIS,
                new BigDecimal("25.90"),
                null);
        itens.add(itenCardapio7);

        var itenCardapio8 = new ItemCardapio(
                1L,
                "Refresco do Chaves",
                "Suco de limão que parece de tamarindo e tem gosto de groselha.",
                BEBIDAS,
                new BigDecimal("2.99"),
                null);
        itens.add(itenCardapio8);

        return itens;
    }
    public List<Pedido> listaDePedidos() {
        return List.of(
                new Pedido(1L, "Ana", List.of(this.listaDeItensCardapio().get(0), this.listaDeItensCardapio().get(2))),
                new Pedido(2L, "Zeca", List.of(this.listaDeItensCardapio().get(1))),
                new Pedido(3L, "Ana", List.of(this.listaDeItensCardapio().get(0))), // Ana pediu de novo
                new Pedido(4L, "Beto", List.of(this.listaDeItensCardapio().get(2)))
        );
    }
}
