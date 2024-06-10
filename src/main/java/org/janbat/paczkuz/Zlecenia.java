package org.janbat.paczkuz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Zlecenia {
    // Lista zlecen
    public static ArrayList<Zlecenie> zleceniaArrayList;
    // Lista obserwowalna zlecen
    public static ObservableList<Zlecenie> zleceniaObs;

    /**
     * Metoda wczytująca zlecenia z pliku JSON.
     */
    public static void wczytaj(){
        File f = new File("zlecenia.json");
        if(f.length() == 0 || !f.exists()){
            zleceniaArrayList = new ArrayList<Zlecenie>();
            zleceniaObs = FXCollections.observableList(zleceniaArrayList);
            System.out.println("Zlecenia: "+zleceniaObs);
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
            // Obsługa błędów odczytu pliku
        }
        System.out.println(result);
        Gson gson = new Gson();
        Type towarType = new TypeToken<ArrayList<Zlecenie>>(){}.getType();
        zleceniaArrayList = gson.fromJson(result.toString(), towarType);
        zleceniaObs = FXCollections.observableList(zleceniaArrayList);
        System.out.println("Zlecenia: "+zleceniaObs);
    }

    /**
     * Metoda zapisująca zlecenie do pliku JSON.
     * @param z obiekt zlecenia do zapisania.
     */
    public static void zapisz(Zlecenie z){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        zleceniaObs.add(z);
        Type towarType = new TypeToken<ArrayList<Zlecenie>>(){}.getType();
        String json = gson.toJson(zleceniaArrayList,towarType);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("zlecenia.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            // Obsługa błędów zapisu do pliku
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda usuwająca zlecenie z listy i zapisująca zmiany do pliku JSON.
     * @param idx indeks zlecenia do usunięcia.
     */
    public static void usun(int idx){
        //Przyjmuje index zlecenia do usunięcia i zapisuje zaktualizowany plik
        Zlecenia.getZleceniaObs().remove(idx);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type towarType = new TypeToken<ArrayList<Zlecenie>>(){}.getType();
        String json = gson.toJson(zleceniaArrayList,towarType);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("zlecenia.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            // Obsługa błędów zapisu do pliku
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda zwracająca listę zleceń.
     * @return lista zleceń.
     */
    public static ArrayList<Zlecenie> getZleceniaArrayList() {
        return zleceniaArrayList;
    }

    /**
     * Metoda zwracająca obserwowalną listę zleceń.
     * @return obserwowalna lista zleceń.
     */
    public static ObservableList<Zlecenie> getZleceniaObs() {
        return zleceniaObs;
    }
}
