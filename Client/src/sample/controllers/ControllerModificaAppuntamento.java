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

public class ControllerModificaAppuntamento implements Initializable {

    String idApp1;
    String idUtente1;
    JSONObject jsonApp;

    void setIdApp(String idApp, String idUtente) {
        idUtente1 = idUtente;
        idApp1 = idApp;
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    TextField nomeAppMod;
    @FXML
    TextField descrizioneAppMod;
    @FXML
    TextField dataAppMod;
    @FXML
    Button indietroAppMod;
    @FXML
    ListView<String> listviewAppMod;
    @FXML
    ComboBox<String> comboAppMod;
    JSONArray listUtenti;
    int times = 0;
    int lenght = 0;

    @FXML
    private void loadData() throws Exception {
        String link = "http://localhost:8080//getappuntamentibyidAppuntamento/" + idApp1;
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
        jsonApp = new JSONObject(msg);
        nomeAppMod.setText(jsonApp.getString("nome"));
        descrizioneAppMod.setText(jsonApp.getString("descrizione"));
        dataAppMod.setText(jsonApp.getString("data"));
        caricaCombo();
        caricaPartecipanti();
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
        Stage stage1 = (Stage) indietroAppMod.getScene().getWindow();
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
            comboAppMod.getItems().add(sup);
        }

    }

    @FXML
    private void caricaPartecipanti() throws Exception {
        String link = "http://localhost:8080//getpartecipantibyidAppuntamento/" + idApp1;
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
            listviewAppMod.getItems().add(sup);
        }
        lenght = listUtenti.length();

    }

    @FXML
    private void addList() throws Exception {

        System.out.println(comboAppMod.getValue());

        String[] elem = comboAppMod.getValue().split("  ");
        boolean presente = false;
        for (int i = 0; i < lenght; i++) {
            String[] parts = listviewAppMod.getItems().get(i).split("  ");
            if (elem[0].equals(parts[0])) {
                presente = true;
            }
        }
        if (!presente) {
            listviewAppMod.getItems().add(comboAppMod.getValue());
            lenght++;
        }
    }


    @FXML
    private void removeList() throws Exception {

        System.out.println(comboAppMod.getValue());

        String[] elem = comboAppMod.getValue().split("  ");
        for (int i = 0; i < lenght; i++) {
            String[] parts = listviewAppMod.getItems().get(i).split("  ");
            if (elem[0].equals(parts[0])) {
                listviewAppMod.getItems().remove(i);
                lenght = lenght - 1;
            }
        }
    }

    @FXML
    private void eliminaApp() throws Exception {
        String link = "http://localhost:8080//eliminaApp/" + idApp1;
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
        Stage stage1 = (Stage) indietroAppMod.getScene().getWindow();
        stage1.close();

    }

    @FXML
    private void modificaApp() throws Exception {
        String link = "http://localhost:8080/modificaApp/" + idApp1 + "," + nomeAppMod.getText() + "," + descrizioneAppMod.getText() + "," + dataAppMod.getText().replace("/", "-");
        link = link.replace(" ", "@");
        System.out.println(link);
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        System.out.println(connection.getResponseCode());
        modificaPart();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainPage.fxml"));
        Parent root = loader.load();
        ControllerMainPage controller2 = loader.getController();
        ;
        controller2.setIdUtente(idUtente1);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Account Page");
        stage.show();
        Stage stage1 = (Stage) indietroAppMod.getScene().getWindow();
        stage1.close();

    }

    @FXML
    private void modificaPart() throws Exception {
        String result = "";
        for (int i = 0; i < lenght; i++) {
            if (i != lenght - 1) {
                String[] parts = listviewAppMod.getItems().get(i).split("  ");
                result = result + parts[0] + "-";
            } else {
                String[] parts = listviewAppMod.getItems().get(i).split("  ");
                result = result + parts[0];
            }
        }

        String link = "http://localhost:8080/modificaPartecipanti/" + result + "," + idApp1;
        System.out.println(link);
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        System.out.println(connection.getResponseCode());

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }
}
