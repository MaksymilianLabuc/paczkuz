package org.janbat.paczkuz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class typyTras {
    public static ArrayList<typTrasy> trasyArrayList = new ArrayList<>();
    public static ObservableList<typTrasy> trasyObsList;

    /**
     * Metoda wczytująca typy tras z pliku JSON do listy.
     * Jeśli plik jest pusty lub nie istnieje, tworzy nową pustą listę.
     */
    public static void wczytaj(){
        File f = new File("trasy.json");
        if(f.length() == 0 || !f.exists()){
            trasyArrayList = new ArrayList<typTrasy>();
            trasyObsList = FXCollections.observableList(trasyArrayList);
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
        Type trasaType = new TypeToken<ArrayList<typTrasy>>(){}.getType();
        trasyArrayList = gson.fromJson(result.toString(), trasaType);
        trasyObsList = FXCollections.observableList(trasyArrayList);
    }

    /**
     * Metoda zapisująca typy tras do pliku JSON.
     */
    public static void zapisz(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type trasaType = new TypeToken<ArrayList<typTrasy>>(){}.getType();
        String json = gson.toJson(trasyArrayList,trasaType);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("trasy.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Zwraca listę typów tras jako ArrayList.
     * @return lista typów tras.
     */
    public static ArrayList<typTrasy> getTrasyArrayList() {
        return trasyArrayList;
    }

    /**
     * Zwraca listę typów tras jako ObservableList.
     * @return obserwowalna lista typów tras.
     */
    public static ObservableList<typTrasy> getTrasyObsList() {
        return trasyObsList;
    }
}
