package org.example;

import org.example.domain.CategoriaCardapio;
import org.example.domain.Database;
import org.example.domain.ItemCardapio;
import org.example.domain.Pedido;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.domain.CategoriaCardapio.getEnumSetCategorias;

public class Main {
    public static void main(String[] args) throws Exception {
        Database database = new Database();
        List<ItemCardapio> itens = database.listaDeItensCardapio();
        List<Pedido> pedidos = database.listaDePedidos();

        System.out.println("--- Iniciando Desafios ---");

        /*
        Desafio 1: O Relatório de Clientes Únicos (Sets)
        Objetivo: Entender a diferença entre HashSet, LinkedHashSet e TreeSet.

        O gerente quer uma lista com os nomes de todos os clientes que fizeram pedidos, sem repetições.
        1. Crie uma coleção que mostre os nomes únicos na ordem aleatória (ou a mais performática possível).
        2. Crie uma coleção que mostre os nomes únicos na ordem alfabética.
        3. Crie uma coleção que mostre os nomes únicos na ordem em que aparecem na lista original (Ana, Zeca, Beto).
        Dica: Extraia os nomes da lista de pedidos e adicione-os às implementações corretas de Set. */

        Set<String> nomesOrdemAleatoria = pedidos.stream()
                .map(Pedido::cliente)
                .collect(Collectors.toSet());
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("1. Crie uma coleção que mostre os nomes únicos na ordem aleatória (ou a mais performática possível).");
        nomesOrdemAleatoria.forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------------------------------------------");

        TreeSet<String> nomesOrdemAlfabetica = pedidos.stream()
                .map(Pedido::cliente)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("2. Crie uma coleção que mostre os nomes únicos na ordem alfabética.");
        nomesOrdemAlfabetica.forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------------------------------------------");

        LinkedHashSet<String> nomesPorOrdemDeInsercao = pedidos.stream()
                .map(Pedido::cliente)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println("3. Crie uma coleção que mostre os nomes únicos na ordem em que aparecem na lista original (Ana, Zeca, Beto).");
        nomesPorOrdemDeInsercao.forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------------------------------------------");


        /*
        Desafio 2: Agrupamento por Categoria (Maps & Enums)
        Objetivo: Usar a otimização de EnumMap.

        Você precisa separar os itens disponíveis por categoria para o menu.
        1. Crie um mapa onde a Chave é a Categoria e o Valor é uma lista de Item que pertencem àquela categoria.
        2. Regra: Como a chave é um Enum, você deve usar a implementação de Map mais otimizada para memória e performance (aquela que usa array de bits internamente).
        3. Dica: Não use HashMap aqui. Lembre-se que existe uma classe específica para quando a chave é um Enum.*/

        EnumMap<CategoriaCardapio, LinkedHashSet<ItemCardapio>> categoriaCardapioListEnumMap = new EnumMap<>(CategoriaCardapio.class);
        EnumSet<CategoriaCardapio> categorias = getEnumSetCategorias();
        categorias.forEach(categoria -> {
            LinkedHashSet<ItemCardapio> itensFiltradosPorCategoria = itens.stream()
                    .filter(itemCardapio -> itemCardapio.categora().equals(categoria))
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            categoriaCardapioListEnumMap.put(categoria, itensFiltradosPorCategoria);
        });

        categorias.forEach(categoria -> {
            LinkedHashSet<ItemCardapio> itemCardapios = categoriaCardapioListEnumMap.get(categoria);
            System.out.println("Chave: " + categoria + " quantidade dessa categoria: " + itemCardapios.size());
        });
        System.out.println("-----------------------------------------------------------------------------------------------------");

        /*
        Desafio 3: O Mistério da Auditoria (IdentityHashMap)
        Objetivo: Entender a diferença entre Igualdade (equals) e Identidade (==).

        1. No código base, note que item1 e item8 são logicamente iguais (mesmo ID, nome e preço), mas são objetos diferentes na memória (new diferentes). O record implementa o equals automaticamente comparando os dados.
        2. Tente adicionar item1 e item8 como chaves em um HashMap normal associando a um valor (ex: id). Imprima o tamanho do mapa.
        3. Agora, o auditor quer rastrear exatamente qual instância de objeto foi usada, mesmo que os dados sejam iguais. Crie um mapa que consiga armazenar tanto item1 quanto item8 como chaves distintas simultaneamente.
        4. Output esperado: O primeiro mapa deve ter tamanho 1. O segundo mapa deve ter tamanho 2. */

        ItemCardapio itemCardapio1 = itens.get(0);
        ItemCardapio itemCardapio8 = itens.get(7);
        HashMap<ItemCardapio, String> primeiroHashMap = new HashMap<>();
        primeiroHashMap.put(itemCardapio1, "itemCardapio1");
        primeiroHashMap.put(itemCardapio8, "itemCardapio8");
        System.out.println("2. Tente adicionar item1 e item8 como chaves em um HashMap normal associando a um valor (ex: id). Imprima o tamanho do mapa.");
        System.out.println("Tamanho do mapa: " + primeiroHashMap.size());
        System.out.println("-----------------------------------------------------------------------------------------------------");

        IdentityHashMap<ItemCardapio, String> segundoHashMap = new IdentityHashMap<>();
        segundoHashMap.put(itemCardapio1, "itemCardapio1");
        segundoHashMap.put(itemCardapio8, "itemCardapio8");
        System.out.println("3. Agora, o auditor quer rastrear exatamente qual instância de objeto foi usada, mesmo que os dados sejam iguais. Crie um mapa que consiga armazenar tanto item1 quanto item8 como chaves distintas simultaneamente.");
        System.out.println("Tamanho do mapa: " + segundoHashMap.size());
        System.out.println("-----------------------------------------------------------------------------------------------------");

        /*
        Desafio 4: O Cache Temporário (WeakHashMap)
        Objetivo: Demonstrar o Garbage Collector limpando um Map.

        1. Crie um cenário onde metadados de itens (ex: descrições longas) são guardados em cache, mas não queremos que esse cache cause estouro de memória (OutOfMemoryError) se o item original deixar de ser usado.
        2. Crie um Map especial onde a chave é um objeto Item e o valor é uma String "Descrição Pesada".
                1. Insira um item nesse mapa.
                2. Force a perda da referência forte desse item (ex: item = null).
                3. Force a execução do Garbage Collector (System.gc()).
                4. Verifique se o item sumiu do mapa ou se o mapa está vazio, provando que não há vazamento de memória.
        Dica: Nos slides, isso é mencionado como "O Problema do Histórico". Qual mapa usa referências fracas para as chaves? */

        System.out.println("\n--- Desafio 4: WeakHashMap ---");

        // 1. Criamos o mapa com referências fracas
        Map<ItemCardapio, String> cacheMetadata = new WeakHashMap<>();

        // 2. Criamos um item que será nossa chave (Referência Forte criada aqui)
        // IMPORTANTE: Não use itens de listas estáticas ou constantes, crie um novo.
        ItemCardapio itemTemporario = new ItemCardapio(99L, "Item Fantasma", "Descricao", CategoriaCardapio.BEBIDAS, new BigDecimal("20.99"), null);

        // 3. Adicionamos ao cache
        cacheMetadata.put(itemTemporario, "Dados pesados de cache...");

        System.out.println("Tamanho do mapa com referência forte: " + cacheMetadata.size());
        System.out.println("O mapa contém a chave? " + cacheMetadata.containsKey(itemTemporario));

        // 4. O PULO DO GATO: Removemos a referência forte
        // Agora, somente o WeakHashMap "conhece" esse objeto.
        itemTemporario = null;

        // 5. Sugerimos ao Java que limpe a memória (Garbage Collection)
        System.gc();

        // Damos um tempinho para o estagiário (GC) trabalhar...
        System.out.println("Solicitando limpeza de memória...");
        Thread.sleep(2000);

        // 6. Verificamos se o item sumiu
        System.out.println("Tamanho do mapa após GC: " + cacheMetadata.size());

        if (cacheMetadata.isEmpty()) {
            System.out.println("SUCESSO: O item foi removido da memória!");
        } else {
            System.out.println("Falha: O item ainda está lá (o GC pode não ter rodado ainda).");
        }
    }
}