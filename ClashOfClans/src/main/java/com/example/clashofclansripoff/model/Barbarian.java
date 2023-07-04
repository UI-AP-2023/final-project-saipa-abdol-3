package com.example.clashofclansripoff.model;

import com.example.clashofclansripoff.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Barbarian extends Troop{
    public Barbarian(int x,int y){
        super(23,8,10,Type.Barbarian,null,x,y);
        ImageView view=new ImageView(new Image(Main.class.getResourceAsStream("Barbarian2.png")));
        view.setX(x);
        view.setY(y);
        view.setFitWidth(50);
        view.setFitHeight(50);
        setView(view);
    }
}
