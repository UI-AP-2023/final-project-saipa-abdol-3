package com.example.clashofclansripoff.controller;

import com.example.clashofclansripoff.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private ImageView bkg;

    @FXML
    private Button exit_btn;

    @FXML
    private AnchorPane root;

    @FXML
    private Button signup_btn;

    @FXML
    void exit_event(MouseEvent event) {
        ((Stage) exit_btn.getScene().getWindow()).close();
    }

    @FXML
    void login_btn(ActionEvent event) {

    }

    @FXML
    void login_event(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("map-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 899, 679);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Clash of Clans");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signup_event(MouseEvent event) {

    }
}
