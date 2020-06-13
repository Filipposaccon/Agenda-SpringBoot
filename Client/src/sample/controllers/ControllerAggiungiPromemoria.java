package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAggiungiPromemoria implements Initializable {
    String idUtente1;
    @FXML
    TextField nomePromAdd;
    @FXML
    TextField descrizionePromAdd;
    @FXML
    TextField dataPromAdd;
    @FXML
    Button aggiungiProm;

    void setIdUtente(String idUtente) {
        idUtente1 = idUtente;


    }

    @FXML
    private void aggiungi() throws Exception {
        String link = "http://localhost:8080/addpromemoria/" + nomePromAdd.getText() + "," + descrizionePromAdd.getText() + "," + dataPromAdd.getText().replace("/", "-") + "," + idUtente1;
        link = link.replace(" ", "@");
        System.out.println(link);
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        System.out.println(connection.getResponseCode());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainPage.fxml"));
        Parent root = loader.load();
        ControllerMainPage controller2 = loader.getController();
        controller2.setIdUtente(idUtente1.toString());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Account Page");
        stage.show();
        Stage stage1 = (Stage) aggiungiProm.getScene().getWindow();
        stage1.close();

    }

    @FXML
    private void indietro() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainPage.fxml"));
        Parent root = loader.load();
        ControllerMainPage controller2 = loader.getController();
        controller2.setIdUtente(idUtente1.toString());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Account Page");
        stage.show();
        Stage stage1 = (Stage) aggiungiProm.getScene().getWindow();
        stage1.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
