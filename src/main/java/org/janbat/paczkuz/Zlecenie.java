package org.janbat.paczkuz;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Queue;

public class Zlecenie {
    public int id;
    public String start;
    public String cel;
    public Pojazd wybranyPojazd;
    public typTrasy wybranyTypTrasy;
    public double cenaZaKm;
    public double objetoscCalkowita;
    public ArrayList<Towar> towary;
    public Double dystans;
    public Double cenaCalkowita;
    public String oplacone;


    public Zlecenie(ObservableList<Zlecenie> zleceniaObs){
        towary = new ArrayList<Towar>();
        this.start = "";
        this.cel = "";
        this.oplacone = "NIE";
        if(zleceniaObs.isEmpty() == false) this.id = zleceniaObs.getLast().getId()+1;
        else this.id = 0;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public Pojazd getWybranyPojazd() {
        return wybranyPojazd;
    }

    public void setWybranyPojazd(Pojazd wybranyPojazd) {
        this.wybranyPojazd = wybranyPojazd;
    }

    public typTrasy getWybranyTypTrasy() {
        return wybranyTypTrasy;
    }

    public void setWybranyTypTrasy(typTrasy wybranyTypTrasy) {
        this.wybranyTypTrasy = wybranyTypTrasy;
    }

    public double getCenaZaKm() {
        return cenaZaKm;
    }

    public void setCenaZaKm(double cenaZaKm) {
        this.cenaZaKm = cenaZaKm;
    }

    public double getObjetoscCalkowita() {
        return objetoscCalkowita;
    }

    public void setObjetoscCalkowita(double objetoscCalkowita) {
        this.objetoscCalkowita = objetoscCalkowita;
    }

    public ArrayList<Towar> getTowary() {
        return towary;
    }

    public void setTowary(ArrayList<Towar> towary) {
        this.towary = towary;
    }

    public Double getDystans() {
        return dystans;
    }

    public void setDystans(Double dystans) {
        this.dystans = dystans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getCenaCalkowita() {
        return cenaCalkowita;
    }

    public void setCenaCalkowita(Double cenaCalkowita) {
        this.cenaCalkowita = cenaCalkowita;
    }

    public String getOplacone() {
        return oplacone;
    }

    public void setOplacone(String oplacone) {
        this.oplacone = oplacone;
    }
}
