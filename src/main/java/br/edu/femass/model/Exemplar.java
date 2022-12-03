package br.edu.femass.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.edu.femass.dao.DaoExemplar;

@Entity
public class Exemplar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo = 1L;
    private LocalDate dataAquisicao;
    private boolean disponivel;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Livro livro;

    public Exemplar(){
        this.dataAquisicao = LocalDate.now();
    }

    public Exemplar(Livro livro) throws Exception {
        //this.proximoId();
        this.dataAquisicao = LocalDate.now();
        this.disponivel = true;
        this.livro = livro;
        livro.adicionarExemplar(this);
    }

    @Override
    public String toString() {
        return this.getLivro().getTitulo() + " " + this.getCodigo().toString();
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
    public boolean getDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public Livro getLivro() {
        return livro;
    }
}
