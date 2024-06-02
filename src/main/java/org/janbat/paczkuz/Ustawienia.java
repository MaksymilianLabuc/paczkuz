package org.janbat.paczkuz;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class Ustawienia {
    private static String motyw = "Light mode";  // Domyślny motyw
    private static HashMap<String, Locale> jezyki = new HashMap<String,Locale>(){{
        put("Polski",Locale.of("pl"));
        put("English",Locale.of("en","US"));
        put("Japanese",Locale.of("ja","JP"));
    }}; //Lista języków
    private static String jezyk = "Polski";

    /**
     * Zwraca bieżący motyw aplikacji.
     * @return nazwa motywu.
     */
    public static String getMotyw() {
        return motyw;
    }

    /**
     * Ustawia motyw aplikacji.
     * @param motyw nowy motyw.
     */
    public static void setMotyw(String motyw) {
        Ustawienia.motyw = motyw;
    }

    /**
     * Zwraca bieżący język aplikacji.
     * @return nazwa języka.
     */
    public static String getJezyk() {
        return jezyk;
    }

    /**
     * Ustawia język aplikacji.
     * Aktualizuje także ResourceBundle na podstawie wybranego języka.
     * @param jezyk nowy język.
     */
    public static void setJezyk(String jezyk) {
        Ustawienia.jezyk = jezyk;
        HelloApplication.paczkaJezykowa = ResourceBundle.getBundle("org.janbat.paczkuz.language", Ustawienia.getJezyki().get(jezyk));
    }

    /**
     * Zwraca mapę dostępnych języków wraz z ich lokalizacjami.
     * @return mapa języków.
     */
    public static HashMap<String, Locale> getJezyki() {
        return jezyki;
    }
}

