package org.janbat.paczkuz;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Account {
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    /**
     * Inicjalizuje obiekt konta z określoną nazwą użytkownika i hasłem.
     * @param username Nazwa użytkownika dla konta.
     * @param password Hasło dla konta.
     */
    public Account(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    /**
     * Pobiera nazwę użytkownika konta.
     * @return Nazwa użytkownika.
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * Pobiera hasło konta.
     * @return Hasło.
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * Ustawia hasło konta.
     * @param password Nowe hasło.
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    /**
     * Ustawia nazwę użytkownika konta.
     * @param username Nowa nazwa użytkownika.
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

}
