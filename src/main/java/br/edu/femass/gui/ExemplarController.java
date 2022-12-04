package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ExemplarController implements Initializable {
    
    @FXML
    private TextField CampoCodigo;
    @FXML
    private TextField CampoTitulo;
    @FXML
    private TextField CampoAutor;
    @FXML
    private TextField CampoDataAquisicao;
    @FXML
    private ComboBox<Livro> ComboBoxLivros;
    @FXML
    private TableView<Exemplar> TabelaExemplares;
    @FXML
    private Button BotaoAdicionar;
    @FXML
    private Button Deletar;
    @FXML
    private TableColumn<Autor, String> ColunaAutor;
    @FXML
    private TableColumn<Exemplar, Integer> ColunaCodigo;
    @FXML
    private TableColumn<Exemplar, LocalDate> ColunaDataAquisicao;
    @FXML
    private TableColumn<Livro, String> ColunaTitulo;

    private DaoLivro daoLivro = new DaoLivro();
    private DaoExemplar daoExemplar = new DaoExemplar();
    private Exemplar exemplar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        preencherLista();

        ColunaCodigo.setCellValueFactory(new PropertyValueFactory<Exemplar, Integer>("codigo"));
        ColunaTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        ColunaAutor.setCellValueFactory(new PropertyValueFactory<Autor, String>("autor"));
        ColunaDataAquisicao.setCellValueFactory(new PropertyValueFactory<Exemplar, LocalDate>("dataAquisicao"));

    }    

    @FXML
    private void Adicionar_Click(ActionEvent event) throws Exception {
        
        Exemplar exemplar = new Exemplar(ComboBoxLivros.getSelectionModel().getSelectedItem());
    
        daoExemplar.inserir(exemplar);
        preencherLista();
    }

    @FXML
    private void ListarExemplar_KeyPressed(KeyEvent event) {

        exibirDados();

    }

    @FXML
    private void ListarExemplar_MouseClicked(MouseEvent event) {

        exibirDados();
        
    }

    @FXML
    void Deletar_Click(ActionEvent event) {

        daoExemplar.apagar(exemplar);
        preencherLista();
    }

    private void exibirDados() {

        this.exemplar = TabelaExemplares.getSelectionModel().getSelectedItem();

        if(exemplar == null)
            return;

        CampoCodigo.setText(exemplar.getCodigo().toString());
        CampoTitulo.setText(exemplar.getLivro().getTitulo());
        CampoAutor.setText(exemplar.getLivro().getAutor().toString());
        CampoDataAquisicao.setText(exemplar.getDataAquisicao().toString());

    }

    private void preencherLista() {

        List<Exemplar> exemplares = daoExemplar.buscarTodos();

        ObservableList<Exemplar> data1 = FXCollections.observableArrayList(exemplares);
        TabelaExemplares.setItems(data1);

        List<Livro> livros = daoLivro.buscarTodos();

        ObservableList<Livro> data2 = FXCollections.observableArrayList(livros);
        ComboBoxLivros.setItems(data2);

    }
}