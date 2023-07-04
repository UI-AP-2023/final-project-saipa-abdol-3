package com.example.clashofclansripoff.model;

import com.example.clashofclansripoff.controller.DatabaseController;
import javafx.scene.image.ImageView;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Building {
    public enum Type{
        TownHall,Cannon,Elixir,Gold,Archer
    }
    private int health;
    private int currentHealth;
    private Type type;
    private int x;
    private int y;
    private int level;
    private int range;
    private static AtomicInteger buildingCount;
    private String username;
    private ImageView view;
    public Building(Type type, int health, String username, int x, int y,int level,ImageView view,int range) throws Exception{
        buildingCount=new AtomicInteger(DatabaseController.getMaxID());
        this.health=health;
        currentHealth=health;
        this.type=type;
        this.username=username;
        this.view=view;
        this.x=x;
        this.y=y;
        this.level=level;
        this.range=range;
    }

    public int getHealth() {
        return health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Type getType() {
        return type;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void changeCurrentHealth(int change) {
        this.currentHealth -= change;
    }

    public static AtomicInteger getBuildingCount() {
        return buildingCount;
    }
    public void doDamage(Troop troop){
        troop.changeCurrentHP(10);
    }

    public int getRange() {
        return range;
    }
}
