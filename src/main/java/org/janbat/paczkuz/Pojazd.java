package org.janbat.paczkuz;

public class Pojazd {
    public String nazwa;
    public double ladownosc;
    public double spalanie;

    /**
     * Zwraca nazwę pojazdu.
     * @return nazwa pojazdu.
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia nazwę pojazdu.
     * @param nazwa nowa nazwa pojazdu.
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Zwraca ładowność pojazdu.
     * @return ładowność pojazdu.
     */
    public double getLadownosc() {
        return ladownosc;
    }

    /**
     * Ustawia ładowność pojazdu.
     * @param ladownosc nowa ładowność pojazdu.
     */
    public void setLadownosc(double ladownosc) {
        this.ladownosc = ladownosc;
    }

    /**
     * Zwraca spalanie pojazdu.
     * @return spalanie pojazdu.
     */
    public double getSpalanie() {
        return spalanie;
    }

    /**
     * Ustawia spalanie pojazdu.
     * @param spalanie nowe spalanie pojazdu.
     */
    public void setSpalanie(double spalanie) {
        this.spalanie = spalanie;
    }

    /**
     * Zwraca reprezentację tekstową obiektu pojazdu.
     * @return nazwa pojazdu.
     */
    @Override
    public String toString() {
        return nazwa;
    }
}
