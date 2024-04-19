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
    public static ArrayList<Towar> towaryArrayList;
    public static ObservableList<Towar> towaryObs;

    public static void wczytaj(){
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
        towaryArrayList = gson.fromJson(result.toString(), towarType);
        towaryObs = FXCollections.observableList(towaryArrayList);
    }

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
        System.out.println("Here");
    }
    public static void usun(int idx){
        towaryObs.remove(idx);
    }

    public static ArrayList<Towar> getTowaryArrayList() {
        return towaryArrayList;
    }

    public static ObservableList<Towar> getTowaryObs() {
        return towaryObs;
    }
}
