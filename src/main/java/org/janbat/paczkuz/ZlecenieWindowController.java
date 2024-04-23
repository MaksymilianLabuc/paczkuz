package org.janbat.paczkuz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ZlecenieWindowController {
    @FXML
    private ChoiceBox<String> pojazdyChoice;
    @FXML
    ListView<String> towaryListView;
    @FXML
    TextField cel;
    @FXML
    TextField start;
    @FXML
    private Parent root;
//    private ObservableList<Pojazd> pojazdyObs;
    private ObservableList<String> pojazdyObs;
    private MenuItem[] pojazdyItemy;
    @FXML
    public void initialize(){
        Pojazdy.wczytaj();
        Zlecenia.wczytaj();
        Towary.wczytaj();
        wczytajPojazdy();

    }
    public void zapisz(){
        Zlecenie z = new Zlecenie();
        z.cel = cel.getText();
        z.start = start.getText();
        z.towary.add(Towary.getTowaryArrayList().get(0));
        Zlecenia.zapisz(z);
    }

    public void wczytajPojazdy(){
        pojazdyObs = FXCollections.observableArrayList();
        for(int i=0; i<Pojazdy.getPojazdyObs().size(); i++) pojazdyObs.add(Pojazdy.getPojazdyObs().get(i).getNazwa());
        pojazdyChoice.setItems(pojazdyObs);

    }

    @FXML
    public void switchToLoginWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToEdycjaTowarow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edycjaSprzetuPojazdow.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void menuClick(ActionEvent event){
        System.out.println("HERE");
    }
}
