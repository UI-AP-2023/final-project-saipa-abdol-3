package com.example.clashofclansripoff.model;

public class Building {
    public enum Type{
        Defense,TownHall,Resources
    }
    private int health;
    private Type type;
    public Building(Type type,int health){
        this.health=health;
        this.type=type;
    }
}
