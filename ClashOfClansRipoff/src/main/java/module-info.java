module com.example.clashofclansripoff {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clashofclansripoff to javafx.fxml;
    exports com.example.clashofclansripoff;
}