package org.janbat.paczkuz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
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

    /**
     * Inicjalizuje kontroler, ustawia fokus na polach tekstowych.
     */
    @FXML
    public void initialize(){
        TextArea1.setFocusTraversable(true);
        PasswordField1.setFocusTraversable(true);
    }

    /**
     * Obsługuje kliknięcie przycisku "Hello", ustawia tekst powitalny.
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    /**
     * Obsługuje kliknięcie przycisku logowania.
     */
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

    /**
     * Obsługuje proces logowania użytkownika.
     * Jeśli logowanie zakończy się sukcesem, przełącza scenę na okno zleceń.
     * @param event Zdarzenie akcji.
     * @throws IOException Jeśli wystąpi błąd wejścia/wyjścia podczas ładowania nowej sceny.
     */
    public void login(ActionEvent event) throws IOException {
         if (LoginSystem.login(TextArea1.getText(),PasswordField1.getText())) {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"), HelloApplication.paczkaJezykowa);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             Scene scene = new Scene(fxmlLoader.load());
             if (Ustawienia.getMotyw().equals("Dark mode")){
                 File cssFile = new File("src/main/resources/dark-mode.css");
                 scene.getStylesheets().add(cssFile.toURI().toString()); //zmiana na tryb ciemny
             }
             stage.setScene(scene);
             stage.show();
         }

    }

    /**
     * Obsługuje proces rejestracji nowego użytkownika.
     * Jeśli rejestracja zakończy się sukcesem, przełącza scenę na okno zleceń.
     * @param event Zdarzenie akcji.
     * @throws IOException Jeśli wystąpi błąd wejścia/wyjścia podczas ładowania nowej sceny.
     */
    public void register(ActionEvent event) throws IOException {
        if (LoginSystem.register(TextArea1.getText(),PasswordField1.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"), HelloApplication.paczkaJezykowa);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            if (Ustawienia.getMotyw().equals("Dark mode")){
                File cssFile = new File("src/main/resources/dark-mode.css");
                scene.getStylesheets().add(cssFile.toURI().toString()); //zmiana na tryb ciemny
            }
            stage.setScene(scene);
            stage.show();
        }
    }
}