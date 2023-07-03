package com.example.clashofclansripoff.controller;

import com.example.clashofclansripoff.Main;
import com.example.clashofclansripoff.model.Player;
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
    void Login_clicked(MouseEvent event) throws Exception {
        if (login()) {
            ((Stage) pass_field.getScene().getWindow()).close();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("page-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 899, 679);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Clash of Clans");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
            stage.show();
        }
    }

    @FXML
    void back_clicked(MouseEvent event) throws Exception{
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
    private boolean login()throws Exception{
        for(Player player:DatabaseController.getPlayersDB()){
            if(username_field.getText().equals(player.getUsername())&&pass_field.getText().equals(player.getPassword())){
                DatabaseController.setCurrentUser(player);
                return true;
            }
        }
        return false;
    }
}
