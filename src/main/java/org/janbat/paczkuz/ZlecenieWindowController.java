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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ZlecenieWindowController {
    @FXML
    private TableView<?> listaTowarowTab;
    @FXML
    private TableView<?> dodaneTowryTab;
    @FXML
    private TableColumn<Towar, String> ciezarColA;

    @FXML
    private TableColumn<Towar, String> ciezarColB;

    @FXML
    private TableColumn<Towar, Integer> iloscColA;

    @FXML
    private TableColumn<Towar, Integer> iloscColB;


    @FXML
    private TableColumn<Towar, Integer> nazwaColA;

    @FXML
    private TableColumn<Towar, Integer> nazwaColB;

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
    private ObservableList towary;
    private ObservableList towaryWZleceniu;
    private Zlecenie z;
    @FXML
    public void initialize(){
        Pojazdy.wczytaj();
        Zlecenia.wczytaj();
        wczytajPojazdy();
        Towary.wczytaj();
        towary = Towary.getTowaryObs();
        z = new Zlecenie();
        towaryWZleceniu = FXCollections.observableList(z.towary);

        nazwaColA.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        iloscColA.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        ciezarColA.setCellValueFactory(new PropertyValueFactory<>("ciezar"));

        nazwaColB.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        iloscColB.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        ciezarColB.setCellValueFactory(new PropertyValueFactory<>("ciezar"));

        listaTowarowTab.setItems(towary);
        dodaneTowryTab.setItems(towaryWZleceniu);

    }
    public void dodajTowarDoZlecenia(){
        int idx = listaTowarowTab.getSelectionModel().getSelectedIndex();
        towaryWZleceniu.add(towary.get(idx));
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
