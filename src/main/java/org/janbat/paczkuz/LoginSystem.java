package org.janbat.paczkuz;

import java.io.*;
import java.util.*;

public class LoginSystem {
    private static final String FILE_NAME = "users.txt";

    public static boolean register(String username, String password) {
        if (userExists(username)) {
            System.out.println("Użytkownik już istnieje!");
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            System.out.println("Rejestracja zakończona sukcesem!");
            return true;
        } catch (IOException e) {
            System.out.println("Błąd!");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(",")[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd!");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean login(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    System.out.println("Logowanie zakończone sukcesem!");
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
}
