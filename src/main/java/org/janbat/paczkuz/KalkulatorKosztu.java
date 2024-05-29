package org.janbat.paczkuz;

public class KalkulatorKosztu {
    public void oblicz(Zlecenie z){
        int D = API.getDistanceOfTowns(z.getStart(),z.getCel()); // Dystans między miastem startowym a docelowym
        Double S = z.getWybranyPojazd().getSpalanie(); // Spalanie na 100km
        Double C = 7.0; // Cena paliwa za litr
        int MT = 12; // Maksymalny czas pracy kierowcy na dzień ( w godzinach )
        Double P = z.getWybranyTypTrasy().getWyplataDlaKierowcy(); // Stawka godzinowa kierowcy
        Double Kk = (D/90) * P; // Płaca dla kierowcy
        Double Kp = S * C; // Koszt paliwa
        Double Kc = Kk+Kp; // koszt całkowity
        Double Kkm = Kc/D; // koszt za kilometr
        z.setCenaZaKm(Kkm);
        z.setCenaCalkowita(Kc);
    }
}
