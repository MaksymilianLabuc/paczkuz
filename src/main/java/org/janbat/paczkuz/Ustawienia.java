package org.janbat.paczkuz;

public class Ustawienia {
    private static String motyw = "Light mode";  // Domyślny motyw

    public static String getMotyw() {
        return motyw;
    }

    public static void setMotyw(String motyw) {
        Ustawienia.motyw = motyw;
    }
}

