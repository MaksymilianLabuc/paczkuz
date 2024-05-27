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
    public void initialize(){
        MotywChoiceBox.getItems().add("Dark mode");
        MotywChoiceBox.getItems().add("Light mode");
    }
    @FXML
    public void switchToLoginWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        if(MotywChoiceBox.getValue().equals("Dark mode")){
            File cssFile = new File("src/main/resources/dark-mode.css");
            scene.getStylesheets().add(cssFile.toURI().toString()); //zmiana na tryb ciemny
        }
        stage.setScene(scene);
        stage.show();
    }
}
