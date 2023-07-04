package com.example.clashofclansripoff.model;

import com.example.clashofclansripoff.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cannon extends Building{
    public Cannon(String ownerID,int x,int y)throws Exception{
        super(Type.Cannon,88,ownerID, x, y,0,null,220);
        ImageView view=new ImageView(new Image(Main.class.getResourceAsStream("Cannon.png")));
        view.setFitHeight(75);
        view.setFitWidth(75);
        view.setX(getX());
        view.setY(getY());
        setView(view);
    }
}
