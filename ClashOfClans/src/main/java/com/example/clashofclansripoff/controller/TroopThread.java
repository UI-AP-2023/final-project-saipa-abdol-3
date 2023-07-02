package com.example.clashofclansripoff.controller;

import com.example.clashofclansripoff.model.*;

import java.util.ArrayList;

public class TroopThread extends Thread {
    private Map map;
    private Troop troop;

    @Override
    public void run() {
        ArrayList<Building> defenses = new ArrayList<>();
        ArrayList<Building> nonDefenses = new ArrayList<>();
        for (Building building : map.getBuildings()) {
            if (building instanceof ArcherTower || building instanceof Cannon) {
                defenses.add(building);
            } else {
                nonDefenses.add(building);
            }
        }
        if (troop instanceof Giant) {
            while (defenses != null) {
                int minDistance = (int)Double.POSITIVE_INFINITY;
                Building closest=null;
                for (Building building : defenses) {
                    int distance=getDistance(building);
                    if(distance<minDistance){
                        minDistance=distance;
                        closest=building;
                    }
                }
                for (Building building:nonDefenses){
                    int distance=getDistance(building);
                    if(distance<minDistance){
                        minDistance=distance;
                        closest=building;
                    }
                }
                moveTroop(closest);
            }
        } else {

        }
    }

    private int getXDistance(Building building){
        return building.getX()-troop.getX();
    }
    private int getYDistance(Building building){
        return building.getY()-troop.getY();
    }
    private int getDistance(Building building) {
        int distance = (int)(Math.sqrt(Math.pow(getXDistance(building), 2) + Math.pow(getYDistance(building), 2)));
        return distance;
    }
    private void moveTroop(Building building){
        while (getDistance(building)>troop.getRange()){
            double slope=(double)getYDistance(building)/getXDistance(building);
            if(getXDistance(building)>=0){
                int dy=(int)Math.sqrt(10/(1+Math.pow(1/slope,2)));
                int dx=(int)(1/slope)*dy;
                troop.changeX(dx);
                troop.changeY(dy);
            }
        }
    }
}
