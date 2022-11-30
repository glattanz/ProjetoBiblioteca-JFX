package br.edu.femass.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.edu.femass.dao.DaoExemplar;

@Entity
public class Exemplar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo = 1L;
    private LocalDate dataAquisicao;
    private boolean disponivel;
    private String tituloExemplar;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "exemplares")
    private Livro livro;

    public Exemplar(){
    }

    public Exemplar(Livro livro) throws Exception {
        this.proximoId();
        this.dataAquisicao = LocalDate.now();
        this.disponivel = true;
        this.tituloExemplar = livro.toString();

        //livro.addListaExemplares(this);
    }

    @Override
    public String toString() {
        return this.getTituloExemplar() + " " + this.getCodigo().toString();
    }

    public void proximoId() throws Exception {
        Long maior = 0L;

        List<Exemplar> exemplares = new DaoExemplar().buscarTodos();
        for (Exemplar exemplar: exemplares) {
            if (exemplar.getCodigo()>maior) {
                maior = exemplar.getCodigo();
                setCodigo(maior + 1);
            }
        }
    }

    //Getters and Setters
    public Long getCodigo() {
        return codigo;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public String getTituloExemplar() {
        return tituloExemplar;
    }
    public boolean getDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
}
