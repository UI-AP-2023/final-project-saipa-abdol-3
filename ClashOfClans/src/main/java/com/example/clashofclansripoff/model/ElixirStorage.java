package com.example.clashofclansripoff.model;

import com.example.clashofclansripoff.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ElixirStorage extends Building{
    public ElixirStorage(String ownerID,int x,int y)throws Exception{
        super(Type.Elixir,100, ownerID, x, y,0,null,0);
        ImageView view=new ImageView(new Image(Main.class.getResourceAsStream("ElixirStorage.png")));
        view.setFitHeight(75);
        view.setFitWidth(75);
        view.setX(getX());
        view.setY(getY());
        setView(view);
    }
}
