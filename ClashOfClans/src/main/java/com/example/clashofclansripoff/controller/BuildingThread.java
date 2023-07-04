package com.example.clashofclansripoff.controller;

import com.example.clashofclansripoff.Main;
import com.example.clashofclansripoff.Stages.AttackStage;
import com.example.clashofclansripoff.model.Building;
import com.example.clashofclansripoff.model.Map;
import com.example.clashofclansripoff.model.Troop;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BuildingThread extends Thread {
    private Building building;
    private Map map;

    public BuildingThread(Map map, Building building) {
        this.map = map;
        this.building = building;
    }

    @Override
    public void run() {
        synchronized (""){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (map.getTroops().size() > 0 && building.getCurrentHealth() > 0) {
            int minDistance = (int) Double.POSITIVE_INFINITY;
            Troop closest = null;
            for (Troop troop : map.getTroops()) {
                int distance = getDistance(troop);
                if (distance < minDistance && inRange(troop)) {
                    minDistance = distance;
                    closest = troop;
                }
            }
            if (closest != null) {
                try {
                    System.out.println("Attacking closest troop");
                    attack(closest);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Finished building thread");
    }

}

    private int getXDistance(Troop troop) {
        return troop.getX() - building.getX();
    }

    private int getYDistance(Troop troop) {
        return troop.getY() - building.getY();
    }

    private int getDistance(Troop troop) {
        int distance = (int) (Math.sqrt(Math.pow(getXDistance(troop), 2) + Math.pow(getYDistance(troop), 2)));
        return distance;
    }

    private void attack(Troop troop) {
        int i = 0;
        while (i++ < 20) {
                ImageView projectile = new ImageView(new Image(Main.class.getResourceAsStream("Fireball.png")));
                projectile.setFitWidth(15);
                projectile.setFitHeight(15);
                projectile.setX(building.getX());
                projectile.setY(building.getY());

                Platform.runLater(
                        () -> {
                            AttackStage.getRoot().getChildren().add(projectile);
                            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), projectile);
                            transition.setByX(getXDistance(troop));
                            transition.setByY(getYDistance(troop));
                            transition.play();
                        }
                );
                troop.changeCurrentHP(10);
                if (troop.getCurrentHP() < 0) {
                    map.getTroops().remove(troop);
                    if (AttackStage.getRoot().getChildren().contains(troop.getView())) {
                        AttackStage.getRoot().getChildren().remove(troop.getView());
                    }
                }
                try {
                    Thread.sleep(500);
                    Platform.runLater(
                            () -> {
                                AttackStage.getRoot().getChildren().remove(projectile);
                            }
                    );
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    private boolean inRange(Troop troop){
        return (Math.abs(troop.getX()- building.getX())< building.getRange())&&(Math.abs(troop.getY()- building.getY())< building.getRange());
    }
}
