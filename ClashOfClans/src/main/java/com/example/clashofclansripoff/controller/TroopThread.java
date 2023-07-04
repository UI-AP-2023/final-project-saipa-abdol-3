package com.example.clashofclansripoff.controller;

import com.example.clashofclansripoff.Main;
import com.example.clashofclansripoff.Stages.AttackStage;
import com.example.clashofclansripoff.Stages.ResultStage;
import com.example.clashofclansripoff.model.*;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class TroopThread extends Thread {
    private Map map;
    private Troop troop;
    private int index;
    private ArrayList<Building> nonDefenses;
    private ArrayList<Building> defenses;
    private static int count =0;
    private static boolean stageStarted;
    private static boolean destroyedTH;
    private int percentage;
    public TroopThread(Map map, Troop troop) {
        index=++count;
        this.map = map;
        this.troop = troop;
    }

    @Override
    public void run() {
        defenses = new ArrayList<>();
        nonDefenses = new ArrayList<>();
        destroyedTH=false;
        stageStarted=false;
        for (Building building : map.getBuildings()) {
            if (building instanceof ArcherTower || building instanceof Cannon) {
                defenses.add(building);
            } else {
                nonDefenses.add(building);
            }
        }
        if (troop instanceof Giant) {
            while (map.getBuildings().size()>0 && troop.getCurrentHP() > 0) {
                int minDistance = (int) Double.POSITIVE_INFINITY;
                Building closest = null;
                for (Building building : defenses) {
                    int distance = getDistance(building);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closest = building;
                    }
                }
                if(defenses.size()==0) {
                    for (Building building : nonDefenses) {
                        int distance = getDistance(building);
                        if (distance < minDistance) {
                            minDistance = distance;
                            closest = building;
                        }
                    }
                }
                if(closest!=null) {
                    try {
                        moveTroop(closest);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } else {
            while (map.getBuildings().size()>0 && troop.getCurrentHP() > 0) {
                int minDistance = (int) Double.POSITIVE_INFINITY;
                Building closest = null;
                for (Building building : map.getBuildings()) {
                    int distance = getDistance(building);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closest = building;
                    }
                }
                if(closest!=null) {
                    try {
                        moveTroop(closest);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        System.out.println("Thread "+ index +" stopped.");
    }

    private int getXDistance(Building building) {
        return building.getX() - troop.getX() + 37;
    }

    private int getYDistance(Building building) {
        return building.getY() - troop.getY() + 37;
    }

    private int getDistance(Building building) {
        int distance = (int) (Math.sqrt(Math.pow(getXDistance(building), 2) + Math.pow(getYDistance(building), 2)));
        return distance;
    }

    private void moveTroop(Building building) throws Exception {
        while (getDistance(building) > troop.getRange()) {
            double slope = (double) getYDistance(building) / getXDistance(building);
            double dy = Math.sqrt(100 / (1 + Math.pow(1 / slope, 2)));
            if (getYDistance(building) < 0) {
                dy *= -1;
            }
            double dx = (1 / slope) * dy;
            troop.changeX((int) dx);
            troop.changeY((int) dy);
            Thread.sleep(700);
        }
        if (troop instanceof Archer||troop instanceof Wizard) {
            int i = 0;
            while (i++ < 10&&building.getCurrentHealth()>0) {
                ImageView projectile = new ImageView(new Image(Main.class.getResourceAsStream("Fireball.png")));
                projectile.setFitWidth(15);
                projectile.setFitHeight(15);
                projectile.setX(troop.getX());
                projectile.setY(troop.getY());
                Platform.runLater(
                        () -> {
                            AttackStage.getRoot().getChildren().add(projectile);
                            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), projectile);
                            transition.setByX(getXDistance(building));
                            transition.setByY(getYDistance(building));
                            transition.play();
                        }
                );
                Thread.sleep(500);
                troop.doDamage(building);
                Platform.runLater(
                        () -> {
                            AttackStage.getRoot().getChildren().remove(projectile);
                            if (building.getCurrentHealth() < 0) {
                                if (building instanceof TownHall){
                                    System.out.println("Destroyed TH");
                                    destroyedTH=true;
                                }
                                AttackStage.getRoot().getChildren().remove(building.getView());
                                map.getBuildings().remove(building);
                                if(defenses.contains(building)){
                                    defenses.remove(building);
                                }
                                if (nonDefenses.contains(building)){
                                    nonDefenses.remove(building);
                                }
                                if(map.getBuildings().size()==0){
                                    System.out.println("Thread "+index+" stopped. TH"+destroyedTH);
                                    AttackStage.getStage().close();
                                    if(!stageStarted){
                                        try {
                                            ResultStage.getResultStage(!hasTownHall(map.getBuildings()),getPercentage()).show();
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                        stageStarted=true;
                                    }
                                    this.interrupt();
                                }
                            }
                        }
                );
                Thread.sleep(1000);
            }
        }else {
            int i=0;
            while (i++ < 10 && building.getCurrentHealth() > 0) {
                troop.doDamage(building);
                Platform.runLater(
                        () -> {
                            if (building.getCurrentHealth() < 0) {
                                if (building instanceof TownHall){
                                    destroyedTH=true;
                                }
                                AttackStage.getRoot().getChildren().remove(building.getView());
                                map.getBuildings().remove(building);
                                System.out.println(map.getBuildings().size());
                                if(defenses.contains(building)){
                                    defenses.remove(building);
                                }
                                if (nonDefenses.contains(building)){
                                    nonDefenses.remove(building);
                                }
                                if(map.getBuildings().size()==0){
                                    System.out.println("Thread "+index+" stopped.");
                                    AttackStage.getStage().close();
                                    try {
                                        ResultStage.getResultStage(!hasTownHall(map.getBuildings()),getPercentage()).show();
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    this.interrupt();
                                }
                            }
                        }
                );
                Thread.sleep(1000);
            }
        }
    }
    public static boolean hasTownHall(ArrayList<Building> buildings){
        for(Building building:buildings){
            if(building instanceof TownHall){
                return true;
            }
        }
        return false;
    }
    private int getPercentage(){
        int percentage=(int) (((double)(map.getBuildingCount()-map.getBuildings().size())/map.getBuildingCount())*100);
        return percentage;
    }
    public static int getPercentage(Map map){
        int percentage=(int) (((double)(map.getBuildingCount()-map.getBuildings().size())/map.getBuildingCount())*100);
        return percentage;
    }
}
