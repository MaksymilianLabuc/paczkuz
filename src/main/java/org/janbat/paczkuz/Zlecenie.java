package org.janbat.paczkuz;

import java.util.ArrayList;

public class Zlecenie {
    public String start;
    public String cel;
    public Pojazd wybranyPojazd;
    public typTrasy wybranyTypTrasy;
    public double cenaZaKm;
    public double objetoscCalkowita;
    public ArrayList<Towar> towary;
    public Double dystans;


    public Zlecenie(){
        towary = new ArrayList<Towar>();
        this.start = "";
        this.cel = "";
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
}
