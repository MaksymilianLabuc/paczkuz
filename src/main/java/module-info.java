module org.janbat.paczkuz {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens org.janbat.paczkuz to javafx.fxml;
    exports org.janbat.paczkuz;
}