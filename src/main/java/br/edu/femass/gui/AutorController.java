package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AutorController implements Initializable {

    @FXML
    private TextField CampoNome;
    @FXML
    private TextField CampoSobrenome;
    @FXML
    private TextField CampoNacionalidade;
    @FXML
    private ListView<Autor> ListaAutores;
    @FXML
    private Button BotaoSalvar;
    @FXML
    private Button BotaoInserir;
    @FXML
    private Button BotaoAlterar;
    @FXML
    private Button BotaoExcluir;

    private DaoAutor daoAutor = new DaoAutor();
    private Autor autor;
    private boolean inserindo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preencherLista();

    }

    @FXML
    private void Salvar_Click(ActionEvent event) {

        autor.setNome(CampoNome.getText());
        autor.setSobrenome(CampoSobrenome.getText());
        autor.setNacionalidade(CampoNacionalidade.getText());

        if (inserindo) {
            daoAutor.inserir(autor);
        } else {
            daoAutor.alterar(autor);
        }

        preencherLista();
        dadosEmBranco();
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

        daoAutor.apagar(autor);
        dadosEmBranco();
        preencherLista();
    }

    @FXML
    private void Inserir_Click(ActionEvent event) {

        editar(true);
        inserindo = true;
        autor = new Autor();

        // Deixa os campos em branco
        // CampoNome.setText("");
        // CampoSobrenome.setText("");
        // CampoNacionalidade.setText("");

        // Deixa o cursor nesse campo para digitar
        CampoNome.requestFocus();

        dadosEmBranco();
        preencherLista();

    }

    @FXML
    private void ListarAutores_KeyPressed(KeyEvent event) {

        exibirDados();

    }

    @FXML
    private void ListarAutores_MouseClicked(MouseEvent event) {

        exibirDados();

    }

    private void dadosEmBranco() {

        CampoNome.setText("");
        CampoSobrenome.setText("");
        CampoNacionalidade.setText("");

    }

    private void exibirDados() {

        this.autor = ListaAutores.getSelectionModel().getSelectedItem();

        if (autor == null)
            return;

        CampoNome.setText(autor.getNome());
        CampoSobrenome.setText(autor.getSobrenome());
        CampoNacionalidade.setText(autor.getNacionalidade());

    }

    private void editar(boolean habilitar) {

        ListaAutores.setDisable(habilitar); // Desabilita
        CampoNome.setDisable(!habilitar); // Habilita
        CampoSobrenome.setDisable(!habilitar);
        CampoNacionalidade.setDisable(!habilitar);
        BotaoSalvar.setDisable(!habilitar);
    }

    private void preencherLista() {

        List<Autor> autores = daoAutor.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        ListaAutores.setItems(data);

    }
}