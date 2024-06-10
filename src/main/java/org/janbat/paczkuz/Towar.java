package org.janbat.paczkuz;

public class Towar {
    public String nazwa;
    public int ciezar;
    //public int ilosc;

    /**
     * Konstruktor tworzący obiekt klasy Towar.
     * @param n nazwa towaru.
     * @param c ciężar towaru.
     * @param i ilość towaru.
     */
    public Towar(String n, int c, int i){
        nazwa = n;
        ciezar = c;
        //ilosc = i;
    }

    /**
     * Domyślny konstruktor tworzący pusty obiekt klasy Towar.
     */
    public Towar(){}

    /**
     * Zwraca nazwę towaru.
     * @return nazwa towaru.
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia nazwę towaru.
     * @param nazwa nowa nazwa towaru.
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Zwraca ciężar towaru.
     * @return ciężar towaru.
     */
    public int getCiezar() {
        return ciezar;
    }

    /**
     * Ustawia ciężar towaru.
     * @param ciezar nowy ciężar towaru.
     */
    public void setCiezar(int ciezar) {
        this.ciezar = ciezar;
    }


      /**
     * Zwraca ilość towaru.
     * @return ilość towaru.
     */
//    public int getIlosc() {
//        return ilosc;
//    }

      /**
     * Ustawia ilość towaru.
     * @param ilosc nowa ilość towaru.
     */
//    public void setIlosc(int ilosc) {
//        this.ilosc = ilosc;
//    }


}
