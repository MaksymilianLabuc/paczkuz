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

import java.io.File;
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

    /**
     * Inicjalizuje widok zlecenia przez wczytanie danych, konfigurację tabeli oraz ustawienie obsługi wyboru pojazdów.
     */
    @FXML
    public void initialize(){
        // Wczytanie danych
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

        // Obsługa wyboru pojazdu
        pojazdyChoice.setOnAction(event -> {
            int selectedIndex = pojazdyChoice.getSelectionModel().getSelectedIndex();
            Object selectedItem = pojazdyChoice.getSelectionModel().getSelectedItem();
            z.setWybranyPojazd(pojazdyChoice.getSelectionModel().getSelectedItem());
            maxLadownoscLabel.setText(String.valueOf(z.getWybranyPojazd().getLadownosc()));
        });
    }

    /**
     * Otwiera okno wysyłania maila z potwierdzeniem zlecenia.
     * @param event Obiekt zdarzenia akcji.
     * @throws IOException Występuje, gdy nie można załadować pliku FXML okna wysyłania maila.
     */
    public void sendMail(ActionEvent event) throws IOException {
        // Pobranie indeksu zaznaczonego zlecenia
        int idx = zapisaneTab.getSelectionModel().getSelectedIndex();

        // Sprawdzenie, czy zlecenie zostało wybrane
        if(zapisaneTab.getSelectionModel().getSelectedItem() == z) return;

        // Przypisanie zaznaczonego zlecenia
        z = zleceniaObs.get(idx);
        System.out.println(z);

        // Tworzenie tytułu i treści maila z potwierdzeniem zlecenia
        String title = "Potwierdzenie zlecenia";
        String towars = "";
        for (Towar t: z.towary) {
            towars += ("\t" + t.ilosc + "x " + t.nazwa + ", " + t.ciezar + "\n");
        }
        String body = "Start: " + z.start + " Cel: " + z.cel + "\n" + towars;

        // Ładowanie widoku do wysyłania maila
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("get-mail.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());

        // Ustawienie tytułu i treści maila w kontrolerze okna wysyłania maila
        GetMailController gmc = fxmlLoader.<GetMailController>getController();
        gmc.setMailTitle(title);
        gmc.setMailBody(body);

        // Ustawienie sceny i wyświetlenie okna wysyłania maila
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Wczytuje zaznaczone zlecenie i przypisuje jego wartości do odpowiednich pól w zakładce edycji zleceń.
     * Jeśli żadne zlecenie nie jest zaznaczone lub zlecenie zapisane jest już wczytane, to metoda nie wykonuje operacji.
     */
    public void wczytajZapisaneZlecenie(){
        // Pobiera indeks zaznaczonego wiersza reprezentującego zlecenie
        int idx = zapisaneTab.getSelectionModel().getSelectedIndex();

        // Sprawdza, czy zlecenie zostało zaznaczone i czy nie jest już wczytane
        if(zapisaneTab.getSelectionModel().getSelectedItem() == z) return;

        // Przypisanie zaznaczonego zlecenia
        z = zleceniaObs.get(idx);

        // Wypełnienie pól startu, celu, typu trasy i pojazdu z wczytanymi wartościami zlecenia
        start.setText(z.getStart());
        cel.setText(z.getCel());
        trasyChoice.getSelectionModel().select(z.getWybranyTypTrasy());
        pojazdyChoice.getSelectionModel().select(z.getWybranyPojazd());

        // Ustawienie maksymalnej ładowności pojazdu jako tekst
        maxLadownoscLabel.setText(String.valueOf(z.getWybranyPojazd().getLadownosc()));

        // Dodanie towarów ze zlecenia do listy towarów w zleceniu
        towaryWZleceniu.addAll(z.getTowary());

        //Automatycznie przełącza użytkownika do zakładki edycji zleceń
        zakladki.getSelectionModel().select(0);
    }

    /**
     * Usuwa zaznaczone zlecenie z listy zapisanych zleceń.
     */
    public void usunZapisaneZlecenie(){
        int idx = zapisaneTab.getSelectionModel().getSelectedIndex();
        Zlecenia.usun(idx);
    }

    /**
     * Usuwa zaznaczony towar z aktualnego zlecenia.
     */
    public void usunTowarZzlecenia(){
        int idx = dodaneTowryTab.getSelectionModel().getSelectedIndex();
        towaryWZleceniu.remove(idx);
    }

    /**
     * Dodaje wybrany towar do aktualnego zlecenia.
     * Jeśli żaden pojazd nie jest wybrany, wyświetla ostrzeżenie.
     * Jeśli wybrany towar przekracza dostępną pojemność pojazdu, wyświetla błąd.
     * Jeśli żaden towar nie jest wybrany, metoda nie wykonuje operacji.
     */
    public void dodajTowarDoZlecenia(){
        // Sprawdza, czy został wybrany pojazd
        if(z.getWybranyPojazd() == null){
            Alert nieWybranoPojzdu = new Alert(Alert.AlertType.WARNING);
            nieWybranoPojzdu.setTitle("Ostrzeżenie");
            nieWybranoPojzdu.setContentText("Nie wybrano pojazdu!!!");
            nieWybranoPojzdu.showAndWait();
            return;
        }
        // Pobiera indeks zaznaczonego wiersza reprezentującego towar
        int idx = listaTowarowTab.getSelectionModel().getSelectedIndex();

        // Sprawdza, czy został wybrany towar
        if(z.getObjetoscCalkowita()+towary.get(idx).getCiezar() < z.getWybranyPojazd().getLadownosc()) {
            towaryWZleceniu.add(towary.get(idx));
            z.setObjetoscCalkowita(z.getObjetoscCalkowita()+towary.get(idx).getCiezar());
            pozostalaLadownoscLabel.setText(String.valueOf(z.getWybranyPojazd().getLadownosc()-z.getObjetoscCalkowita()));
        }
        else{
            // Wyświetla komunikat o przekroczeniu dostępnej pojemności pojazdu
            Alert przekroczonaPojemnoscError = new Alert(Alert.AlertType.ERROR);
            przekroczonaPojemnoscError.setTitle("Error");
            przekroczonaPojemnoscError.setContentText("Przekroczono dostępną pojemność pojazdu!!!");
            przekroczonaPojemnoscError.showAndWait();
        }
    }

    /**
     * Zapisuje aktualne zlecenie.
     * Sprawdza, czy zlecenie już istnieje w liście zleceń.
     * Jeśli zlecenie nie istnieje, dodaje je do listy zleceń.
     * Jeśli zlecenie istnieje, aktualizuje jego dane w liście zleceń.
     */
    public void zapisz(){
        //Zlecenie z = new Zlecenie();

        // Sprawdza, czy zlecenie już istnieje w liście zleceń
        Boolean isnieje = false;
        for(int i=0; i<zleceniaObs.size(); i++){
            if(z.id == zleceniaObs.get(i).getId()){
                isnieje = true;
            }
        }
        // Aktualizuje dane zlecenia
        z.cel = cel.getText();
        z.start = start.getText();
        z.setTowary(towaryWZleceniuArrayList);
        z.setWybranyPojazd(pojazdyChoice.getSelectionModel().getSelectedItem());
        z.setWybranyTypTrasy(trasyChoice.getSelectionModel().getSelectedItem());

        // Jeśli zlecenie nie istnieje, dodaje je do listy zleceń
        // Jeśli zlecenie istnieje, aktualizuje jego dane w liście zleceń
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

    /**
     * Wczytuje listę pojazdów i ustawia ją jako dane źródłowe dla wyboru pojazdów.
     */
    public void wczytajPojazdy(){
        pojazdyObs = FXCollections.observableArrayList();
        for(int i=0; i<Pojazdy.getPojazdyObs().size(); i++) pojazdyObs.add(Pojazdy.getPojazdyObs().get(i));
        pojazdyChoice.setItems(pojazdyObs);
    }

    /**
     * Wczytuje listę dostępnych tras i ustawia je jako dane źródłowe dla wyboru tras.
     */
    public void wczytajTrasy(){
        typyTrasObs = typyTras.trasyObsList;
        trasyChoice.setItems(typyTrasObs);
    }

    /**
     * Przełącza interfejs użytkownika na okno logowania.
     * @param event Zdarzenie akcji, np. kliknięcie przycisku.
     * @throws IOException Wyjątek rzucany, gdy wystąpi błąd podczas ładowania pliku FXML.
     */
    @FXML
    public void switchToLoginWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"), HelloApplication.paczkaJezykowa);
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
     * Przełącza interfejs użytkownika na okno edycji sprzętu pojazdów.
     * @param event Zdarzenie akcji, np. kliknięcie przycisku.
     * @throws IOException Wyjątek rzucany, gdy wystąpi błąd podczas ładowania pliku FXML.
     */
    @FXML
    public void switchToEdycjaTowarow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edycjaSprzetuPojazdow.fxml"), HelloApplication.paczkaJezykowa);
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
     * Przełącza interfejs użytkownika na panel administratora, jeśli użytkownik ma uprawnienia administratora.
     * @param event Zdarzenie akcji, np. kliknięcie przycisku.
     * @throws IOException Wyjątek rzucany, gdy wystąpi błąd podczas ładowania pliku FXML.
     */
    @FXML
    public void switchToAdminPanel(ActionEvent event) throws IOException {
        if (LoginSystem.isAdmin()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminPanel.fxml"), HelloApplication.paczkaJezykowa);
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

    /**
     * Przełącza interfejs użytkownika na panel ustawień, jeśli użytkownik ma uprawnienia administratora.
     * @param event Zdarzenie akcji, np. kliknięcie przycisku.
     * @throws IOException Wyjątek rzucany, gdy wystąpi błąd podczas ładowania pliku FXML.
     */
    @FXML
    public void switchToUstawienia(ActionEvent event) throws IOException {
        if (LoginSystem.isAdmin()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ustawienia.fxml"));
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

    /**
     * Obsługuje zdarzenie kliknięcia na menu.
     * @param event Zdarzenie akcji, np. kliknięcie w menu.
     */
    @FXML
    public void menuClick(ActionEvent event){
        System.out.println("HERE");
    }
}
