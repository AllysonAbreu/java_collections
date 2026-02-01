package domain.isento;

import domain.CategoriaCardapio;
import domain.ItemCardapio;

public class ItemCardapioIsento extends ItemCardapio {

    //construtor
    public ItemCardapioIsento(long id, String nome, String descricao, double preco, CategoriaCardapio categoria) {
        super(id, nome, descricao, preco, categoria);
    }

    // reescrita de metodo (override)
    @Override // anotacao opcional
    public double getImposto() {
        return 0.0;
    }

}