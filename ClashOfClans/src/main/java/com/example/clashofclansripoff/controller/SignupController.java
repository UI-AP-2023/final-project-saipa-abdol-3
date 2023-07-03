package com.example.clashofclansripoff.controller;

import com.example.clashofclansripoff.Main;
import com.example.clashofclansripoff.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML
    private Text age;

    @FXML
    private Slider age_slider;

    @FXML
    private Button back_btn1;

    @FXML
    private TextField email_field;

    @FXML
    private TextField pass_field;
    @FXML
    private TextField username_field;

    @FXML
    void age_changed(MouseEvent event) {
        age.setText(String.valueOf((int)age_slider.getValue()));
    }

    @FXML
    void exit(MouseEvent event) throws Exception{
        ((Stage)pass_field.getScene().getWindow()).close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 444);
        Stage stage=new Stage();
        stage.setResizable(false);
        stage.setTitle("Clash of Clans");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void submit(MouseEvent event) throws Exception {
        if(Integer.parseInt(age.getText())>=13){
            Player player=new Player(username_field.getText(),pass_field.getText(),email_field.getText(),Integer.parseInt(age.getText()));
            DatabaseController.addPlayer(player);
            ((Stage)back_btn1.getScene().getWindow()).close();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 430);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Clash of Clans");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
            stage.setScene(scene);
            stage.show();
        }
    }
}
