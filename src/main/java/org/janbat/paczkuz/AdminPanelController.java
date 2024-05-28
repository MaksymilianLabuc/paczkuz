package org.janbat.paczkuz;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminPanelController {
    @FXML
    private Parent root;  // Główny węzeł dla kontrolera
    @FXML
    private TableView<Account> userTable;  // Tabela do wyświetlania kont użytkowników

    @FXML
    private TableColumn<Account, String> usernameColumn;  // Kolumna dla nazw użytkowników
    @FXML
    private TableColumn<Account, String> passwordColumn;  // Kolumna dla haseł użytkowników

    @FXML
    private TextField usernameField;  // Pole tekstowe do wprowadzania nazwy użytkownika
    @FXML
    private PasswordField passwordField;  // Pole do wprowadzania hasła

    private List<Account> accounts = new ArrayList<>();  // Lista przechowująca konta użytkowników

    public void initialize() {
        // Inicjalizacja kolumn tabeli
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        // Ustawienie słuchacza dla wybranego elementu w tabeli
        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                usernameField.setText(newSelection.getUsername());
                passwordField.setText(newSelection.getPassword());
            }
        });
        loadAccounts();  // Załaduj konta z pliku

        // Dodaj filtr do sceny, który czyści zaznaczenie i pola tekstowe, gdy klikniesz poza tabelę
        Platform.runLater(() -> {
            userTable.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                Node source = (Node) event.getTarget();
                while (source != userTable && source != null) {
                    if (source.getStyleClass().contains("table-row-cell") || source.getStyleClass().contains("table-cell") || source instanceof TextField || source instanceof PasswordField) {
                        return;
                    }
                    source = source.getParent();
                }
                userTable.getSelectionModel().clearSelection();
                usernameField.clear();
                passwordField.clear();
            });
        });
    }

    // Metoda do ładowania kont z pliku
    private void loadAccounts() {
        accounts.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Account account = new Account(parts[0].trim(), parts[1].trim());
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        userTable.setItems(FXCollections.observableArrayList(accounts));
    }

    // Obsługa przycisku dodawania/zapisywania
    @FXML
    private void handleAddSaveButtonAction() {
        Account selectedAccount = userTable.getSelectionModel().getSelectedItem();
        if (selectedAccount != null && !(usernameField.getText().isEmpty())) {
            // Aktualizuj istniejące konto
            selectedAccount.setUsername(usernameField.getText());
            selectedAccount.setPassword(passwordField.getText());
        } else if (!(usernameField.getText().isEmpty())){
            if (LoginSystem.userExists(usernameField.getText())) {
                System.out.println("Taki user już istnieje, nie mozesz tego zrobić!");
            }
            else {
                // Dodaj nowe konto
                Account newAccount = new Account(usernameField.getText(), passwordField.getText());
                accounts.add(newAccount);
            }
        }

        saveAccounts();  // Zapisz konta do pliku
        loadAccounts();  // Odśwież widok tabeli
    }

    // Obsługa przycisku usuwania
    @FXML
    private void handleDeleteButtonAction() {
        Account selectedAccount = userTable.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            // Usuń wybrane konto
            accounts.remove(selectedAccount);
            saveAccounts();  // Zapisz konta do pliku
            loadAccounts();  // Odśwież widok tabeli
        }
    }

    // Metoda do zapisywania kont do pliku
    private void saveAccounts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt", false))) {
            for (Account account : accounts) {
                writer.println(account.getUsername() + "," + account.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Przełącz na okno "Zlecenia"
    @FXML
    public void switchToZlecenia(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zlecenieWindow.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        if (Ustawienia.getMotyw().equals("Dark mode")){
            File cssFile = new File("src/main/resources/dark-mode.css");
            scene.getStylesheets().add(cssFile.toURI().toString()); //zmiana na tryb ciemny
        }
        stage.setScene(scene);
        stage.show();
    }
}
