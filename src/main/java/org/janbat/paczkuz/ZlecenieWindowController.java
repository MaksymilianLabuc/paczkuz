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
import java.util.Collection;

public class ZlecenieWindowController {
    @FXML
    private TabPane zakladki;
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
    private ChoiceBox<Pojazd> pojazdyChoice;
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
    private ObservableList<Pojazd> pojazdyObs;
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
        zleceniaObs = Zlecenia.getZleceniaObs();
        z = new Zlecenie(zleceniaObs);
        towaryWZleceniuArrayList = z.getTowary();
        towaryWZleceniu = FXCollections.observableList(towaryWZleceniuArrayList);

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
            z.setWybranyPojazd(pojazdyChoice.getSelectionModel().getSelectedItem());
            maxLadownoscLabel.setText(String.valueOf(z.getWybranyPojazd().getLadownosc()));
        });
    }

    public void sendMail(ActionEvent event) throws IOException {
        int idx = zapisaneTab.getSelectionModel().getSelectedIndex();
        if(zapisaneTab.getSelectionModel().getSelectedItem() == z) return;
        z = zleceniaObs.get(idx);
        System.out.println(z);
        String title = "Potwierdzenie zlecenia";
        String towars = "";
        for (Towar t: z.towary) {
            towars += ("\t" + t.ilosc + "x " + t.nazwa + ", " + t.ciezar + "\n");
        }
        String body = "Start: " + z.start + " Cel: " + z.cel + "\n" + towars;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("get-mail.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        GetMailController gmc = fxmlLoader.<GetMailController>getController();
        gmc.setMailTitle(title);
        gmc.setMailBody(body);
        stage.setScene(scene);
        stage.show();
    }

    public void wczytajZapisaneZlecenie(){
        // Pobiera index wiersza wybranego w tabeli który reprezentuje zlecenie i wcztyje jego wartości do
        // odpowiednich pól w zakładce edycji zleceń
        int idx = zapisaneTab.getSelectionModel().getSelectedIndex();
        if(zapisaneTab.getSelectionModel().getSelectedItem() == z) return;
        z = zleceniaObs.get(idx);
        start.setText(z.getStart());
        cel.setText(z.getCel());
        trasyChoice.getSelectionModel().select(z.getWybranyTypTrasy());
        pojazdyChoice.getSelectionModel().select(z.getWybranyPojazd());
        maxLadownoscLabel.setText(String.valueOf(z.getWybranyPojazd().getLadownosc()));
        towaryWZleceniu.addAll(z.getTowary());
        //Automatycznie przełącza użytkownika do zakłądki edycji zleceń
        zakladki.getSelectionModel().select(0);
    }
    public void usunZapisaneZlecenie(){
        int idx = zapisaneTab.getSelectionModel().getSelectedIndex();
        Zlecenia.usun(idx);
    }
    public void usunTowarZzlecenia(){
        int idx = dodaneTowryTab.getSelectionModel().getSelectedIndex();
        towaryWZleceniu.remove(idx);
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
        //Zlecenie z = new Zlecenie();
        Boolean isnieje = false;
        for(int i=0; i<zleceniaObs.size(); i++){
            if(z.id == zleceniaObs.get(i).getId()){
                isnieje = true;
            }
        }
        z.cel = cel.getText();
        z.start = start.getText();
        z.setTowary(towaryWZleceniuArrayList);
        z.setWybranyPojazd(pojazdyChoice.getSelectionModel().getSelectedItem());
        z.setWybranyTypTrasy(trasyChoice.getSelectionModel().getSelectedItem());
        if(!isnieje){
            Zlecenia.zapisz(z);
        } else if (isnieje) {
            int idx = 0;
            for(int i=0; i<zleceniaObs.size(); i++){
                if(zleceniaObs.get(i).getId() == z.getId()) idx = zleceniaObs.get(i).getId();
            }
            zleceniaObs.set(idx,z);
        }

    }
    public void wczytajPojazdy(){
        pojazdyObs = FXCollections.observableArrayList();
        for(int i=0; i<Pojazdy.getPojazdyObs().size(); i++) pojazdyObs.add(Pojazdy.getPojazdyObs().get(i));
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
    public void switchToAdminPanel(ActionEvent event) throws IOException {
        if (LoginSystem.isAdmin()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminPanel.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    public void switchToUstawienia(ActionEvent event) throws IOException {
        if (LoginSystem.isAdmin()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ustawienia.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    public void menuClick(ActionEvent event){
        System.out.println("HERE");
    }
}
