package org.janbat.paczkuz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class edycjaSprzetuController {
    @FXML
    private TextField ladownoscPojazdField;
    @FXML
    private TextField pojazdNazwaFIeld;
    @FXML
    private TextField pojazdSpalanieField;
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
    @FXML
    public void initialize(){
        wczytajTowary();
        Pojazdy.wczytaj();
        pojazdy = Pojazdy.getPojazdyObs();
        //Towary.wczytaj();
        //dane = Towary.getDane();
        dane = FXCollections.observableList(towary);
        colCiezar.setCellValueFactory(new PropertyValueFactory<>("ciezar"));
        colNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        colilosc.setCellValueFactory(new PropertyValueFactory<>("ilosc"));

        colPojazdNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        colPojazdLadownosc.setCellValueFactory(new PropertyValueFactory<>("ladownosc"));
        colPojazdSpalanie.setCellValueFactory(new PropertyValueFactory<>("spalanie"));

        towaryTab.setItems(dane);
        pojazdTabela.setItems(pojazdy);
    }

    public void wczytajTowary(){
        File f = new File("towary.json");
        if(f.length() == 0){
            return;
        }
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while((line = br.readLine()) != null){
                result.append(line);
            }
        } catch (Exception e) {

        }
        System.out.println(result);
        Gson gson = new Gson();
        Type towarType = new TypeToken<ArrayList<Towar>>(){}.getType();
        ArrayList<Towar> t = gson.fromJson(result.toString(), towarType);
        towary = t;
        System.out.println(towary.get(0).getClass());
    }

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

    public void zapiszTowar(){
        if(nazwa.getText().trim().isEmpty() || ciezar.getText().trim().isEmpty() || ilosc.getText().trim().isEmpty()){
            System.out.println("Zadne pole nie moze byc puste!");
            return;
        }
        Towar t = new Towar();
        t.setNazwa(nazwa.getText());
        t.setCiezar(Integer.parseInt(ciezar.getText()));
        t.setIlosc(Integer.parseInt(ilosc.getText()));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        dane.add(t);
        Type towarType = new TypeToken<ArrayList<Towar>>(){}.getType();
        String json = gson.toJson(towary,towarType);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("towary.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Here");


    }

    public void usunPojazd(){
        System.out.println("usuwanie pojazdu");
        int idx = pojazdTabela.getSelectionModel().getSelectedIndex();
        pojazdy.remove(idx);
        Pojazdy.zapisz();
    }

    public void usunTowar(){
        int idx = towaryTab.getSelectionModel().getSelectedIndex();
        dane.remove(idx);
        //towary.remove(idx);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type towarType = new TypeToken<ArrayList<Towar>>(){}.getType();
        String json = gson.toJson(towary,towarType);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("towary.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
