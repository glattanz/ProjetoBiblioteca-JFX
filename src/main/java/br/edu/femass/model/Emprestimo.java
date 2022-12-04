package br.edu.femass.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Emprestimo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo = 1L;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private Boolean atrasado;
    @ManyToOne(cascade = {CascadeType.DETACH})
    private Exemplar exemplar;
    @ManyToOne(cascade = {CascadeType.DETACH})
    private Leitor leitor;

    public Emprestimo(){
        this.dataEmprestimo = LocalDate.now();
        //this.dataPrevistaDevolucao = LocalDate.now().plusDays(leitor.getPrazoMaximoDevolucao());
        this.atrasado = false;
    }

    public Emprestimo(Leitor leitor, Livro livro){
        this.leitor = leitor;
        this.exemplar = livro.retornaExemplarDisponivel();
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

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public Boolean getAtrasado() {
        return atrasado;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public Leitor getLeitor() {
        return leitor;
    }
}
