module com.example.lr1_interface {
    requires javafx.controls;
    requires javafx.fxml;
    requires poi;
    requires java.desktop;
    requires org.apache.commons.io;


    opens com.example.lr1_interface to javafx.fxml;
    exports com.example.lr1_interface;
}