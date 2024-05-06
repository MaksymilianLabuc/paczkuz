package org.janbat.paczkuz;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class adminPanelController {
    @FXML
    private TableView<User> userTable;

    @FXML
    public void initialize() {
        UserTable table = new UserTable();
        userTable.setItems(table.getTable().getItems());
    }
}