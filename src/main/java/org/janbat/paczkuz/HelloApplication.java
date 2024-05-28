package org.janbat.paczkuz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    public static ResourceBundle paczkaJezykowa = ResourceBundle.getBundle("org.janbat.paczkuz.language", Locale.getDefault());

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"), paczkaJezykowa);
        Scene scene = new Scene(fxmlLoader.load());

//        File cssFile = new File("src/main/resources/dark-mode.css");
//        scene.getStylesheets().add(cssFile.toURI().toString()); //zmiana na tryb ciemny

        stage.setTitle("PaczkUZ");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}