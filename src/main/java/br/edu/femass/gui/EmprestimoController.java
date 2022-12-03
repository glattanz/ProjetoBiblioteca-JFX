package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Livro;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class EmprestimoController implements Initializable {
    
    @FXML
    private Button BotaoRealizarEmprestimo;
    @FXML
    private TableColumn<Emprestimo, LocalDate> ColunaDataDevolucao;
    @FXML
    private TableColumn<Emprestimo, LocalDate> ColunaDataEmprestimo;
    @FXML
    private TableColumn<Emprestimo, Long> ColunaID;
    @FXML
    private TableColumn<Leitor, String> ColunaLeitor;
    @FXML
    private TableColumn<Livro, String> ColunaLivro;
    @FXML
    private TableColumn<Emprestimo, LocalDate> ColunaDataPrevisao;
    @FXML
    private TableColumn<Exemplar, Boolean> ColunaAtrasado;
    @FXML
    private ListView<Leitor> ListaLeitores;
    @FXML
    private ListView<Livro> ListaLivros;
    @FXML
    private Pane PaneInfoSelecionada;
    @FXML
    private TextField CampoLeitor;
    @FXML
    private TextField CampoLivro;

    private Leitor leitor;
    private Livro livro;
    private Emprestimo emprestimo;
    private Exemplar exemplar;
    private DaoAluno daoAluno = new DaoAluno();
    private DaoProfessor daoProfessor = new DaoProfessor();
    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
    private DaoLivro daoLivro = new DaoLivro();
    private Boolean mostrarInfoSelecionada = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        preencherLista();

        List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.verificaAtraso()) {
                emprestimo.setAtrasado(true);
            } else {
                emprestimo.setAtrasado(false);
            }
        }

        ColunaDataDevolucao.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucao"));
        ColunaDataEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucao"));
        ColunaDataPrevisao.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataPrevistaDevolucao"));
        ColunaID.setCellValueFactory(new PropertyValueFactory<Emprestimo, Long>("codigo"));
        ColunaLeitor.setCellValueFactory(new PropertyValueFactory<Leitor, String>("leitor"));
        ColunaLivro.setCellValueFactory(new PropertyValueFactory<Livro, String>("livro"));
        ColunaAtrasado.setCellValueFactory(new PropertyValueFactory<Exemplar, Boolean>("atrasado"));
    } 

    @FXML
    private void ListaLivros_MouseClicked(MouseEvent event) {
    
        livro = ListaLivros.getSelectionModel().getSelectedItem();

        exibirInfoSelecionada();

    }

    @FXML
    private void ListaLeitores_MouseClicked(MouseEvent event) {
    
        leitor = ListaLeitores.getSelectionModel().getSelectedItem();

        exibirInfoSelecionada();
    }

    @FXML
    private void RealizarEmprestimo_Click(ActionEvent event) {
    
        livro = ListaLivros.getSelectionModel().getSelectedItem();
        leitor = ListaLeitores.getSelectionModel().getSelectedItem();
        exemplar = livro.retornaExemplar();

        exemplar.setDisponivel(false);
        emprestimo = new Emprestimo(leitor, livro);
        
        emprestimo.setExemplar(exemplar);
        emprestimo.setLeitor(leitor);

    }

    private void exibirInfoSelecionada() {

        PaneInfoSelecionada.setVisible(true);

        CampoLeitor.setText(leitor == null ? "" : leitor.getNome());
        CampoLivro.setText(livro == null ? "" : livro.getTitulo());
    }

    private void preencherLista() {

        List<Aluno> alunos = daoAluno.buscarTodos();
        List<Professor> professores = daoProfessor.buscarTodos();
        List<Leitor> leitores = new ArrayList<>();

        leitores.addAll(alunos);
        leitores.addAll(professores);

        ObservableList<Leitor> data1 = FXCollections.observableArrayList(leitores);
        ListaLeitores.setItems(data1);

        List<Livro> livros = daoLivro.buscarTodos();

        List<Livro> livrosDisponiveis = new ArrayList<>();

        for (Livro livro : livros) {

            if(livro.getExemplares().size() > 0){
                
                livrosDisponiveis.add(livro);

            }
        }

        ObservableList<Livro> data2 = FXCollections.observableArrayList(livrosDisponiveis);
        ListaLivros.setItems(data2);
    }
}
