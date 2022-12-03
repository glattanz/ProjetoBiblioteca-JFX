package br.edu.femass.dao;

import java.util.List;
import javax.persistence.Query;
import br.edu.femass.model.Livro;

public class DaoLivro extends Dao<Livro> { 
    
    public List<Livro> buscarTodos(){

        Query query = em.createQuery("select L from Livro L order by L.titulo");

        return query.getResultList();
    }
}
