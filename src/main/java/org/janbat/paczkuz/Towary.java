package org.janbat.paczkuz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Towary {
    public static ArrayList<Towar> towaryArrayList = new ArrayList<>();
    public static ObservableList<Towar> towaryObs;

    /**
     * Metoda wczytująca towary z pliku JSON do listy.
     * Jeśli plik jest pusty lub nie istnieje, tworzy nową pustą listę.
     */
    public static void wczytaj(){
        File f = new File("towary.json");
        if(f.length() == 0 || !f.exists()){
            towaryArrayList = new ArrayList<Towar>();
            towaryObs = FXCollections.observableList(towaryArrayList);
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
        Gson gson = new Gson();
        Type towarType = new TypeToken<ArrayList<Towar>>(){}.getType();
        towaryArrayList = gson.fromJson(result.toString(), towarType);
        towaryObs = FXCollections.observableList(towaryArrayList);
    }

    /**
     * Metoda zapisująca towar do listy oraz do pliku JSON.
     * @param t towar do zapisania.
     */
    public static void zapisz(Towar t){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        towaryObs.add(t);
        Type towarType = new TypeToken<ArrayList<Towar>>(){}.getType();
        String json = gson.toJson(towaryArrayList,towarType);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("towary.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda zapisująca liste wszystkich towarów
     */
    public static void zapiszWszystkie(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type towarType = new TypeToken<ArrayList<Towar>>(){}.getType();
        String json = gson.toJson(towaryArrayList,towarType);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("towary.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda usuwająca towar z listy oraz aktualizująca plik JSON.
     * @param idx indeks towaru do usunięcia.
     */
    public static void usun(int idx){
        towaryObs.remove(idx);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type towarType = new TypeToken<ArrayList<Towar>>(){}.getType();
        String json = gson.toJson(towaryArrayList,towarType);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("towary.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Zwraca listę towarów jako ArrayList.
     * @return lista towarów.
     */
    public static ArrayList<Towar> getTowaryArrayList() {
        return towaryArrayList;
    }

    /**
     * Zwraca listę towarów jako ObservableList.
     * @return obserwowalna lista towarów.
     */
    public static ObservableList<Towar> getTowaryObs() {
        return towaryObs;
    }
}
