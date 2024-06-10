package org.janbat.paczkuz;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EdycjaTowaruController {
    @FXML
    private TextField nazwa;
    @FXML
    private TextField ciezar;

    /**
     * Zamyka okno
     */
    public void zamknij(){
        Stage stage = (Stage) nazwa.getScene().getWindow();
        stage.close();
    }

    public TextField getNazwa() {
        return nazwa;
    }

    public TextField getCiezar() {
        return ciezar;
    }

}
