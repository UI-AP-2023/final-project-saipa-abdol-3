package com.example.clashofclansripoff.controller;

import com.example.clashofclansripoff.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField pass_field;

    @FXML
    private TextField username_field;

    @FXML
    void Login_clicked(MouseEvent event) throws IOException {
        ((Stage)pass_field.getScene().getWindow()).close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("map-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 909, 424);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Clash of Clans");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void back_clicked(MouseEvent event) {
        ((Stage)pass_field.getScene().getWindow()).close();
    }
}
