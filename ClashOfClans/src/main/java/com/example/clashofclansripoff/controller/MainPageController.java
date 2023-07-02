package com.example.clashofclansripoff.controller;

import com.example.clashofclansripoff.Main;
import com.example.clashofclansripoff.Stages.AttackStage;
import com.example.clashofclansripoff.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    @FXML
    private Button attack_btn;
    @FXML
    private AnchorPane root;
    @FXML
    void attack(ActionEvent event) throws Exception{
        ((Stage)attack_btn.getScene().getWindow()).close();
        AttackStage.getAttackStage().show();
    }

    @FXML
    void logout(ActionEvent event) throws Exception{
        ((Stage)attack_btn.getScene().getWindow()).close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 444);
        Stage stage=new Stage();
        stage.setResizable(false);
        stage.setTitle("Clash of Clans");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Building building:DatabaseController.getCurrentUser().getMap().getBuildings()){
            root.getChildren().add(building.getView());
            if(building instanceof ArcherTower){
                Archer archer=new Archer(building.getX()+15, building.getY()-10, true);
                root.getChildren().add(archer.getView());
            }
        }
//        Barbarian barbarian=new Barbarian(500,400);
//        root.getChildren().add(barbarian.getView());
//        Giant giant=new Giant(600,300);
//        root.getChildren().add(giant.getView());
//        Wizard wizard=new Wizard(300,200);
//        root.getChildren().add(wizard.getView());
    }

    public AnchorPane getRoot() {
        return root;
    }
}