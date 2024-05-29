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

    public static String getMotyw() {
        return motyw;
    }

    public static void setMotyw(String motyw) {
        Ustawienia.motyw = motyw;
    }

    public static String getJezyk() {
        return jezyk;
    }

    public static void setJezyk(String jezyk) {
        Ustawienia.jezyk = jezyk;
        HelloApplication.paczkaJezykowa = ResourceBundle.getBundle("org.janbat.paczkuz.language", Ustawienia.getJezyki().get(jezyk));
    }

    public static HashMap<String, Locale> getJezyki() {
        return jezyki;
    }
}

