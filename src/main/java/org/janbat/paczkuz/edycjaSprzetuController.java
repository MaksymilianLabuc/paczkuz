package org.janbat.paczkuz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class edycjaSprzetuController {
    @FXML
    private Parent root;
    @FXML
    private TextField ladownoscPojazdField;
    @FXML
    private TextField pojazdNazwaFIeld;
    @FXML
    private TextField pojazdSpalanieField;
    @FXML
    private TextField trasaPlacaTextField;
    @FXML
    private TextField trasaNazwaTextField;
    @FXML
    private TextField trasaCenaTextField;
    @FXML
    TextArea nazwa;
    @FXML
    TextArea ciezar;
    @FXML
    TextArea ilosc;
    private ArrayList<Towar> towary = new ArrayList<>();
    //private List<Towar> towary = new ArrayList();
    @FXML
    TableView towaryTab;
    @FXML
    private TableView<?> pojazdTabela;
    @FXML
    private TableView<typTrasy> trasyTab;
    @FXML
    private TableColumn<typTrasy, Double> trasaCenaCol;
    @FXML
    private TableColumn<typTrasy, String> trasaNazwaCol;
    @FXML
    private TableColumn<typTrasy, Double> trasaPlacaCol;
    @FXML
    private TableColumn<Towar, String> colCiezar;
    @FXML
    private TableColumn<Towar, Integer> colNazwa;
    @FXML
    private TableColumn<Towar, Integer> colilosc;
    @FXML
    private TableColumn<Pojazd, Double> colPojazdLadownosc;
    @FXML
    private TableColumn<Pojazd, String> colPojazdNazwa;
    @FXML
    private TableColumn<Pojazd, Double> colPojazdSpalanie;
    //private ObservableList<Towar> dane = FXCollections.observableArrayList();
    private ObservableList<Towar> dane;
    private ObservableList pojazdy;
    private ObservableList<typTrasy> trasy;

    /**
     * Inicjalizuje dane tabeli towarów, pojazdów i tras oraz ustawia wartości kolumn.
     */
    @FXML
    public void initialize(){
        Towary.wczytaj();
        //wczytajTowary();
        Pojazdy.wczytaj();
        typyTras.wczytaj();

        pojazdy = Pojazdy.getPojazdyObs();
        dane = Towary.getTowaryObs();
        trasy = typyTras.getTrasyObsList();

        colCiezar.setCellValueFactory(new PropertyValueFactory<>("ciezar"));
        colNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        colilosc.setCellValueFactory(new PropertyValueFactory<>("ilosc"));

        colPojazdNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        colPojazdLadownosc.setCellValueFactory(new PropertyValueFactory<>("ladownosc"));
        colPojazdSpalanie.setCellValueFactory(new PropertyValueFactory<>("spalanie"));

        trasaCenaCol.setCellValueFactory(new PropertyValueFactory<>("cenaViatoll"));
        trasaPlacaCol.setCellValueFactory(new PropertyValueFactory<>("wyplataDlaKierowcy"));
        trasaNazwaCol.setCellValueFactory(new PropertyValueFactory<>("nazwa"));

        towaryTab.setItems(dane);
        pojazdTabela.setItems(pojazdy);
        trasyTab.setItems(trasy);
    }

    public void wczytajTowary(){

    }

    /**
     * Zapisuje nową trasę na podstawie wprowadzonych danych.
     * Jeśli pola nazwa, cena i płaca są wypełnione, trasa jest dodawana do listy i zapisywana.
     */
    public void zapiszTrasy(){
        typTrasy trasa = new typTrasy();
        if(!trasaNazwaTextField.getText().trim().isEmpty() && !trasaPlacaTextField.getText().trim().isEmpty() && !trasaCenaTextField.getText().trim().isEmpty()){
            trasa.setCenaViatoll(Double.parseDouble(trasaCenaTextField.getText()));
            trasa.setWyplataDlaKierowcy(Double.parseDouble(trasaPlacaTextField.getText()));
            trasa.setNazwa(trasaNazwaTextField.getText());
            trasy.add(trasa);
            typyTras.zapisz();
        }
    }

    /**
     * Zapisuje nowy pojazd na podstawie wprowadzonych danych.
     * Jeśli pola nazwa, ładowność i spalanie są wypełnione, pojazd jest dodawany do listy i zapisywany.
     */
    public void zapiszPojazd(){
        if(pojazdNazwaFIeld.getText().trim().isEmpty() || ladownoscPojazdField.getText().trim().isEmpty() || pojazdSpalanieField.getText().trim().isEmpty()){
            System.out.println("Zadne pole nie moze byc puste!");
            return;
        }
        String pNazwa = pojazdNazwaFIeld.getText();
        double pladownosc = Double.parseDouble(ladownoscPojazdField.getText());
        double pSpalanie = Double.parseDouble(pojazdSpalanieField.getText());
        Pojazd p = new Pojazd();
        p.setNazwa(pNazwa);
        p.setLadownosc(pladownosc);
        p.setSpalanie(pSpalanie);
        pojazdy.add(p);
        Pojazdy.zapisz();
    }

    /**
     * Zapisuje nowy towar na podstawie wprowadzonych danych.
     * Jeśli pola nazwa, ciężar i ilość są wypełnione, towar jest dodawany do listy i zapisywany.
     */
    public void zapiszTowar(){
        if(nazwa.getText().trim().isEmpty() || ciezar.getText().trim().isEmpty() || ilosc.getText().trim().isEmpty()){
            System.out.println("Zadne pole nie moze byc puste!");
            return;
        }
        Towar t = new Towar();
        t.setNazwa(nazwa.getText());
        t.setCiezar(Integer.parseInt(ciezar.getText()));
        t.setIlosc(Integer.parseInt(ilosc.getText()));
        Towary.zapisz(t);
    }

    /**
     * Usuwa wybraną trasę z tabeli i zapisuje zmiany.
     */
    public void usunTrase(){
        int idx = trasyTab.getSelectionModel().getSelectedIndex();
        trasy.remove(idx);
        typyTras.zapisz();
    }

    /**
     * Usuwa wybrany pojazd z tabeli i zapisuje zmiany.
     */
    public void usunPojazd(){
        System.out.println("usuwanie pojazdu");
        int idx = pojazdTabela.getSelectionModel().getSelectedIndex();
        pojazdy.remove(idx);
        Pojazdy.zapisz();
    }

    /**
     * Usuwa wybrany towar z tabeli.
     */
    public void usunTowar(){
        int idx = towaryTab.getSelectionModel().getSelectedIndex();
        Towary.usun(idx);
    }

    /**
     * Przełącza widok na okno edycji zleceń.
     * Ładuje nową scenę z odpowiednim plikiem FXML i motywem graficznym.
     * @param event Zdarzenie akcji wywołane przez użytkownika.
     * @throws IOException Jeśli wystąpi błąd podczas ładowania pliku FXML.
     */
    @FXML
    public void switchToEdycjaZlecen(ActionEvent event) throws IOException {
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


}
