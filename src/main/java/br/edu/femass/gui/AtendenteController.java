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

public class AtendenteController implements Initializable {
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }    

    @FXML
    private void Leitores_Click(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Leitor.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
    
            Stage stage = new Stage();
            stage.setTitle("Leitores");
            stage.setScene(scene);
            stage.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    @FXML
    private void Emprestimo_Click(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Emprestimo.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
    
            Stage stage = new Stage();
            stage.setTitle("Emprestimo");
            stage.setScene(scene);
            stage.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}
