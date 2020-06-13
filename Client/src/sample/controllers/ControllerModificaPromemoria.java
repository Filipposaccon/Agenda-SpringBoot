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

public class ControllerModificaPromemoria implements Initializable {


    @FXML
    TextField nomeProm;
    @FXML
    TextField descrizioneProm;
    @FXML
    TextField dataProm;

    @FXML
    Button IndietroProm;


    JSONObject jsonObject;
    String idProme1;

    void setIdProme(String idProme) {
        idProme1 = idProme;
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void loadData() throws Exception {
        String link = "http://localhost:8080/promemoriabyidPromemoria/" + idProme1;
        System.out.println(link);
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String msg = br.readLine();
        System.out.println(msg);
        jsonObject = new JSONObject(msg);
        nomeProm.setText(jsonObject.getString("nome"));
        descrizioneProm.setText(jsonObject.getString("descrizione"));
        dataProm.setText(jsonObject.getString("data"));
    }

    @FXML
    private void modifica() throws Exception {
        String link = "http://localhost:8080/modpromemoria/" + idProme1 + "," + nomeProm.getText() + "," + descrizioneProm.getText() + "," + dataProm.getText().replace("/", "-") + "," + jsonObject.getInt("idUtente");
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
        String idUtente = "" + jsonObject.getInt("idUtente");
        controller2.setIdUtente(idUtente);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Account Page");
        stage.show();
        Stage stage1 = (Stage) IndietroProm.getScene().getWindow();
        stage1.close();

    }

    @FXML
    private void elimina() throws Exception {
        String link = "http://localhost:8080/deletepromemoria/" + idProme1;
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
        String idUtente = "" + jsonObject.getInt("idUtente");
        controller2.setIdUtente(idUtente);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Account Page");
        stage.show();
        Stage stage1 = (Stage) IndietroProm.getScene().getWindow();
        stage1.close();

    }

    @FXML
    private void indietro() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainPage.fxml"));
        Parent root = loader.load();

        //The following both lines are the only addition we need to pass the arguments

        ControllerMainPage controller2 = loader.getController();
        String idUtente = "" + jsonObject.getInt("idUtente");
        controller2.setIdUtente(idUtente);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Account Page");
        stage.show();
        Stage stage1 = (Stage) IndietroProm.getScene().getWindow();
        stage1.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

}

