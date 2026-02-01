package org.example.domain;

import java.util.List;

public record Pedido(Long id, String cliente, List<ItemCardapio> itens) {}
