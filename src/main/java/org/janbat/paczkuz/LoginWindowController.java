package org.janbat.paczkuz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginWindowController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField TextArea1;
    @FXML
    private PasswordField PasswordField1;
    LoginSystem log=new LoginSystem();
    private ResourceBundle bundle;
    @FXML
    public void initialize(){
        // Ladowanie paczki z tlumaczeniami
        // Musi byc dodana przy ladowaniu plikow fxml
        Locale locale = new Locale("jp", "JP");
        bundle = ResourceBundle.getBundle("org.janbat.paczkuz.language", locale);
        TextArea1.setFocusTraversable(true);
        PasswordField1.setFocusTraversable(true);
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onLoginButtonClick(){System.out.println("cos");}
   /* @FXML
    public void switchToZlecenieWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }*/
    public void login(ActionEvent event) throws IOException {
         if (LoginSystem.login(TextArea1.getText(),PasswordField1.getText())) {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"), bundle);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             Scene scene = new Scene(fxmlLoader.load());
             stage.setScene(scene);
             stage.show();
         }

    }

    public void register(ActionEvent event) throws IOException {
        if (LoginSystem.register(TextArea1.getText(),PasswordField1.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"), bundle);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
    }
}