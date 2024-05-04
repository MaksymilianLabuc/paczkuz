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
        System.out.println(result);
        Gson gson = new Gson();
        Type trasaType = new TypeToken<ArrayList<typTrasy>>(){}.getType();
        trasyArrayList = gson.fromJson(result.toString(), trasaType);
        trasyObsList = FXCollections.observableList(trasyArrayList);
    }
    public static void zapisz(){
        System.out.println("zapisywanie pojazdu");
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

    public static ArrayList<typTrasy> getTrasyArrayList() {
        return trasyArrayList;
    }

    public static ObservableList<typTrasy> getTrasyObsList() {
        return trasyObsList;
    }
}
