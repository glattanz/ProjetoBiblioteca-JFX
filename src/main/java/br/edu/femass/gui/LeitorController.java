package br.edu.femass.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.edu.femass.model.Aluno;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoProfessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LeitorController implements Initializable {
    
    @FXML
    private TextField CampoCodigo;
    @FXML
    private TextField CampoNome;
    @FXML
    private TextField CampoEndereco;
    @FXML
    private TextField CampoTelefone;
    @FXML
    private TextField CampoPrazo;
    @FXML
    private TextField CampoDisciplina;
    @FXML
    private TableView<Leitor> TabelaLeitores;
    // @FXML
    // private TableView<Emprestimo> TabelaEmprestimos;
    @FXML
    private Button BotaoSalvar;
    @FXML
    private Button BotaoInserir;
    @FXML
    private Button BotaoAlterar;
    @FXML
    private Button BotaoExcluir;
    @FXML
    private ComboBox<String> ComboBoxTipoLeitor;
    
    private DaoAluno daoAluno = new DaoAluno();
    private DaoProfessor daoProfessor = new DaoProfessor();
    //private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
    private Leitor leitor;
    private Aluno aluno;
    private Professor professor;
    private boolean inserindo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        preencherLista();

    }    

    @FXML
    private void Salvar_Click(ActionEvent event) {

        boolean bool = false;

        while(bool == false) {

            if(ComboBoxTipoLeitor.getOnAction().getClass() == Aluno.class){

                aluno.setNome(CampoNome.getText());
                aluno.setEndereco(CampoEndereco.getText());
                aluno.setTelefone(CampoTelefone.getText());
                
                if (inserindo) {
                    aluno = new Aluno();

                    daoAluno.inserir(aluno);
                }else{
                    daoAluno.alterar(aluno);
                }

                bool = true;

            }else if(ComboBoxTipoLeitor.getSelectionModel().getSelectedItem().toString() == "Professor"){

                professor.setNome(CampoNome.getText());
                professor.setEndereco(CampoEndereco.getText());
                professor.setTelefone(CampoTelefone.getText());
                
                if (inserindo) {
                    professor = new Professor();

                    daoProfessor.inserir(professor);
                }else{
                    daoProfessor.alterar(professor);
                }

                bool = true;
            }

            JOptionPane.showMessageDialog(null, this, "Selecione um tipo de leitor", 3);
        }

        preencherLista();
        editar(false);
        //TODO: Fazer um entidade.getId() para mostrar o código que foi gerado
    }

    @FXML
    private void Alterar_Click(ActionEvent event) {
    
        editar(true);
        inserindo = false;

    }

    @FXML
    private void Excluir_Click(ActionEvent event) {

        if (TabelaLeitores.getSelectionModel().getSelectedItem().getClass() == Aluno.class) {
            
            daoAluno.apagar(aluno);

        } else if (TabelaLeitores.getSelectionModel().getSelectedItem().getClass() == Professor.class) {
            
            daoProfessor.apagar(professor);

        }
        
        preencherLista();
    }

    @FXML
    private void Inserir_Click(ActionEvent event) {

        editar(true);
        inserindo = true;

        //Deixa os campos em branco
        CampoCodigo.setText("");
        CampoNome.setText("");
        CampoEndereco.setText("");
        CampoTelefone.setText("");
        CampoPrazo.setText("");

        //Deixa o cursor nesse campo para digitar
        CampoNome.requestFocus();

        preencherLista();
        
    }

    @FXML
    private void ListarLeitores_KeyPressed(KeyEvent event) {

        exibirDados();

    }

    @FXML
    private void ListarLeitores_MouseClicked(MouseEvent event) {

        exibirDados();
        
    }

    private void exibirDados() {

        this.leitor = TabelaLeitores.getSelectionModel().getSelectedItem();

        if(leitor == null)
            return;

        CampoCodigo.setText(leitor.getCodigo().toString());
        CampoNome.setText(leitor.getNome());
        CampoEndereco.setText(leitor.getEndereco());
        CampoTelefone.setText(leitor.getTelefone());
        CampoPrazo.setText(leitor.getPrazoMaximoDevolucao().toString());
        //Campo.setText(leitor.getTelefone()); TIPO DE LEITOR
        //CampoTelefone.setText(leitor.getTelefone()); DISCIPLINA OU MATRICULA

    }

    private void editar(boolean habilitar) {

        TabelaLeitores.setDisable(habilitar); //Desabilita
        //TabelaEmprestimos.setDisable(habilitar); //Desabilita
        CampoNome.setDisable(!habilitar); //Habilita
        CampoEndereco.setDisable(!habilitar);
        CampoTelefone.setDisable(!habilitar);
        BotaoExcluir.setDisable(habilitar);
        BotaoInserir.setDisable(habilitar);
        BotaoAlterar.setDisable(habilitar);
        BotaoSalvar.setDisable(!habilitar);
        ComboBoxTipoLeitor.setDisable(!habilitar);

    }

    private void preencherLista() {

        List<String> tiposDeLeitor = new ArrayList<>();
        tiposDeLeitor.add("Aluno");
        tiposDeLeitor.add("Professor");
        ObservableList<String> itensCombo = FXCollections.observableArrayList(tiposDeLeitor);
        ComboBoxTipoLeitor.setItems(itensCombo);

        List<Aluno> alunos = daoAluno.buscarTodos();
        List<Professor> professores = daoProfessor.buscarTodos();
        List<Leitor> leitores = new ArrayList<>();

        leitores.addAll(alunos);
        leitores.addAll(professores);

        ObservableList<Leitor> data1 = FXCollections.observableArrayList(leitores);
        TabelaLeitores.setItems(data1);

        /*List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();

        ObservableList<Emprestimo> data2 = FXCollections.observableArrayList(emprestimos);
        TabelaEmprestimos.setItems(data2); */

    }
}

