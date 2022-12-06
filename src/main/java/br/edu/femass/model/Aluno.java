package br.edu.femass.model;

import javax.persistence.Entity;

@Entity
public class Aluno extends Leitor {

    private String matricula;

    public Aluno() {
        setPrazoMaximoDevolucao(15);
    }

    public Aluno(String nome, String endereco, String telefone, String matricula) throws Exception {
        super(nome, endereco, telefone);
        this.matricula = matricula;
        setPrazoMaximoDevolucao(15);
        // proximoId();
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}