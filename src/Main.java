import domain.Cardapio;

void main() throws IOException {

    String nomeArquivo = IO.readln("Digite um nome de arquivo de itens de cardápio: ");

    Cardapio cardapio = new Cardapio(nomeArquivo);

}