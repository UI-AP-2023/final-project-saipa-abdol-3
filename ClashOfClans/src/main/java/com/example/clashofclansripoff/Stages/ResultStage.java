package com.example.clashofclansripoff.Stages;

import com.example.clashofclansripoff.Main;
import com.example.clashofclansripoff.controller.DatabaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultStage {
    private static Stage stage;
    private static AnchorPane root;
    public static Stage getResultStage(boolean destroyedTH,int percentage)throws Exception {
        stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Clash of Clans");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        ImageView background = new ImageView(new Image(Main.class.getResourceAsStream("ResultBackground.jpg")));
        root=new AnchorPane();
        root.getChildren().add(background);
        Scene scene=new Scene(root,312.6,556);
        Button back_btn =new Button("Go Back");
        back_btn.setStyle("-fx-font-family: 'Arial Black';-fx-font-size: 16px;");
        root.getChildren().add(back_btn);
        back_btn.setLayoutY(506);
        back_btn.setLayoutX(107);
        back_btn.setPrefHeight(35);
        back_btn.setPrefWidth(117);
        Text percentageText=new Text(String.valueOf(percentage));
        if(percentage<10){
            percentageText.setLayoutX(149);
        }else if(percentage<100){
            percentageText.setLayoutX(145);
        }else if(percentage==100){
            percentageText.setLayoutX(139);
        }
        percentageText.setLayoutY(117);
        percentageText.setStyle("-fx-font-family: 'Arial Black';-fx-font-size: 16px;");
        back_btn.setOnMouseClicked(mouseEvent -> {
            try {
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("page-view.fxml"));
                Scene scene1 = new Scene(fxmlLoader.load(), 899, 679);
                Stage stage=new Stage();
                stage.setScene(scene1);
                stage.setResizable(false);
                stage.setTitle("Clash of Clans");
                stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        ImageView stars=new ImageView(new Image(Main.class.getResourceAsStream("ThreeStars.jpeg")));
        if(percentage<100&&percentage>=50&&destroyedTH){
            stars.setImage(new Image(Main.class.getResourceAsStream("TwoStars.jpeg")));
            DatabaseController.addToVictories();
        }else if(destroyedTH||percentage>=50){
            stars.setImage(new Image(Main.class.getResourceAsStream("OneStars.jpeg")));
            DatabaseController.addToVictories();
        }else {
            stars.setImage(new Image(Main.class.getResourceAsStream("ZeroStars.jpeg")));
            DatabaseController.addToLosses();
        }
        if(percentage==100&&destroyedTH){
            stars=new ImageView(new Image(Main.class.getResourceAsStream("ThreeStars.jpeg")));
            DatabaseController.addToVictories();
        }
        stars.setFitHeight(102);
        stars.setFitWidth(232);
        stars.setLayoutX(40);
        stars.setLayoutY(59);
        root.getChildren().add(stars);
        root.getChildren().add(percentageText);
        background.setFitHeight(556);
        background.setFitWidth(313);
        stage.setScene(scene);
        return stage;
    }
}
