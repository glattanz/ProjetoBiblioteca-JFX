package br.edu.femass.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import br.edu.femass.dao.DaoExemplar;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String titulo;
    @ManyToOne(cascade = { CascadeType.ALL })
    private Autor autor;
    @OneToMany(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
    private List<Exemplar> exemplares = new ArrayList<>();

    public Livro() {

    }

    public Livro(String titulo, Autor autor) {

        this.titulo = titulo;
        this.autor = autor;

    }

    public void adicionarExemplar(Exemplar exemplar) {
        this.exemplares.add(exemplar);
    }

    public Exemplar retornaExemplarDisponivel() {

        List<Exemplar> exemplaresDisponiveis = new ArrayList<>();

        DaoExemplar daoExemplar = new DaoExemplar();

        exemplares = daoExemplar.buscarTodos();

        for (Exemplar exemplar : exemplares) {

            if (exemplar.getDisponivel() == true) {

                exemplaresDisponiveis.add(exemplar);

            }
        }

        int indexUltimo = exemplaresDisponiveis.size() - 1;

        return exemplaresDisponiveis.get(indexUltimo);
    }

    @Override
    public String toString() {
        return titulo.toString();
    }

    // Getters and Setters
    public Long getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }
}