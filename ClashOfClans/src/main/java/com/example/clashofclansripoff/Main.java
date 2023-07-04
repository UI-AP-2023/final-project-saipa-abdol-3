package com.example.clashofclansripoff;

import com.example.clashofclansripoff.Stages.AttackStage;
import com.example.clashofclansripoff.Stages.ResultStage;
import com.example.clashofclansripoff.controller.DatabaseController;
import com.example.clashofclansripoff.model.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 444);
        stage.setResizable(false);
        stage.setTitle("Clash of Clans");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception{
        launch();
    }
}