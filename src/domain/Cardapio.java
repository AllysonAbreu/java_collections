package domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Cardapio {

    private final ItemCardapio[] itens;

    public Cardapio(String nomeArquivo) throws IOException {
        Path arquivo = Path.of(nomeArquivo);
        String conteudoArquivo = Files.readString(arquivo);
        String[] linhasArquivo = conteudoArquivo.split("\n");

        for(String linha: linhasArquivo) {
            IO.println(linha);
            if(nomeArquivo.endsWith(".csv")) {
                // tratar csv
            } else if (nomeArquivo.endsWith(".json")) {
                // tratar json
            } else {
                IO.println("Arquivo com extensão inválida: " + nomeArquivo);
            }
        }

        itens = new ItemCardapio[0];
    }
}
