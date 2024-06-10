package org.janbat.paczkuz;

public class typTrasy {
    public String nazwa;
    public double wyplataDlaKierowcy;
    public double cenaViatoll;

    /**
     * Zwraca nazwę trasy jako jej reprezentację tekstową.
     * @return nazwa trasy.
     */
    @Override
    public String toString() {
        return nazwa;
    }

    /**
     * Zwraca nazwę trasy.
     * @return nazwa trasy.
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia nazwę trasy.
     * @param nazwa nazwa trasy.
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Zwraca wypłatę dla kierowcy za daną trasę.
     * @return wypłata dla kierowcy.
     */
    public double getWyplataDlaKierowcy() {
        return wyplataDlaKierowcy;
    }

    /**
     * Ustawia wypłatę dla kierowcy za daną trasę.
     * @param wyplataDlaKierowcy wypłata dla kierowcy.
     */
    public void setWyplataDlaKierowcy(double wyplataDlaKierowcy) {
        this.wyplataDlaKierowcy = wyplataDlaKierowcy;
    }

    /**
     * Zwraca cenę Viatoll za tę trasę.
     * @return cena Viatoll.
     */
    public double getCenaViatoll() {
        return cenaViatoll;
    }

    /**
     * Ustawia cenę Viatoll za tę trasę.
     * @param cenaViatoll cena Viatoll.
     */
    public void setCenaViatoll(double cenaViatoll) {
        this.cenaViatoll = cenaViatoll;
    }
}
