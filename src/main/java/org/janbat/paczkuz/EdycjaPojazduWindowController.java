package org.janbat.paczkuz;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EdycjaPojazduWindowController {
    @FXML
    private TextField nazwa;
    @FXML
    private TextField ladownosc;
    @FXML
    private TextField spalanie;

    /**
     * Zamyka okno
     */
    @FXML
    public void zamknij(){
        Stage stage = (Stage) nazwa.getScene().getWindow();
        stage.close();
    }

    public TextField getNazwa() {
        return nazwa;
    }

    public TextField getLadownosc() {
        return ladownosc;
    }

    public TextField getSpalanie() {
        return spalanie;
    }
}
