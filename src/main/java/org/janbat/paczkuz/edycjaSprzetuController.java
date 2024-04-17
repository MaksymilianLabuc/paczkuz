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
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class edycjaSprzetuController {
    @FXML
    TextArea nazwa;
    @FXML
    TextArea ciezar;
    @FXML
    TextArea ilosc;
    private ArrayList<Towar> towary = new ArrayList<>();
    //private List towary = new ArrayList<Towar>();
    @FXML
    TableView towaryTab;
    @FXML
    private TableColumn<Towar, String> colCiezar;
    @FXML
    private TableColumn<Towar, Integer> colNazwa;
    @FXML
    private TableColumn<Towar, Integer> colilosc;
    private ObservableList<Towar> dane = FXCollections.observableArrayList();
    //private ObservableList<Towar> dane = FXCollections.observableList(towary);
    @FXML
    public void initialize(){
        wczytajTowary();
        colCiezar.setCellValueFactory(new PropertyValueFactory<>("ciezar"));
        colNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        colilosc.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
//        for(int i=0; i< towary.size(); i++){
//            towaryTab.getItems().add(towary.get(i));
//        }
         for(int i=0; i< towary.size(); i++){
            dane.add(towary.get(i));
         }
        towaryTab.setItems(dane);
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
        towary.add(t);
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

    public void usunTowar(){
        int idx = towaryTab.getSelectionModel().getSelectedIndex();
        towary.remove(idx);
        dane.remove(idx);
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
