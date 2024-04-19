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

public class LoginWindowController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField TextArea1;
    @FXML
    private PasswordField PasswordField1;
    LoginSystem log=new LoginSystem();
    @FXML
    public void initialize(){
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
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"));
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             Scene scene = new Scene(fxmlLoader.load());
             stage.setScene(scene);
             stage.show();
         }

    }

    public void register(ActionEvent event) throws IOException {
        if (LoginSystem.register(TextArea1.getText(),PasswordField1.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
    }
}