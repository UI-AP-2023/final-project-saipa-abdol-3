module com.example.clashofclansripoff {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.clashofclansripoff.controller;
    opens com.example.clashofclansripoff.controller to javafx.fxml;
    exports com.example.clashofclansripoff;
    opens com.example.clashofclansripoff to javafx.fxml;
}