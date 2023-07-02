module com.example.clashofclansripoff {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.clashofclansripoff.controller;
    opens com.example.clashofclansripoff.controller to javafx.fxml;
    exports com.example.clashofclansripoff.model;
    opens com.example.clashofclansripoff.model to javafx.fxml;
    exports com.example.clashofclansripoff;
    opens com.example.clashofclansripoff to javafx.fxml;
    requires java.sql;
}