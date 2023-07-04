package com.example.clashofclansripoff.model;

import com.example.clashofclansripoff.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wizard extends Troop{
    public Wizard(int x,int y){
        super(23,23,80,Type.Wizard,null,x,y);
        ImageView view=new ImageView(new Image(Main.class.getResourceAsStream("Wizard2.png")));
        view.setX(x);
        view.setY(y);
        view.setFitWidth(40);
        view.setFitHeight(50);
        setView(view);
    }
}
