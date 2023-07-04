package com.example.clashofclansripoff.model;

import com.example.clashofclansripoff.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Giant extends Troop{
    public Giant(int x,int y){
        super(80,12,20,Type.Giant,null,x,y);
        ImageView view=new ImageView(new Image(Main.class.getResourceAsStream("Giant2.png")));
        view.setX(x);
        view.setY(y);
        view.setFitWidth(50);
        view.setFitHeight(50);
        setView(view);
    }
}
