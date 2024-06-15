package org.janbat.paczkuz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UstawieniaWindowController {
    @FXML
    private Parent root;
    @FXML
    private ChoiceBox<String> MotywChoiceBox;
    @FXML
    private ChoiceBox<String> jezykChoiceBox;
    @FXML

    /**
     * Inicjalizuje wybór motywu i języka na podstawie bieżących ustawień.
     */
    public void initialize(){
        MotywChoiceBox.getItems().add("Dark mode");
        MotywChoiceBox.getItems().add("Light mode");
        MotywChoiceBox.setValue(Ustawienia.getMotyw());
        jezykChoiceBox.getItems().add("Polski");
        jezykChoiceBox.getItems().add("Japanese");
        jezykChoiceBox.getItems().add("English");
        jezykChoiceBox.setValue(Ustawienia.getJezyk());

        // Obsługa wyboru języka
        jezykChoiceBox.setOnAction(event -> {
            String wybranyJezyk = jezykChoiceBox.getSelectionModel().getSelectedItem();
            Ustawienia.setJezyk(wybranyJezyk);
        });
    }

    /**
     * Przełącza na okno logowania.
     * @param event zdarzenie akcji.
     * @throws IOException wyjątek wejścia/wyjścia.
     */
    @FXML
    public void switchToLoginWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"), HelloApplication.paczkaJezykowa);
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());

        // Ustawienie wybranego motywu
        Ustawienia.setMotyw(MotywChoiceBox.getValue());
        if(Ustawienia.getMotyw().equals("Dark mode")){
            File cssFile = new File("src/main/resources/dark-mode.css");
            scene.getStylesheets().add(cssFile.toURI().toString()); //zmiana na tryb ciemny
        }
        stage.setScene(scene);
        stage.show();
    }
}
