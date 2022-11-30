package br.edu.femass.dao;

import java.util.List;
import javax.persistence.Query;
import br.edu.femass.model.Exemplar;

public class DaoExemplar extends Dao<Exemplar> {
    
    public List<Exemplar> buscarTodos(){

        Query query = em.createQuery("select E from Exemplar E order by E.titulo");

        return query.getResultList();
    }
}
