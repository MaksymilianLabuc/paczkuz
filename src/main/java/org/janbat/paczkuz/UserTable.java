package org.janbat.paczkuz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserTable {
    private TableView<User> table = new TableView<>();
    private ObservableList<User> data;

    public UserTable() {
        TableColumn<User, String> userCol = new TableColumn<>("Użytkownik");
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> passCol = new TableColumn<>("Haslo");
        passCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        table.getColumns().addAll(userCol, passCol);
        loadDataFromFile("users.txt");
    }

    private void loadDataFromFile(String fileName) {
        data = FXCollections.observableArrayList();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                data.add(new User(details[0], details[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        table.setItems(data);
    }

    public TableView<User> getTable() {
        return table;
    }
}

