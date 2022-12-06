package br.edu.femass;

import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Livro;

public class Main {
    public static void main(String[] args) throws Exception {
        // EntryPoint.main(args);

        Aluno aluno = new Aluno("teste", "Teste", "teste", "tste");
        Autor autor = new Autor("AutorTEste", "SobrenomeTeste", "nacionalidadeBr");
        Livro livro = new Livro("teste", autor);
        Exemplar exemplar = new Exemplar(livro);
        DaoExemplar daoExemplar = new DaoExemplar();
        daoExemplar.inserir(exemplar);

        Emprestimo emprestimo = new Emprestimo(aluno, livro);
        DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
        daoEmprestimo.inserir(emprestimo);
    }
}
