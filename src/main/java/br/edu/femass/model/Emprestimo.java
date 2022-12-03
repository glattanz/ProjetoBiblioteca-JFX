package br.edu.femass.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import net.bytebuddy.asm.Advice.Local;

@Entity
public class Emprestimo {
    
    @Id
    private Long codigo;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private Boolean atrasado;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Exemplar exemplar;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Leitor leitor;

    public Emprestimo(){
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = LocalDate.now().plusDays(leitor.getPrazoMaximoDevolucao());
        this.atrasado = false;
    }

    public Emprestimo(Leitor leitor, Livro livro){
        this.leitor = leitor;
        this.exemplar = livro.retornaExemplar();
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = LocalDate.now().plusDays(leitor.getPrazoMaximoDevolucao());
        this.atrasado = false;
    }

    //Retorna true se tiver atrasado
    public Boolean verificaAtraso(){
        return this.getDataPrevistaDevolucao().isBefore(LocalDate.now());
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public void setAtrasado(Boolean atrasado) {
        this.atrasado = atrasado;
    }
}
