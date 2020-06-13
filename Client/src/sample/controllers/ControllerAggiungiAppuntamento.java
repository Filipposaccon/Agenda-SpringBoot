package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAggiungiAppuntamento implements Initializable {
    String idUtente1;
    JSONObject jsonapp;

    @FXML
    Button indietroAppAdd;
    @FXML
    TextField nomeAppAdd;
    @FXML
    TextField descrizioneAppAdd;
    @FXML
    TextField dataAppAdd;
    @FXML
    ComboBox<String> comboAppAdd;
    @FXML
    ListView<String> listviewAppAdd;

    int times = 0;
    int lenght = 0;
    JSONArray listUtenti;

    void setIdUtente(String idUtente) {
        idUtente1 = idUtente;
        try {
            caricaCombo();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Stage stage1 = (Stage) indietroAppAdd.getScene().getWindow();
        stage1.close();

    }

    @FXML
    private void caricaCombo() throws Exception {
        String link = "http://localhost:8080/utenti";
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
        listUtenti = new JSONArray(msg);
        for (int i = 0; i < listUtenti.length(); i++) {
            String sup = listUtenti.getJSONObject(i).getInt("idUtente") + "  " + listUtenti.getJSONObject(i).getString("nome") + "  " + listUtenti.getJSONObject(i).getString("cognome");
            comboAppAdd.getItems().add(sup);
        }

    }


    @FXML
    private void addList() throws Exception {

        System.out.println(comboAppAdd.getValue());
        if (times == 0) {
            listviewAppAdd.getItems().add(comboAppAdd.getValue());
            times++;
            lenght++;
        } else {
            String[] elem = comboAppAdd.getValue().split("  ");
            boolean presente = false;
            for (int i = 0; i < lenght; i++) {
                String[] parts = listviewAppAdd.getItems().get(i).split("  ");
                if (elem[0].equals(parts[0])) {
                    presente = true;
                }
            }
            if (!presente) {
                listviewAppAdd.getItems().add(comboAppAdd.getValue());
                lenght++;
            }
        }

    }

    @FXML
    private void aggiungi() throws Exception {
        String link = "http://localhost:8080/addappuntamento/" + nomeAppAdd.getText() + "," + descrizioneAppAdd.getText() + "," + dataAppAdd.getText().replace("/", "-");
        link = link.replace(" ", "@");
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
        jsonapp = new JSONObject(msg);
        System.out.println(connection.getResponseCode());
        for (int i = 0; i < lenght; i++) {
            String[] parts = listviewAppAdd.getItems().get(i).split("  ");
            String link1 = "http://localhost:8080/addutentiapp/" + parts[0] + "," + jsonapp.getLong("idAppuntamento");
            System.out.println(link1);
            URL url1 = new URL(link1);
            HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
            connection1.setRequestMethod("GET");
            connection1.setConnectTimeout(5000);
            connection1.setReadTimeout(5000);
            System.out.println(connection1.getResponseCode());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainPage.fxml"));
        Parent root = loader.load();
        ControllerMainPage controller2 = loader.getController();
        controller2.setIdUtente(idUtente1.toString());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Account Page");
        stage.show();
        Stage stage1 = (Stage) indietroAppAdd.getScene().getWindow();
        stage1.close();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
