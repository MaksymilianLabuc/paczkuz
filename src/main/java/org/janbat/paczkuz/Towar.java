package org.janbat.paczkuz;

public class Towar {
    public String nazwa;
    public int ciezar;
    //public int ilosc;

    public Towar(String n, int c, int i){
        nazwa = n;
        ciezar = c;
        //ilosc = i;
    }
    public Towar(){}
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getCiezar() {
        return ciezar;
    }

    public void setCiezar(int ciezar) {
        this.ciezar = ciezar;
    }

//    public int getIlosc() {
//        return ilosc;
//    }

//    public void setIlosc(int ilosc) {
//        this.ilosc = ilosc;
//    }

}
