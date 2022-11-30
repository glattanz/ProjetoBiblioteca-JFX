package br.edu.femass.dao;

import java.util.List;
import javax.persistence.Query;
import br.edu.femass.model.Professor;

public class DaoProfessor extends Dao<Professor> {
    
    public List<Professor> buscarTodos(){

        Query query = em.createQuery("select P from Professor P order by P.id");

        return query.getResultList();
    }
}
