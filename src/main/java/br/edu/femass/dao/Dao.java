package br.edu.femass.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

 //<E> de entidade
public class Dao<E> {

    private static EntityManagerFactory emf; //Cria o EntityManager
    protected static EntityManager em; //Acessa o banco para fazer o CRUD

    static{
        try {
            //Cria uma EntityManegerFactory a partir da nossa persitência
            emf = Persistence.createEntityManagerFactory("jpa_n2");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Dao() {
        //emf cria uma EntityManeger
        em = emf.createEntityManager();
    }
    
    public void inserir(E entidade) {
        
        //Começa uma transação/conexão no banco
        em.getTransaction().begin();
        //Insere no banco
        em.persist(entidade);
        //Fecha a transação/conexão
        em.getTransaction().commit();

    }

    public void apagar(E entidade) {

        //Começa uma transação/conexão no banco
        em.getTransaction().begin();
        //Deletar no banco
        em.remove(entidade);
        //Fecha a transação/conexão
        em.getTransaction().commit();

    }

    public void alterar(E entidade) {

        //Começa uma transação/conexão no banco
        em.getTransaction().begin();
        //Altera no banco
        em.merge(entidade);
        //Fecha a transação/conexão
        em.getTransaction().commit();

    }
}