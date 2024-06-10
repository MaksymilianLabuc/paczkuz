package org.janbat.paczkuz;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.*;

public class LoginSystem {
    private static final String FILE_NAME = "users.txt";
    private static String user;

    /**
     * Rejestruje nowego użytkownika z podaną nazwą użytkownika i hasłem.
     * Sprawdza, czy użytkownik już istnieje i czy pola nie są puste.
     * @param username Nazwa użytkownika.
     * @param password Hasło użytkownika.
     * @return true jeśli rejestracja zakończyła się sukcesem, w przeciwnym razie false.
     */
    public static boolean register(String username, String password) {
        if (username.isEmpty()||password.isEmpty()) { //sprawdzanie czy jest cos wpisane w pola tekstowe
            System.out.println("Nie można utworzyć pustego użytkownika.");
            return false;
        }
        if (userExists(username)) {
            System.out.println("Użytkownik już istnieje!");
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            System.out.println("Rejestracja zakończona sukcesem!");
            user=username;
            return true;
        } catch (IOException e) {
            System.out.println("Błąd!");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Sprawdza, czy użytkownik o podanej nazwie już istnieje.
     * @param username Nazwa użytkownika do sprawdzenia.
     * @return true jeśli użytkownik istnieje, w przeciwnym razie false.
     */
    public static boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(",") && line.split(",")[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd!");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Loguje użytkownika na podstawie podanej nazwy użytkownika i hasła.
     * Sprawdza, czy pola nie są puste i czy dane są poprawne.
     * @param username Nazwa użytkownika.
     * @param password Hasło użytkownika.
     * @return true jeśli logowanie zakończyło się sukcesem, w przeciwnym razie false.
     */
    public static boolean login(String username, String password) {
        if (username.isEmpty()||password.isEmpty()) { //sprawdzanie czy jest cos wpisane w pola tekstowe
            System.out.println("Błędne dane.");
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    System.out.println("Logowanie zakończone sukcesem!");
                    user=username;
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd");
            e.printStackTrace();
        }
        System.out.println("Błędne dane! Spróbuj ponownie!");
        return false;
    }

    /**
     * Sprawdza, czy zalogowany użytkownik jest administratorem.
     * Jeśli nie, wyświetla komunikat alertu.
     * @return true jeśli użytkownik jest administratorem, w przeciwnym razie false.
     */
    public static boolean isAdmin() {
        if (user.equalsIgnoreCase("admin")) {
            System.out.println("admin");
            return true;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BŁĄD!");
        alert.setHeaderText(null);
        alert.setContentText("Nie jesteś adminem!");
        alert.showAndWait();
        return false;
    }
}
