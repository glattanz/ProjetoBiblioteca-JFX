package br.edu.femass.dao;

import java.util.List;
import javax.persistence.Query;
import br.edu.femass.model.Autor;

public class DaoAutor extends Dao<Autor> {
    
    public List<Autor> buscarTodos(){

        Query query = em.createQuery("select A from Autor A order by A.nome");

        return query.getResultList();
    }
}