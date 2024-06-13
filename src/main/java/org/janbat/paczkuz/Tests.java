package org.janbat.paczkuz;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class Tests {
    static API apiCaller;
    static ZlecenieWindowController zwc;

    @BeforeAll
    static void init() {
        Platform.startup(() -> {});
        apiCaller = new API();
        zwc = new ZlecenieWindowController();
    }
    @Test
    void testAPIDistanceBetweenTowns() {
        assertEquals(154440.0, apiCaller.getDistanceOfTowns("Zielona Góra", "Poznań"), "Test odległości Zielona Góra - Poznań");
    }

//    @Test
//    void testAddOrder() {
//        zwc.setAttrsForTests();
//        zwc.initialize();
//        zwc.cel = new TextField("Wrocław");
//        zwc.start = new TextField("Zielona Góra");
//        assertDoesNotThrow(() -> {
//            zwc.zapisz();
//        });
//    }
}
