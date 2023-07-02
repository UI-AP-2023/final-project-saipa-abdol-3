package com.example.clashofclansripoff.Stages;

import com.example.clashofclansripoff.Main;
import com.example.clashofclansripoff.model.Building;
import com.example.clashofclansripoff.model.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AttackStage {
    private static Stage stage;
    public static Stage getAttackStage()throws Exception{
        stage=new Stage();
        AnchorPane root=new AnchorPane();
        ImageView background=new ImageView(new Image(Main.class.getResourceAsStream("thumb.jpg")));
        background.setFitHeight(679);
        background.setFitWidth(923);
        root.getChildren().add(background);
        Scene scene=new Scene(root,899,679);
        stage.setResizable(false);
        stage.setTitle("Clash of Clans");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        for(Building building: (new Player()).getMap().getBuildings()){
            root.getChildren().add(building.getView());
        }

        Button next_btn=new Button("Next");
        Button back_btn=new Button("Back");

        next_btn.setStyle("-fx-font-family: 'Arial Black';-fx-font-size: 18px;");

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

        back_btn.setStyle("-fx-font-family: 'Arial Black';-fx-font-size: 18px;");

        back_btn.setPrefWidth(96);
        back_btn.setPrefHeight(40);
        back_btn.setLayoutX(174);
        back_btn.setLayoutY(632);

        back_btn.setOnMouseClicked(mouseEvent -> {
            stage.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
                Scene scene1 = new Scene(fxmlLoader.load(), 900, 444);
                stage.setResizable(false);
                stage.setTitle("Clash of Clans");
                stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
                stage.setScene(scene1);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        ImageView archerImage=new ImageView(new Image(Main.class.getResourceAsStream("Archer3.png")));
        ImageView barbarianImage=new ImageView(new Image(Main.class.getResourceAsStream("Barbarian.png")));
        ImageView wizardImage=new ImageView(new Image(Main.class.getResourceAsStream("Wizard.png")));
        ImageView giantImage=new ImageView(new Image(Main.class.getResourceAsStream("Giant.png")));

        archerImage.setLayoutY(622);
        archerImage.setLayoutX(300);
        archerImage.setFitHeight(50);
        archerImage.setFitWidth(50);

        barbarianImage.setLayoutY(622);
        barbarianImage.setLayoutX(350);
        barbarianImage.setFitHeight(50);
        barbarianImage.setFitWidth(50);

        wizardImage.setLayoutY(622);
        wizardImage.setLayoutX(400);
        wizardImage.setFitHeight(50);
        wizardImage.setFitWidth(40);

        giantImage.setLayoutY(622);
        giantImage.setLayoutX(450);
        giantImage.setFitHeight(50);
        giantImage.setFitWidth(50);

        root.getChildren().addAll(back_btn,next_btn,archerImage,barbarianImage,wizardImage,giantImage);
        stage.setScene(scene);
        return stage;
    }
    public static Stage getStage(){
        return stage;
    }
}
