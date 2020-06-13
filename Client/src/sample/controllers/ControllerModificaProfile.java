package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerModificaProfile implements Initializable {
    String idUtente1;

    void setIdUtente(String idUtente) {
        idUtente1 = idUtente;

        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    TextField nomeProf;
    @FXML
    TextField cognomeProf;
    @FXML
    TextField emailProf;
    @FXML
    TextField telProf;
    @FXML
    TextField userProf;
    @FXML
    TextField passProf;
    @FXML
    Button modificaProf;


    @FXML
    private void loadData() throws Exception {
        String link = "http://localhost:8080/utentebyid/" + idUtente1;
        System.out.println(link);
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        System.out.println(connection.getResponseCode());
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String msg = br.readLine();
        System.out.println(msg);
        JSONObject jsonApp = new JSONObject(msg);
        nomeProf.setText(jsonApp.getString("nome"));
        cognomeProf.setText(jsonApp.getString("cognome"));
        emailProf.setText(jsonApp.getString("email"));
        telProf.setText(jsonApp.getString("telefono"));
        userProf.setText(jsonApp.getString("username"));
        passProf.setText(jsonApp.getString("password"));

    }

    @FXML
    private void modifica() throws Exception {
        String link = "http://localhost:8080/modificaUtente/" + idUtente1 + "," + nomeProf.getText() + "," + cognomeProf.getText() + "," + emailProf.getText() + "," + telProf.getText() + "," + userProf.getText() + "," + passProf.getText();
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
        controller2.setIdUtente(idUtente1);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Account Page");
        stage.show();
        Stage stage1 = (Stage) modificaProf.getScene().getWindow();
        stage1.close();
    }

    @FXML
    private void indietro() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainPage.fxml"));
        Parent root = loader.load();
        ControllerMainPage controller2 = loader.getController();
        controller2.setIdUtente(idUtente1);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Account Page");
        stage.show();
        Stage stage1 = (Stage) modificaProf.getScene().getWindow();
        stage1.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }
}
