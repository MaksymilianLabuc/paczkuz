package org.janbat.paczkuz;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ResourceBundle;

public class PodsumowanieWindowController {

    @FXML
    private Label celLabel;

    @FXML
    private Label cenaKmLabel;

    @FXML
    private Label dystansLabel;

    @FXML
    private Label idLabel;

    @FXML
    private TableColumn<Towar, String> nazwaCol;

    @FXML
    private TableColumn<Towar, Double> wagaCol;

    @FXML
    private Label pojazdLabel;

    @FXML
    private Label startLabel;

    @FXML
    private Label sumaLabel;

    @FXML
    private TableView<Towar> towaryTab;

    @FXML
    private Label typTrasyLabel;

    private Zlecenie z;

    @FXML
    public void initialize(){

        nazwaCol.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        wagaCol.setCellValueFactory(new PropertyValueFactory<>("ciezar"));
        towaryTab.getItems().addAll(z.getTowary());

        celLabel.setText(z.getCel());
        startLabel.setText(z.getStart());
        cenaKmLabel.setText(String.format("%.2f",z.getCenaZaKm()));
        dystansLabel.setText(String.valueOf(z.getDystans()));
        idLabel.setText(String.valueOf(z.getId()));
        pojazdLabel.setText(z.getWybranyPojazd().getNazwa());
        sumaLabel.setText(String.format("%.2f",z.getCenaCalkowita()));
        typTrasyLabel.setText(z.getWybranyTypTrasy().getNazwa());
    }
    public PodsumowanieWindowController(Zlecenie z){
        this.z = z;
    }

    public void setZ(Zlecenie z) {
        this.z = z;

    }
}
