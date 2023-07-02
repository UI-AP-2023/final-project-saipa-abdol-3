package com.example.clashofclansripoff.model;

import com.example.clashofclansripoff.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Archer extends Troop{
    public Archer(int x,int y,boolean onTower) {
        super(10, 6, 90, Type.Archer, null, x, y);
        ImageView view = new ImageView(new Image(Main.class.getResourceAsStream("Archer3.png")));
        view.setX(x);
        view.setY(y);
        view.setFitWidth(50);
        view.setFitHeight(50);
        setView(view);
        if (onTower) {
            setRange(200);
            view.setFitWidth(37);
            view.setFitHeight(37);
        }
    }
}
