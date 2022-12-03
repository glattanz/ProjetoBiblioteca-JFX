package br.edu.femass.dao;

import java.util.List;
import javax.persistence.Query;

import br.edu.femass.model.Emprestimo;

public class DaoEmprestimo extends Dao<Emprestimo> { 
    
    public List<Emprestimo> buscarTodos(){

        Query query = em.createQuery("select E from Emprestimo E order by E.codigo");

        return query.getResultList();
    }
}
