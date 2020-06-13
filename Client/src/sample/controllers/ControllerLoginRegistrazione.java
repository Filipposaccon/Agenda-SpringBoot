package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerLoginRegistrazione implements Initializable {

    @FXML
    private Label labelError;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private Button register;

    String idUtente;

    @FXML
    private TextField nomeReg;
    @FXML
    private TextField cognomeReg;
    @FXML
    private TextField emailReg;
    @FXML
    private TextField telReg;
    @FXML
    private TextField userReg;
    @FXML
    private TextField passReg;
    @FXML
    private Label labelReg;


    @FXML
    private void login(ActionEvent event) throws Exception {
        String link = "http://localhost:8080/utentebyuser/" + user.getText();
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String msg = br.readLine();
        JSONObject jsonObject = new JSONObject(msg);
        if (!jsonObject.get("nome").toString().equals("null")) {
            if (jsonObject.get("password").toString().equals(pass.getText())) {

                idUtente = jsonObject.get("idUtente").toString();
                System.out.println(idUtente);
                labelError.setText("Utente corretto");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainPage.fxml"));
                Parent root = loader.load();

                //The following both lines are the only addition we need to pass the arguments

                ControllerMainPage controller2 = loader.getController();
                controller2.setIdUtente(idUtente);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Account Page");
                stage.show();
                Stage stage1 = (Stage) register.getScene().getWindow();
                // do what you have to do
                stage1.close();

            } else {
                labelError.setText("Password errata");
                pass.clear();
            }
        } else {

            labelError.setText("Utente non esiste");
            user.clear();
            pass.clear();
        }

    }

    @FXML
    private void openReg(ActionEvent event) throws Exception {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../gui/Registrazione.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void openLog(ActionEvent event) throws Exception {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void register(ActionEvent event) throws Exception {
        String link = "http://localhost:8080/utentereg/" + nomeReg.getText() + "," + cognomeReg.getText() + "," + emailReg.getText() + "," + telReg.getText() + "," + userReg.getText() + "," + passReg.getText();
        System.out.println(link);
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        if (connection.getResponseCode() == 200) {
            labelReg.setText("Utente creato correttamente");
            nomeReg.clear();
            cognomeReg.clear();
            emailReg.clear();
            telReg.clear();
            userReg.clear();
            passReg.clear();
        } else {
            labelReg.setText("Errore generico");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

