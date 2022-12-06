package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.dao.DaoAutor;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LivroController implements Initializable {

    @FXML
    private TextField CampoCodigo;
    @FXML
    private TextField CampoTitulo;
    @FXML
    private ComboBox<Autor> ComboBoxAutores;
    @FXML
    private ListView<Livro> ListaLivros;
    @FXML
    private TableView<Exemplar> TabelaEmprestimos; // TODO: Que porra é essa
    @FXML
    private Button BotaoSalvar;
    @FXML
    private Button BotaoInserir;
    @FXML
    private Button BotaoAlterar;
    @FXML
    private Button BotaoExcluir;

    private DaoLivro daoLivro = new DaoLivro();
    private DaoAutor daoAutor = new DaoAutor();
    private DaoExemplar daoExemplar = new DaoExemplar();
    private Livro livro;
    private boolean inserindo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preencherLista();

    }

    @FXML
    private void Salvar_Click(ActionEvent event) {

        Autor autor = ComboBoxAutores.getSelectionModel().getSelectedItem();

        livro.setTitulo(CampoTitulo.getText());
        livro.setAutor(autor);
        autor.adicionarLstLivro(livro);

        if (inserindo) {
            daoLivro.inserir(livro);
        } else {
            daoLivro.alterar(livro);
        }

        preencherLista();
        editar(false);
        // TODO: Fazer um entidade.getId() para mostrar o código que foi gerado
    }

    @FXML
    private void Alterar_Click(ActionEvent event) {

        editar(true);
        inserindo = false;

    }

    @FXML
    private void Excluir_Click(ActionEvent event) {

        daoLivro.apagar(livro);
        preencherLista();
    }

    @FXML
    private void Inserir_Click(ActionEvent event) {

        editar(true);
        inserindo = true;
        livro = new Livro();

        // Deixa os campos em branco
        CampoCodigo.setText("");
        CampoTitulo.setText("");
        ComboBoxAutores.setValue(null);

        // Deixa o cursor nesse campo para digitar
        CampoTitulo.requestFocus();

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

        this.livro = ListaLivros.getSelectionModel().getSelectedItem();

        if (livro == null)
            return;

        CampoCodigo.setText(livro.getCodigo().toString());
        CampoTitulo.setText(livro.getTitulo());
        ComboBoxAutores.setValue(livro.getAutor());

    }

    private void editar(boolean habilitar) {

        ListaLivros.setDisable(habilitar); // Desabilita
        TabelaEmprestimos.setDisable(habilitar); // Desabilita
        CampoTitulo.setDisable(!habilitar); // Habilita
        ComboBoxAutores.setDisable(!habilitar);
        BotaoExcluir.setDisable(habilitar);
        BotaoInserir.setDisable(habilitar);
        BotaoAlterar.setDisable(habilitar);
        BotaoSalvar.setDisable(!habilitar);

    }

    private void preencherLista() {

        List<Livro> livros = daoLivro.buscarTodos();

        ObservableList<Livro> data1 = FXCollections.observableArrayList(livros);
        ListaLivros.setItems(data1);

        List<Exemplar> exemplares = daoExemplar.buscarTodos();

        ObservableList<Exemplar> data2 = FXCollections.observableArrayList(exemplares);
        TabelaEmprestimos.setItems(data2);

        List<Autor> autores = daoAutor.buscarTodos();

        ObservableList<Autor> data3 = FXCollections.observableArrayList(autores);
        ComboBoxAutores.setItems(data3);

    }
}