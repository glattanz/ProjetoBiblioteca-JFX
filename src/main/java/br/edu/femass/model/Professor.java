package br.edu.femass.model;

import javax.persistence.Entity;

@Entity
public class Professor extends Leitor {
 
    private String disciplina;

    public Professor(){
        setPrazoMaximoDevolucao(30);
    }

    public Professor(String nome, String endereco, String telefone, String disciplina) throws Exception {
        super(nome, endereco, telefone);
        this.disciplina = disciplina;
        setPrazoMaximoDevolucao(30);
        proximoId();
    }

    public String getDisciplina() {
        return disciplina;
    }

}
