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

    /**
     * Konstruktor tworzący nowe zlecenie.
     * @param zleceniaObs lista zleceń używana do wygenerowania unikalnego identyfikatora dla nowego zlecenia.
     */
    public Zlecenie(ObservableList<Zlecenie> zleceniaObs){
        towary = new ArrayList<Towar>();
        this.start = "";
        this.cel = "";
        this.oplacone = "NIE";
        if(zleceniaObs.isEmpty() == false) this.id = zleceniaObs.getLast().getId()+1;
        else this.id = 0;
    }

    /**
     * Zwraca lokalizację początkową.
     * @return lokalizacja początkowa.
     */
    public String getStart() {
        return start;
    }

    /**
     * Ustawia lokalizację początkową.
     * @param start nowa lokalizacja początkowa.
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Zwraca lokalizację docelową.
     * @return lokalizacja docelowa.
     */
    public String getCel() {
        return cel;
    }

    /**
     * Ustawia lokalizację docelową.
     * @param cel nowa lokalizacja docelowa.
     */
    public void setCel(String cel) {
        this.cel = cel;
    }

    /**
     * Zwraca wybrany pojazd.
     * @return wybrany pojazd.
     */
    public Pojazd getWybranyPojazd() {
        return wybranyPojazd;
    }

    /**
     * Ustawia wybrany pojazd.
     * @param wybranyPojazd nowy wybrany pojazd.
     */
    public void setWybranyPojazd(Pojazd wybranyPojazd) {
        this.wybranyPojazd = wybranyPojazd;
    }

    /**
     * Zwraca wybrany typ trasy.
     * @return wybrany typ trasy.
     */
    public typTrasy getWybranyTypTrasy() {
        return wybranyTypTrasy;
    }

    /**
     * Ustawia wybrany typ trasy.
     * @param wybranyTypTrasy nowy wybrany typ trasy.
     */
    public void setWybranyTypTrasy(typTrasy wybranyTypTrasy) {
        this.wybranyTypTrasy = wybranyTypTrasy;
    }

    /**
     * Zwraca cenę za kilometr.
     * @return cena za kilometr.
     */
    public double getCenaZaKm() {
        return cenaZaKm;
    }

    /**
     * Ustawia cenę za kilometr.
     * @param cenaZaKm nowa cena za kilometr.
     */
    public void setCenaZaKm(double cenaZaKm) {
        this.cenaZaKm = cenaZaKm;
    }

    /**
     * Zwraca całkowitą objętość ładunku.
     * @return całkowita objętość ładunku.
     */
    public double getObjetoscCalkowita() {
        return objetoscCalkowita;
    }

    /**
     * Ustawia całkowitą objętość ładunku.
     * @param objetoscCalkowita nowa całkowita objętość ładunku.
     */
    public void setObjetoscCalkowita(double objetoscCalkowita) {
        this.objetoscCalkowita = objetoscCalkowita;
    }

    /**
     * Zwraca listę towarów w zleceniu.
     * @return lista towarów w zleceniu.
     */
    public ArrayList<Towar> getTowary() {
        return towary;
    }

    /**
     * Ustawia listę towarów w zleceniu.
     * @param towary nowa lista towarów w zleceniu.
     */
    public void setTowary(ArrayList<Towar> towary) {
        this.towary = towary;
    }

    /**
     * Zwraca przewidywany dystans.
     * @return przewidywany dystans.
     */
    public Double getDystans() {
        return dystans;
    }

    /**
     * Ustawia przewidywany dystans.
     * @param dystans nowy przewidywany dystans.
     */
    public void setDystans(Double dystans) {
        this.dystans = dystans;
    }

    /**
     * Zwraca unikalny identyfikator zlecenia.
     * @return unikalny identyfikator zlecenia.
     */
    public int getId() {
        return id;
    }

    /**
     * Ustawia unikalny identyfikator zlecenia.
     * @param id nowy unikalny identyfikator zlecenia.
     */
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
