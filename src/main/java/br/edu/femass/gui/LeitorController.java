package br.edu.femass.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoProfessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TextField CampoInfoEspecifica;
    @FXML
    private TableView<Leitor> TabelaLeitores;
    @FXML
    private TableColumn<Leitor, String>ColunaEndereco;
    @FXML
    private TableColumn<Leitor, String>ColunaNome;
    @FXML
    private TableColumn<Leitor, String>ColunaTelefone;
    @FXML
    private TableView<Emprestimo> TabelaEmprestimos;
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
    @FXML
    private Label LabelInfoEspecifica;
    
    private DaoAluno daoAluno = new DaoAluno();
    private DaoProfessor daoProfessor = new DaoProfessor();
    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
    private Leitor leitor;
    private Aluno aluno;
    private Professor professor;
    private boolean inserindo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        preencherLista();
        ColunaNome.setCellValueFactory(new PropertyValueFactory<Leitor, String>("nome"));
        ColunaEndereco.setCellValueFactory(new PropertyValueFactory<Leitor, String>("endereco"));
        ColunaTelefone.setCellValueFactory(new PropertyValueFactory<Leitor, String>("telefone"));

    }    

    @FXML
    private void Salvar_Click(ActionEvent event) {

        int indexCombo = ComboBoxTipoLeitor.getSelectionModel().getSelectedIndex();
        Boolean bool;

        do{
            if(indexCombo == 0){

                aluno = new Aluno();

                aluno.setNome(CampoNome.getText());
                aluno.setEndereco(CampoEndereco.getText());
                aluno.setTelefone(CampoTelefone.getText());
                aluno.setMatricula(CampoInfoEspecifica.getText());
                
                if (inserindo) {
                    daoAluno.inserir(aluno);

                    JOptionPane.showMessageDialog(null, "Leitor salvo!");
                }else{
                    daoAluno.alterar(aluno);

                    JOptionPane.showMessageDialog(null, "Leitor salvo!");
                }

                bool = true;
                break;

            }else if(indexCombo == 1){

                professor = new Professor();

                professor.setNome(CampoNome.getText());
                professor.setEndereco(CampoEndereco.getText());
                professor.setTelefone(CampoTelefone.getText());
                professor.setDisciplina(CampoInfoEspecifica.getText());
                
                if (inserindo) {
                    daoProfessor.inserir(professor);

                    JOptionPane.showMessageDialog(null, "Leitor salvo!");
                }else{
                    daoProfessor.alterar(professor);

                    JOptionPane.showMessageDialog(null, "Leitor salvo!");
                }

                bool = true;
                break;
            }

            JOptionPane.showMessageDialog(null, "Selecione um tipo de leitor", "Alerta", 3);

        }while(bool = false);

        preencherLista();
        dadosEmBranco();
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
            
            aluno = (Aluno) TabelaLeitores.getSelectionModel().getSelectedItem();
            
            daoAluno.apagar(aluno);

        } else if (TabelaLeitores.getSelectionModel().getSelectedItem().getClass() == Professor.class) {
            
            professor = (Professor) TabelaLeitores.getSelectionModel().getSelectedItem();

            daoProfessor.apagar(professor);

        }
        
        JOptionPane.showMessageDialog(null, "Leitor deletado!");

        dadosEmBranco();
        preencherLista();
    }

    @FXML
    private void Inserir_Click(ActionEvent event) {

        editar(true);
        inserindo = true;

        //Deixa os campos em branco
        // CampoCodigo.setText("");
        // CampoNome.setText("");
        // CampoEndereco.setText("");
        // CampoTelefone.setText("");
        // CampoInfoEspecifica.setText("");
        // CampoPrazo.setText("");
        // ComboBoxTipoLeitor.setValue(null);
        // LabelInfoEspecifica.setText("");
        dadosEmBranco();

        //Deixa o cursor nesse campo para digitar
        CampoNome.requestFocus();

        preencherLista();
        
    }

    @FXML
    private void ListarLeitores_KeyPressed(KeyEvent event) {

        exibirDados();

        preencherLista();

    }

    @FXML
    private void ListarLeitores_MouseClicked(MouseEvent event) {

        exibirDados();

        preencherLista();
        
    }

    @FXML
    private void ComboBoxTipoLeitor_Clicked(ActionEvent event) {

        int indexCombo = ComboBoxTipoLeitor.getSelectionModel().getSelectedIndex();

        String textoLabel;

        if(indexCombo == 0)
            textoLabel = "Matrícula:";
        else
            textoLabel = "Disciplina:";

        LabelInfoEspecifica.setText(textoLabel);
        
    }

    private void exibirDados() {

        this.leitor = TabelaLeitores.getSelectionModel().getSelectedItem();
        
        if(leitor.getClass() == Aluno.class)
            aluno = (Aluno) leitor;
        else
            professor = (Professor) leitor;

        if(leitor == null)
            return;

        CampoCodigo.setText(leitor.getCodigo().toString());
        CampoNome.setText(leitor.getNome());
        CampoEndereco.setText(leitor.getEndereco());
        CampoTelefone.setText(leitor.getTelefone());
        CampoPrazo.setText(leitor.getPrazoMaximoDevolucao().toString());
        ComboBoxTipoLeitor.setPromptText(leitor.getClass().getSimpleName());
        LabelInfoEspecifica.setText(leitor.getClass() == Aluno.class ?  "Matrícula" : "Disciplina");
        CampoInfoEspecifica.setText(leitor.getClass() == Aluno.class ? aluno.getMatricula() : professor.getDisciplina());

    }

    private void dadosEmBranco(){

        CampoCodigo.setText("");
        CampoNome.setText("");
        CampoEndereco.setText("");
        CampoTelefone.setText("");
        CampoInfoEspecifica.setText("");
        CampoPrazo.setText("");
        ComboBoxTipoLeitor.setValue("Selecione um tipo de leitor");;
        LabelInfoEspecifica.setText("");
        CampoInfoEspecifica.setText("");

    }

    private void editar(boolean habilitar) {

        TabelaLeitores.setDisable(habilitar); //Desabilita
        TabelaEmprestimos.setDisable(habilitar); //Desabilita
        CampoNome.setDisable(!habilitar); //Habilita
        CampoEndereco.setDisable(!habilitar);
        CampoTelefone.setDisable(!habilitar);
        BotaoExcluir.setDisable(habilitar);
        BotaoInserir.setDisable(habilitar);
        BotaoAlterar.setDisable(habilitar);
        BotaoSalvar.setDisable(!habilitar);
        ComboBoxTipoLeitor.setDisable(!habilitar);
        CampoInfoEspecifica.setDisable(!habilitar);

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

        List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();

        List<Emprestimo> emprestimosDoLeitor = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {

            if(emprestimo.getLeitor() == leitor){
                
                emprestimosDoLeitor.add(emprestimo);

            }
        }

        ObservableList<Emprestimo> data2 = FXCollections.observableArrayList(emprestimos);
        TabelaEmprestimos.setItems(data2); 

    }
}

