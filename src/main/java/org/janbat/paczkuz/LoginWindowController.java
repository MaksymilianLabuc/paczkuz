package org.janbat.paczkuz;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginWindowController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onLoginButtonClick(){System.out.println("Login");}
}