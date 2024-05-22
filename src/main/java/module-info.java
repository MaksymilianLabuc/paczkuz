module org.janbat.paczkuz {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;
    requires jakarta.mail;


    opens org.janbat.paczkuz to javafx.fxml;
    exports org.janbat.paczkuz;
}