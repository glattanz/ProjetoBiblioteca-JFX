package br.edu.femass.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BibliotecarioController implements Initializable {
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }    

    @FXML
    private void Livros_Click(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Livro.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
    
            Stage stage = new Stage();
            stage.setTitle("Livros");
            stage.setScene(scene);
            stage.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    @FXML
    private void CadastrarAutores_Click(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Autor.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
    
            Stage stage = new Stage();
            stage.setTitle("Autor");
            stage.setScene(scene);
            stage.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    @FXML
    private void Exemplar_Click(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Exemplar.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
    
            Stage stage = new Stage();
            stage.setTitle("Exemplar");
            stage.setScene(scene);
            stage.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}
