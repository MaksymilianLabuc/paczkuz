module org.janbat.paczkuz {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.janbat.paczkuz to javafx.fxml;
    exports org.janbat.paczkuz;
}