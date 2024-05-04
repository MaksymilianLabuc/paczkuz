package org.janbat.paczkuz;

public class typTrasy {
    public String nazwa;
    public double wyplataDlaKierowcy;
    public double cenaViatoll;

    @Override
    public String toString() {
        return nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getWyplataDlaKierowcy() {
        return wyplataDlaKierowcy;
    }

    public void setWyplataDlaKierowcy(double wyplataDlaKierowcy) {
        this.wyplataDlaKierowcy = wyplataDlaKierowcy;
    }

    public double getCenaViatoll() {
        return cenaViatoll;
    }

    public void setCenaViatoll(double cenaViatoll) {
        this.cenaViatoll = cenaViatoll;
    }
}
