package com.example.clashofclansripoff.Stages;

import com.example.clashofclansripoff.Main;
import com.example.clashofclansripoff.controller.BuildingThread;
import com.example.clashofclansripoff.controller.TroopThread;
import com.example.clashofclansripoff.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class AttackStage {
    private static Stage stage;
    private static AnchorPane root;
    private static int troopCount;
    public static Stage getAttackStage()throws Exception{
        troopCount=0;
        stage=new Stage();
        root=new AnchorPane();
        ImageView background=new ImageView(new Image(Main.class.getResourceAsStream("thumb.jpg")));
        background.setFitHeight(679);
        background.setFitWidth(923);
        root.getChildren().add(background);
        Scene scene=new Scene(root,899,679);
        stage.setResizable(false);
        stage.setTitle("Clash of Clans");
        Player player=new Player();
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        for(Building building:player.getMap().getBuildings()){
            root.getChildren().add(building.getView());
        }

        Button next_btn=new Button("Next");
        Button back_btn=new Button("Back");
        Button surrender=new Button("Surrender");

        next_btn.setStyle("-fx-font-family: 'Arial Black';-fx-font-size: 18px;");
        surrender.setStyle("-fx-font-family: 'Arial Black';-fx-font-size: 18px;");

        next_btn.setLayoutX(738);
        next_btn.setLayoutY(632);
        next_btn.setPrefWidth(96);
        next_btn.setPrefHeight(40);

        next_btn.setOnMouseClicked(mouseEvent -> {
            try {
                stage.close();
                getAttackStage().show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        surrender.setOnMouseClicked(mouseEvent -> {
            try {
                stage.close();
                ResultStage.getResultStage(!TroopThread.hasTownHall(player.getMap().getBuildings()),TroopThread.getPercentage(player.getMap())).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        surrender.setLayoutX(738);
        surrender.setLayoutY(532);
        surrender.setPrefWidth(96);
        surrender.setPrefHeight(40);
//        for(Building building:player.getMap().getBuildings()){
//            if(building instanceof Cannon||building instanceof ArcherTower){
//                (new BuildingThread(player.getMap(),building)).start();
//                System.out.println("thread");
//            }
//        }

        back_btn.setStyle("-fx-font-family: 'Arial Black';-fx-font-size: 18px;");

        back_btn.setPrefWidth(96);
        back_btn.setPrefHeight(40);
        back_btn.setLayoutX(174);
        back_btn.setLayoutY(632);

        back_btn.setOnMouseClicked(mouseEvent -> {
            stage.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("page-view.fxml"));
                Scene scene1 = new Scene(fxmlLoader.load(), 899, 679);
                stage.setResizable(false);
                stage.setTitle("Clash of Clans");
                stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
                stage.setScene(scene1);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        ImageView archerImage=new ImageView(new Image(Main.class.getResourceAsStream("ArcherIcon.jpeg")));
        ImageView barbarianImage=new ImageView(new Image(Main.class.getResourceAsStream("BarbarianIcon.png")));
        ImageView wizardImage=new ImageView(new Image(Main.class.getResourceAsStream("WizardIcon.jpeg")));
        ImageView giantImage=new ImageView(new Image(Main.class.getResourceAsStream("GiantIcon.jpeg")));

        archerImage.setLayoutY(572);
        archerImage.setLayoutX(300);
        archerImage.setFitHeight(70);
        archerImage.setFitWidth(70);

        barbarianImage.setLayoutY(572);
        barbarianImage.setLayoutX(400);
        barbarianImage.setFitHeight(70);
        barbarianImage.setFitWidth(70);

        wizardImage.setLayoutY(572);
        wizardImage.setLayoutX(500);
        wizardImage.setFitHeight(70);
        wizardImage.setFitWidth(70);

        giantImage.setLayoutY(572);
        giantImage.setLayoutX(600);
        giantImage.setFitHeight(70);
        giantImage.setFitWidth(70);

        root.getChildren().addAll(back_btn,next_btn,surrender,archerImage,barbarianImage,wizardImage,giantImage);
        Random rand=new Random();
        archerImage.setOnMouseClicked(mouseEvent -> {
            if(troopCount++<13) {
                Archer archer = new Archer(rand.nextInt(150, 600), rand.nextInt(150, 400), false);
                while (checkAll(player.getMap(), archer)) {
                    archer = new Archer(rand.nextInt(150, 600), rand.nextInt(150, 400), false);
                }
                root.getChildren().add(archer.getView());
                TroopThread thread = new TroopThread(player.getMap(), archer);
                player.getMap().getTroops().add(archer);
                (thread).start();
            }else {
                System.out.println("Exceeding troop limit");
            }
        });
        barbarianImage.setOnMouseClicked(mouseEvent -> {
            if(troopCount++<13) {
                Barbarian barbarian = new Barbarian(rand.nextInt(150, 600), rand.nextInt(150, 400));
                while (checkAll(player.getMap(), barbarian)) {
                    barbarian = new Barbarian(rand.nextInt(150, 600), rand.nextInt(150, 400));
                }
                root.getChildren().add(barbarian.getView());
                TroopThread thread = new TroopThread(player.getMap(), barbarian);
                player.getMap().getTroops().add(barbarian);
                (thread).start();
            }else {
                System.out.println("Exceeding troop limit");
            }
        });
        wizardImage.setOnMouseClicked(mouseEvent -> {
            if(troopCount++<13) {
                Wizard wizard = new Wizard(rand.nextInt(150, 600), rand.nextInt(150, 400));
                while (checkAll(player.getMap(), wizard)) {
                    wizard = new Wizard(rand.nextInt(150, 600), rand.nextInt(150, 400));
                }
                root.getChildren().add(wizard.getView());
                TroopThread thread = new TroopThread(player.getMap(), wizard);
                player.getMap().getTroops().add(wizard);
                (thread).start();
            }else {
                System.out.println("Exceeding troop limit");
            }
        });
        giantImage.setOnMouseClicked(mouseEvent -> {
            if(troopCount++<13) {
                Giant giant = new Giant(rand.nextInt(150, 600), rand.nextInt(150, 400));
                while (checkAll(player.getMap(), giant)) {
                    giant = new Giant(rand.nextInt(150, 600), rand.nextInt(150, 400));
                }
                root.getChildren().add(giant.getView());
                TroopThread thread = new TroopThread(player.getMap(), giant);
                player.getMap().getTroops().add(giant);
                (thread).start();
            }else {
                System.out.println("Exceeding troop limit");
            }
        });
        stage.setScene(scene);
        return stage;
    }
    public static Stage getStage(){
        return stage;
    }

    public static AnchorPane getRoot() {
        return root;
    }
    public static boolean checkCollision(Building building1,Troop troop) {
        // Calculate the coordinates of the four corners of each square
        int x1Left = building1.getX();
        int x1Right = building1.getX() + 75;
        int y1Top = building1.getY();
        int y1Bottom = building1.getY() + 75;

        int x2Left = troop.getX();
        int x2Right = troop.getX() + 50;
        int y2Top = troop.getY();
        int y2Bottom = troop.getY() + 50;

        // Check for collision by comparing the coordinates of the corners
        if (x1Right >= x2Left &&
                x1Left <= x2Right &&
                y1Bottom >= y2Top &&
                y1Top <= y2Bottom) {
            return true; // Collision detected
        }

        return false; // No collision detected
    }
    public static boolean checkAll(Map map,Troop troop){
        for(Building building: map.getBuildings()){
            if(checkCollision(building,troop)){
                return true;
            }
        }
        return false;
    }

    public static int getTroopCount() {
        return troopCount;
    }
}
