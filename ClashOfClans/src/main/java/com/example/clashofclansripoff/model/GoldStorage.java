package com.example.clashofclansripoff.model;

import com.example.clashofclansripoff.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GoldStorage extends Building{
    public GoldStorage(String ownerID,int x,int y)throws Exception{
        super(Type.Gold,100,ownerID , x, y,0,null);
        ImageView view=new ImageView(new Image(Main.class.getResourceAsStream("GoldStorage.png")));
        view.setFitHeight(75);
        view.setFitWidth(75);
        view.setX(getX());
        view.setY(getY());
        setView(view);
    }
}
