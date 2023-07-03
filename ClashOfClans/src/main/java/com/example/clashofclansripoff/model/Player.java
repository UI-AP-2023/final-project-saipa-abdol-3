package com.example.clashofclansripoff.model;

import java.util.Random;

public class Player {
    private int playerLevel;
    private int victories;
    private int losses;
    private Map map;
    private int buildingCount;
    private String username;
    private String password;
    private String email;
    private int age;
    public Player(String username,String password,String email,int age) throws Exception{//not from database
            this.username=username;
            this.password=password;
            this.email=email;
            this.age=age;
            playerLevel = 1;
            victories = 0;
            losses=0;
            map=new Map(false,username);
            buildingCount = 1;
    }
    public Player(int playerLevel, int victories,int losses, Map map, int buildingCount,
                  String username, String password, String email, int age){//from database
       this.playerLevel=playerLevel;
       this.victories=victories;
       this.losses=losses;
       this.map=map;
       this.buildingCount=buildingCount;
       this.username=username;
       this.password=password;
       this.email=email;
       this.age=age;
    }
    public Player()throws Exception{//random player
        Random rand=new Random();
        this.username= String.valueOf((char) rand.nextInt(65, 91)) +
                (char) rand.nextInt(97, 123) +
                (char) rand.nextInt(65, 91) +
                (char) rand.nextInt(97, 123) +
                (char) rand.nextInt(65, 91) +
                (char) rand.nextInt(97, 123) +
                (char) rand.nextInt(65, 91) +
                (char) rand.nextInt(97, 123);
        map=new Map(false,username);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public int getBuildingCount() {
        return buildingCount;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public int getVictories() {
        return victories;
    }

    public Map getMap() {
        return map;
    }

    public int getLosses() {
        return losses;
    }
    //    public boolean passwordMatches(String password) {
//        return this.password.equals(password);
//    }
//    public boolean usernameMatches(String username) {
//        return this.username.equals(username);
//    }
}
