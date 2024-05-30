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
    public static ArrayList<Zlecenie> zleceniaArrayList;
    public static ObservableList<Zlecenie> zleceniaObs;

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

        }
        System.out.println(result);
        Gson gson = new Gson();
        Type towarType = new TypeToken<ArrayList<Zlecenie>>(){}.getType();
        zleceniaArrayList = gson.fromJson(result.toString(), towarType);
        zleceniaObs = FXCollections.observableList(zleceniaArrayList);
        System.out.println("Zlecenia: "+zleceniaObs);
    }

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
            throw new RuntimeException(e);
        }
    }
    public static void zapiszWszystkie(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type towarType = new TypeToken<ArrayList<Zlecenie>>(){}.getType();
        String json = gson.toJson(zleceniaArrayList,towarType);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("zlecenia.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Zlecenie> getZleceniaArrayList() {
        return zleceniaArrayList;
    }

    public static ObservableList<Zlecenie> getZleceniaObs() {
        return zleceniaObs;
    }
}
