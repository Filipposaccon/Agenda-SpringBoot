package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMainPage implements Initializable {
    @FXML
    private ListView<String> promList;
    @FXML
    private ListView<String> appList;
    String idUtente1;
    JSONArray listProm;
    JSONArray listApp;
    @FXML
    Button addProm;
    @FXML
    Button addApp;

    void setIdUtente(String idUtente) {
        idUtente1 = idUtente;
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void loadData() throws Exception {
        String link = "http://localhost:8080/promemoriabyidUtente/" + idUtente1;
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
        listProm = new JSONArray(msg);
        for (int i = 0; i < listProm.length(); i++) {
            String sup = listProm.getJSONObject(i).getInt("idPromemoria") + "  " + listProm.getJSONObject(i).getString("nome") + "  " + listProm.getJSONObject(i).getString("data");
            promList.getItems().add(sup);
        }
        String link1 = "http://localhost:8080/getappuntamentibyidUtente/" + idUtente1;
        System.out.println(link1);
        URL url1 = new URL(link1);
        HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
        connection1.setRequestMethod("GET");
        connection1.setConnectTimeout(5000);
        connection1.setReadTimeout(5000);
        System.out.println(connection1.getResponseCode());
        BufferedReader br1;
        br1 = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
        String msg1 = br1.readLine();
        System.out.println(msg1);
        listApp = new JSONArray(msg1);
        for (int i = 0; i < listApp.length(); i++) {
            String sup = listApp.getJSONObject(i).getInt("idAppuntamento") + "  " + listApp.getJSONObject(i).getString("nome") + "  " + listApp.getJSONObject(i).getString("data");
            appList.getItems().add(sup);
        }


    }

    @FXML
    public void handleMouseClickProm(MouseEvent arg0) throws Exception {
        String[] parts = promList.getSelectionModel().getSelectedItem().split("  ");
        System.out.println(parts[0]);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModificaPromemoria.fxml"));
        Parent root = loader.load();
        ControllerModificaPromemoria controller3 = loader.getController();
        controller3.setIdProme(parts[0]);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard Promemoria");
        stage.show();
        Stage stage1 = (Stage) addApp.getScene().getWindow();
        // do what you have to do
        stage1.close();

    }

    @FXML
    public void handleMouseClickApp(MouseEvent arg0) throws Exception {
        System.out.println(appList.getSelectionModel().getSelectedItem());
        String[] parts = appList.getSelectionModel().getSelectedItem().split("  ");
        System.out.println(parts[0]);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModificaAppuntamento.fxml"));
        Parent root = loader.load();
        ControllerModificaAppuntamento controller3 = loader.getController();
        controller3.setIdApp(parts[0], idUtente1);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard Appuntamento");
        stage.show();
        Stage stage1 = (Stage) addApp.getScene().getWindow();
        // do what you have to do
        stage1.close();
    }

    @FXML
    private void aggiungiProm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AggiungiPromemoria.fxml"));
        Parent root = loader.load();
        ControllerAggiungiPromemoria controller4 = loader.getController();
        controller4.setIdUtente(idUtente1);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Aggiungi Promemoria");
        stage.show();
        Stage stage1 = (Stage) addApp.getScene().getWindow();
        // do what you have to do
        stage1.close();

    }

    @FXML
    private void aggiungiApp() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AggiungiAppuntamento.fxml"));
        Parent root = loader.load();
        ControllerAggiungiAppuntamento controller4 = loader.getController();
        controller4.setIdUtente(idUtente1);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Aggiungi Appuntamento");
        stage.show();
        Stage stage1 = (Stage) addApp.getScene().getWindow();
        // do what you have to do
        stage1.close();

    }

    @FXML
    private void openProfile() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModificaProfilo.fxml"));
        Parent root = loader.load();
        ControllerModificaProfile controller4 = loader.getController();
        controller4.setIdUtente(idUtente1);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Profilo");
        stage.show();
        Stage stage1 = (Stage) addApp.getScene().getWindow();
        // do what you have to do
        stage1.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
