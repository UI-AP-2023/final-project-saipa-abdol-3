package com.example.clashofclansripoff.model;

import com.example.clashofclansripoff.controller.DatabaseController;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    private ArrayList<Building> buildings;
    private ArrayList<Troop> troops;
    private String username;
    private int buildingCount;

    public Map(boolean fromDatabase,String username)throws Exception{
        if (!fromDatabase) {
            this.username=username;
            Random rand = new Random();
            buildings = new ArrayList<>();
            Cannon cannon=new Cannon(username,rand.nextInt(200, 600), rand.nextInt(150, 300));
            ElixirStorage elixirStorage=new ElixirStorage(username,rand.nextInt(200, 600), rand.nextInt(150, 300));
            while (checkCollision(elixirStorage,cannon)){
                elixirStorage=new ElixirStorage(username,rand.nextInt(200, 600), rand.nextInt(150, 300));
            }
            GoldStorage goldStorage=new GoldStorage(username,rand.nextInt(200, 600), rand.nextInt(150, 300));
            while (checkCollision(elixirStorage,cannon)||checkCollision(elixirStorage,goldStorage)){
                goldStorage=new GoldStorage(username,rand.nextInt(200, 600), rand.nextInt(150, 300));
            }
            ArcherTower archerTower =new ArcherTower(username,rand.nextInt(200, 600), rand.nextInt(150, 300));
            while (checkCollision(archerTower,cannon)||checkCollision(archerTower,goldStorage)||checkCollision(archerTower,elixirStorage)){
                archerTower =new ArcherTower(username,rand.nextInt(200, 600), rand.nextInt(150, 300));
            }
            TownHall townHall=new TownHall(username,rand.nextInt(200, 600), rand.nextInt(150, 300), rand.nextInt(1, 16));
            while (checkCollision(townHall,cannon)||checkCollision(townHall,elixirStorage)||checkCollision(townHall,goldStorage)||checkCollision(townHall,archerTower)){
                townHall=new TownHall(username,rand.nextInt(200, 600), rand.nextInt(150, 300), rand.nextInt(1, 16));
            }
            DatabaseController.addBuilding(townHall);
            buildings.add(townHall);
            DatabaseController.addBuilding(archerTower);
            buildings.add(archerTower);
            troops = new ArrayList<>();
            DatabaseController.addBuilding(elixirStorage);
            buildings.add(elixirStorage);
            DatabaseController.addBuilding(goldStorage);
            buildings.add(goldStorage);
            DatabaseController.addBuilding(cannon);
            buildings.add(cannon);
            buildingCount= buildings.size();
        } else {
            buildings = new ArrayList<>();
            troops = new ArrayList<>();
        }
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }
    public static boolean checkCollision(Building building1,Building building2) {
        // Calculate the coordinates of the four corners of each square
        int x1Left = building1.getX();
        int x1Right = building1.getX() + 75;
        int y1Top = building1.getY();
        int y1Bottom = building1.getY() + 75;

        int x2Left = building2.getX();
        int x2Right = building2.getX() + 75;
        int y2Top = building2.getY();
        int y2Bottom = building2.getY() + 75;

        // Check for collision by comparing the coordinates of the corners
        if (x1Right >= x2Left &&
                x1Left <= x2Right &&
                y1Bottom >= y2Top &&
                y1Top <= y2Bottom) {
            return true; // Collision detected
        }

        return false; // No collision detected
    }

    public int getBuildingCount() {
        return buildingCount;
    }
}
