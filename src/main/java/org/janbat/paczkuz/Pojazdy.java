package org.janbat.paczkuz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;

public class Pojazdy {
    private static ArrayList<Pojazd> pojazdyArrayList = new ArrayList<>();
    private static ObservableList<Pojazd> pojazdyObs;

    /**
     * Ładuje listę pojazdów z pliku JSON. Jeśli plik jest pusty, inicjalizuje pustą listę.
     */
    public static void wczytaj(){
        File f = new File("pojazdy.json");
        if(f.length()==0){
            pojazdyObs = FXCollections.observableList(pojazdyArrayList);
            return;
        }
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while((line=br.readLine())!=null){
                result.append(line);
            }
        } catch (Exception e){

        }
        Gson gson = new Gson();
        Type pojazdType = new TypeToken<ArrayList<Pojazd>>(){}.getType();
        pojazdyArrayList = gson.fromJson(result.toString(), pojazdType);
        pojazdyObs = FXCollections.observableList(pojazdyArrayList);
    }

    /**
     * Zapisuje listę pojazdów do pliku JSON.
     */
    public static void zapisz(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type pojazdType = new TypeToken<ArrayList<Pojazd>>(){}.getType();
        String json = gson.toJson(pojazdyArrayList,pojazdType);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("pojazdy.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Zwraca listę pojazdów jako ArrayList.
     * @return lista pojazdów.
     */
    public static ArrayList<Pojazd> getPojazdyArrayList() {
        return pojazdyArrayList;
    }

    /**
     * Zwraca listę pojazdów jako ObservableList.
     * @return obserwowalna lista pojazdów.
     */
    public static ObservableList<Pojazd> getPojazdyObs() {
        return pojazdyObs;
    }

}
