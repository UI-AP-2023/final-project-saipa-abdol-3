package com.example.clashofclansripoff.model;

import com.example.clashofclansripoff.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TownHall extends Building {
    private int level;
    private Image image;
    public TownHall(String ownerID,int x, int y, int level) throws Exception{
        super(Type.TownHall, 300, ownerID, x, y,level,null,0);
        this.level = level;
        assignImage(level);
        ImageView view=new ImageView(image);
        view.setFitHeight(75);
        view.setFitWidth(75);
        view.setX(getX());
        view.setY(getY());
        setView(view);
    }
    private void assignImage(int level){
        switch (level) {
            case 1: {
                setHealth(45);
                setImage(new Image(Main.class.getResourceAsStream("Town1.png")));
            }
            break;
            case 2: {
                setHealth(160);
                setImage(new Image(Main.class.getResourceAsStream("Town2.png")));
            }
            break;
            case 3: {
                setHealth(185);
                setImage(new Image(Main.class.getResourceAsStream("Town3.png")));
            }
            break;
            case 4: {
                setHealth(210);
                setImage(new Image(Main.class.getResourceAsStream("Town4.png")));
            }
            break;
            case 5: {
                setHealth(240);
                setImage(new Image(Main.class.getResourceAsStream("Town5.png")));
            }
            break;
            case 6: {
                setHealth(250);
                setImage(new Image(Main.class.getResourceAsStream("Town6.png")));
            }
            break;
            case 7: {
                setHealth(270);
                setImage(new Image(Main.class.getResourceAsStream("Town7.png")));
            }
            break;
            case 8: {
                setHealth(290);
                setImage(new Image(Main.class.getResourceAsStream("Town8.png")));
            }
            break;
            case 9: {
                setHealth(310);
                setImage(new Image(Main.class.getResourceAsStream("Town9.png")));
            }
            break;
            case 10: {
                setHealth(340);
                setImage(new Image(Main.class.getResourceAsStream("Town10.png")));
            }
            break;
            case 11: {
                setHealth(370);
                setImage(new Image(Main.class.getResourceAsStream("Town11.png")));
            }
            break;
            case 12: {
                setHealth(400);
                setImage(new Image(Main.class.getResourceAsStream("Town12.png")));
            }
            break;
            case 13: {
                setHealth(420);
                setImage(new Image(Main.class.getResourceAsStream("Town13.png")));
            }
            break;
            case 14: {
                setHealth(450);
                setImage(new Image(Main.class.getResourceAsStream("Town14.png")));
            }
            break;
            case 15: {
                setHealth(500);
                setImage(new Image(Main.class.getResourceAsStream("Town15.png")));
            }
            break;
        }
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
