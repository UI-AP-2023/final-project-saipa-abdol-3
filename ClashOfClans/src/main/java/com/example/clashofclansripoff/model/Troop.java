package com.example.clashofclansripoff.model;

import javafx.scene.image.ImageView;

public abstract class Troop {
    private int health;
    private int currentHP;
    private int dps;
    private int range;
    private Type type;
    private ImageView view;
    private int x;
    private int y;
    public enum Type{
        Wizard,Archer,Giant,Barbarian
    }

    public int getHealth() {
        return health;
    }

    public int getDps() {
        return dps;
    }

    public int getRange() {
        return range;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public ImageView getView(){
        return view;
    }
    public void setView(ImageView view){
        this.view=view;
    }
    public void move(){
        view.setX(x);
        view.setY(y);
    }
    public void changeX(int change){
        x+=change;
        view.setX(x);
    }
    public void changeY(int change){
        y+=change;
        view.setY(y);
    }

    public void changeCurrentHP(int change) {
        currentHP+=change;
    }
    public void doDamage(Building building){
        building.changeCurrentHealth(dps);
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setRange(int range){
        this.range=range;
    }
    Troop(int health,int dps,int range,Type type,ImageView view,int x,int y){
        this.health=health;
        this.dps=dps;
        this.range=range;
        this.type=type;
        this.view=view;
        this.x=x;
        this.y=y;
        currentHP=health;
    }
}
