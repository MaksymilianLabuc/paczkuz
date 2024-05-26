package org.janbat.paczkuz;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminPanelController {

    @FXML
    private TableView<Account> userTable;

    @FXML
    private TableColumn<Account, String> usernameColumn;

    @FXML
    private TableColumn<Account, String> passwordColumn;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private List<Account> accounts = new ArrayList<>();

    public void initialize() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                usernameField.setText(newSelection.getUsername());
                passwordField.setText(newSelection.getPassword());
            }
        });
        loadAccounts();
        Platform.runLater(() -> {
            userTable.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                Node source = (Node) event.getTarget();
                while (source != userTable && source != null) {
                    if (source.getStyleClass().contains("table-row-cell") || source.getStyleClass().contains("table-cell")) {
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

    @FXML
    private void handleAddSaveButtonAction() {
        Account selectedAccount = userTable.getSelectionModel().getSelectedItem();
        if (selectedAccount != null && !(usernameField.getText().isEmpty())) {
            // Zaaktualizuj obecne konto
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

        saveAccounts();
        loadAccounts();  // Odśwież widok tabeli
    }

    @FXML
    private void handleDeleteButtonAction() {
        Account selectedAccount = userTable.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            // Remove selected account
            accounts.remove(selectedAccount);
            saveAccounts();
            loadAccounts();  // Refresh the table view
        }
    }

    private void saveAccounts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt", false))) {
            for (Account account : accounts) {
                writer.println(account.getUsername() + "," + account.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}