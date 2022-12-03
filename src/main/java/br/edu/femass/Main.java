package br.edu.femass;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;

public class Main {
	public static void main(String[] args) throws Exception {
        EntryPoint.main(args);

        // Autor autor = new Autor("AutorTEste", "SobrenomeTeste", "nacionalidadeBr");
        // Livro livro = new Livro("teste", autor);
        // Exemplar exemplar = new Exemplar(livro);
        // DaoExemplar daoExemplar = new DaoExemplar();
        // daoExemplar.inserir(exemplar);

    }
}
