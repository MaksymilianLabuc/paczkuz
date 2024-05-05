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
import java.util.ArrayList;

public class ZlecenieWindowController {
    @FXML
    private TableView<Towar> listaTowarowTab;
    @FXML
    private TableView<Towar> dodaneTowryTab;

    @FXML
    private TableView<Zlecenie> zapisaneTab;

    @FXML
    private TableColumn<Zlecenie, String> startZapisaneCol;
    @FXML
    private TableColumn<Zlecenie, String> celZapisaneCol;
    @FXML
    private TableColumn<Zlecenie, Double> dystansZapisaneCol;
    @FXML
    private TableColumn<Zlecenie, Double> cenaZapisaneCol;

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
    private ChoiceBox<typTrasy> trasyChoice;
    @FXML
    ListView<String> towaryListView;
    @FXML
    TextField cel;
    @FXML
    TextField start;
    @FXML
    private Label maxLadownoscLabel;
    @FXML
    private Label pozostalaLadownoscLabel;
    @FXML
    private Parent root;
//    private ObservableList<Pojazd> pojazdyObs;
    private ObservableList<String> pojazdyObs;
    private MenuItem[] pojazdyItemy;
    private ObservableList<Towar> towary;
    private ArrayList<Towar> towaryWZleceniuArrayList;
    private ObservableList<Towar> towaryWZleceniu;
    private ObservableList<typTrasy> typyTrasObs;
    private ObservableList<Zlecenie> zleceniaObs;
    private Zlecenie z;
    @FXML
    public void initialize(){
        Pojazdy.wczytaj();
        Zlecenia.wczytaj();
        wczytajPojazdy();
        Towary.wczytaj();
        typyTras.wczytaj();
        wczytajTrasy();
        towary = Towary.getTowaryObs();
        z = new Zlecenie();
        towaryWZleceniuArrayList = z.getTowary();
        towaryWZleceniu = FXCollections.observableList(towaryWZleceniuArrayList);
        zleceniaObs = Zlecenia.getZleceniaObs();

        nazwaColA.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        iloscColA.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        ciezarColA.setCellValueFactory(new PropertyValueFactory<>("ciezar"));

        nazwaColB.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        iloscColB.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        ciezarColB.setCellValueFactory(new PropertyValueFactory<>("ciezar"));

        startZapisaneCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        celZapisaneCol.setCellValueFactory(new PropertyValueFactory<>("cel"));
        dystansZapisaneCol.setCellValueFactory(new PropertyValueFactory<>("dystans"));
        cenaZapisaneCol.setCellValueFactory(new PropertyValueFactory<>("cenaZaKm"));

        listaTowarowTab.setItems(towary);
        dodaneTowryTab.setItems(towaryWZleceniu);
        zapisaneTab.setItems(zleceniaObs);

        pojazdyChoice.setOnAction(event -> {
            int selectedIndex = pojazdyChoice.getSelectionModel().getSelectedIndex();
            Object selectedItem = pojazdyChoice.getSelectionModel().getSelectedItem();
            z.setWybranyPojazd(Pojazdy.getPojazdyArrayList().get(selectedIndex));
            maxLadownoscLabel.setText(String.valueOf(z.getWybranyPojazd().getLadownosc()));
        });
    }
    public void dodajTowarDoZlecenia(){
        if(z.getWybranyPojazd() == null){
            Alert nieWybranoPojzdu = new Alert(Alert.AlertType.WARNING);
            nieWybranoPojzdu.setTitle("Ostrzeżenie");
            nieWybranoPojzdu.setContentText("Nie wybrano pojazdu!!!");
            nieWybranoPojzdu.showAndWait();
            return;
        }
        int idx = listaTowarowTab.getSelectionModel().getSelectedIndex();
        if(z.getObjetoscCalkowita()+towary.get(idx).getCiezar() < z.getWybranyPojazd().getLadownosc()) {
            towaryWZleceniu.add(towary.get(idx));
            z.setObjetoscCalkowita(z.getObjetoscCalkowita()+towary.get(idx).getCiezar());
            pozostalaLadownoscLabel.setText(String.valueOf(z.getWybranyPojazd().getLadownosc()-z.getObjetoscCalkowita()));
        }
        else{
            Alert przekroczonaPojemnoscError = new Alert(Alert.AlertType.ERROR);
            przekroczonaPojemnoscError.setTitle("Error");
            przekroczonaPojemnoscError.setContentText("Przekroczono dostępną pojemność pojazdu!!!");
            przekroczonaPojemnoscError.showAndWait();
        }
    }
    public void zapisz(){
        Zlecenie z = new Zlecenie();
        z.cel = cel.getText();
        z.start = start.getText();
        z.setTowary(towaryWZleceniuArrayList);
        Zlecenia.zapisz(z);
    }
    public void wczytajPojazdy(){
        pojazdyObs = FXCollections.observableArrayList();
        for(int i=0; i<Pojazdy.getPojazdyObs().size(); i++) pojazdyObs.add(Pojazdy.getPojazdyObs().get(i).getNazwa());
        pojazdyChoice.setItems(pojazdyObs);

    }
    public void wczytajTrasy(){
        typyTrasObs = typyTras.trasyObsList;
        trasyChoice.setItems(typyTrasObs);
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
