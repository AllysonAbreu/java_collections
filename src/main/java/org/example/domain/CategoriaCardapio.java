package org.example.domain;

import java.util.EnumSet;

public enum CategoriaCardapio {
    ENTRADAS, PRATOS_PRINCIPAIS, BEBIDAS, SOBREMESAS;

    public static EnumSet<CategoriaCardapio> getEnumSetCategorias() {
        return EnumSet.of(CategoriaCardapio.BEBIDAS, CategoriaCardapio.ENTRADAS, CategoriaCardapio.SOBREMESAS, CategoriaCardapio.PRATOS_PRINCIPAIS);
    }
}
