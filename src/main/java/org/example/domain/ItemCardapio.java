package org.example.domain;

import java.math.BigDecimal;

public record ItemCardapio(
        Long id,
        String nome,
        String descricao,
        CategoriaCardapio categora,
        BigDecimal preco,
        BigDecimal precoPromocional) {}
