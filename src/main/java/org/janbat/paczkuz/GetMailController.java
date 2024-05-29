package org.janbat.paczkuz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GetMailController {
    private String mailTitle;
    private String mailBody;
    @FXML
    Parent root;

    @FXML
    TextField getMailTF;

    /**
     * Metoda obsługująca kliknięcie przycisku Wyślij w widoku
     */
    public void sendMail() {
        String mail = getMailTF.getText();
        Mailer.sendMail(mail, mailTitle, mailBody);
        getMailTF.setText("");
    }

    /**
     * Metoda przekierowująca użytkownika do widoku zleceń
     * @param event
     * @throws IOException
     */
    public void switchToZlecenieWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"), HelloApplication.paczkaJezykowa);
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        if (Ustawienia.getMotyw().equals("Dark mode")){
            File cssFile = new File("src/main/resources/dark-mode.css");
            scene.getStylesheets().add(cssFile.toURI().toString()); //zmiana na tryb ciemny
        }
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda ustawiająca tytuł maila
     * @param mailTitle
     */

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    /**
     * Metoda ustawiająca treść maila
     * @param mailBody
     */

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }
}
