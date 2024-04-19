package org.janbat.paczkuz;

import java.util.ArrayList;

public class Zlecenie {
    public String start;
    public String cel;
    public ArrayList<Towar> towary;

    public Zlecenie(){
        towary = new ArrayList<Towar>();
    }
}
