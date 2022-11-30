package br.edu.femass.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Autor {

    @Id
    private String nome;
    private String sobrenome;
    private String nacionalidade;
    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "autor", orphanRemoval = false)
    private List<Livro> livros;

    public Autor(String nome, String sobrenome, String nacionalidade) {

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nacionalidade = nacionalidade;
        
    }

    public Autor(){

    }

    @Override
    public String toString(){
        return this.nome;
    }

    //Getters and Setters
    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}