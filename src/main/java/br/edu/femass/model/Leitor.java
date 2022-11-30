package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoProfessor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Leitor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long codigo = 1L;
    protected String nome;
    protected String endereco;
    protected String telefone;
    protected Integer prazoMaximoDevolucao;

    public Leitor() {

    }
    
    public Leitor(String nome, String endereco, String telefone) {

        this.endereco = endereco;
        this.nome = nome;
        this.telefone = telefone;
        
    }

    public void proximoId() throws Exception {
        Long maior = 0L;

        List<Aluno> alunos = new DaoAluno().buscarTodos();
        List<Professor> professores = new DaoProfessor().buscarTodos();
        List<Leitor> leitores = new ArrayList<>();
        leitores.addAll(alunos);
        leitores.addAll(professores);

        for (Leitor leitor: leitores) {
            if (leitor.getCodigo()>maior) {
                maior = leitor.getCodigo();
                setCodigo(maior + 1);
            }
        }
    }
    
    @Override
    public String toString() {
        return this.nome;
    }

    //Getters and setters
    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public Integer getPrazoMaximoDevolucao() {
        return prazoMaximoDevolucao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void setPrazoMaximoDevolucao(Integer prazoMaximoDevolucao) {
        this.prazoMaximoDevolucao = prazoMaximoDevolucao;
    }
}
