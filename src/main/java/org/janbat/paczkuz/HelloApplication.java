package org.janbat.paczkuz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    // Zasoby językowe dla interfejsu użytkownika
    public static ResourceBundle paczkaJezykowa = ResourceBundle.getBundle("org.janbat.paczkuz.language", Locale.getDefault());

    /**
     * Metoda startująca aplikację, ładująca widok logowania.
     * @param stage Główny obszar okna aplikacji.
     * @throws IOException Wyjątek, który może wystąpić podczas wczytywania pliku FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Ładowanie widoku logowania
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"), paczkaJezykowa);
        Scene scene = new Scene(fxmlLoader.load());
//        File cssFile = new File("src/main/resources/dark-mode.css");
//        scene.getStylesheets().add(cssFile.toURI().toString()); //zmiana na tryb ciemny

        // Ustawienia głównego okna aplikacji
        stage.setTitle("PaczkUZ");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Metoda główna, uruchamiająca aplikację.
     * @param args Argumenty wiersza poleceń.
     */
    public static void main(String[] args) {
        launch();
    }
}