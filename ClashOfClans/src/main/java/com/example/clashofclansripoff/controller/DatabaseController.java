package com.example.clashofclansripoff.controller;

import com.example.clashofclansripoff.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseController {
    private static final String url = "jdbc:mysql://localhost/clashofclans";
    private static final String username = "root";
    private static final String password = "AdelAdel105@";
    private static ArrayList<Player> playersList;
    private static Player currentUser;

    public static Player getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Player currentUser) {
        DatabaseController.currentUser = currentUser;
    }

    public static ArrayList<Player> getPlayersDB() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        try {
            ArrayList<Player> players = new ArrayList<>();
            String command = "SELECT * FROM players";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(command);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String command1 = "SELECT * from Buildings WHERE buildings.username LIKE " + username;
                Statement statement1 = connection.createStatement();
                ResultSet resultSet1 = statement1.executeQuery("SELECT * from Buildings");
                Map map = new Map(true,"");//map is from database
                while (resultSet1.next()) {
                    if (resultSet1.getString("username").equals(username)) {
                        switch (resultSet1.getString("type")) {
                            case "TownHall": {
                                map.getBuildings().add(new TownHall(resultSet1.getString("username"),Integer.parseInt(resultSet1.getString("x")), Integer.parseInt(resultSet1.getString("y")), Integer.parseInt(resultSet1.getString("level"))));
                            }
                            break;
                            case "Cannon": {
                                map.getBuildings().add(new Cannon(resultSet1.getString("username"),Integer.parseInt(resultSet1.getString("x")), Integer.parseInt(resultSet1.getString("y"))));
                            }
                            break;
                            case "Elixir": {
                                map.getBuildings().add(new ElixirStorage(resultSet1.getString("username"),Integer.parseInt(resultSet1.getString("x")), Integer.parseInt(resultSet1.getString("y"))));
                            }
                            break;
                            case "Gold": {
                                map.getBuildings().add(new GoldStorage(resultSet1.getString("username"),Integer.parseInt(resultSet1.getString("x")), Integer.parseInt(resultSet1.getString("y"))));
                            }
                            break;
                            case "Archer": {
                                map.getBuildings().add(new ArcherTower(resultSet1.getString("username"),Integer.parseInt(resultSet1.getString("x")), Integer.parseInt(resultSet1.getString("y"))));
                            }
                            break;
                        }
                    }
                }
                players.add(new Player(Integer.parseInt(resultSet.getString("level")), Integer.parseInt(resultSet.getString("victories")),Integer.parseInt(resultSet.getString("losses")), map,
                        Integer.parseInt(resultSet.getString("buildings")), resultSet.getString("username"),
                        resultSet.getString("password"), resultSet.getString("email"), Integer.parseInt(resultSet.getString("age"))));
            }
            connection.close();
            playersList = players;
            return players;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addPlayer(Player player) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        String command = String.format("INSERT INTO players (username, password, email,age,level,victories,losses,buildings) VALUES ('%s','%s','%s','%d','%d','%d','%d','%d')",
                player.getUsername(), player.getPassword(), player.getEmail(),player.getAge(),player.getPlayerLevel(),player.getVictories(),player.getLosses(),player.getBuildingCount());
        Statement statement = connection.prepareStatement(command);
        statement.execute(command);
        connection.close();
    }
    public static void addToVictories()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        String command =String.format("UPDATE `players` SET `victories` = '%d' WHERE `players`.`username` = '%s';",(currentUser.getVictories()+1),currentUser.getUsername());
        Statement statement = connection.prepareStatement(command);
        statement.execute(command);
        connection.close();
    }
    public static void addToLosses()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        String command =String.format("UPDATE `players` SET `losses` = '%d' WHERE `players`.`username` = '%s';",(currentUser.getLosses()+1),currentUser.getUsername());
        Statement statement = connection.prepareStatement(command);
        statement.execute(command);
        connection.close();
    }

    public static void addBuilding(Building building) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        String command = String.format("INSERT INTO buildings (username, type, health,x,y,level,buildingID) VALUES ('%s','%s','%d','%d','%d','%d','%d')",
                building.getUsername(), building.getType().toString(), building.getHealth(),building.getX(),building.getY(),building.getLevel(),Building.getBuildingCount().incrementAndGet());
        Statement statement = connection.prepareStatement(command);
        statement.execute(command);
        connection.close();
    }
    public static int getMaxID()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        String command = "SELECT max(buildingID) FROM buildings";
        Statement statement = connection.prepareStatement(command);
        ResultSet resultSet=statement.executeQuery(command);
        int maxID=0;
        while (resultSet.next()){
            maxID=resultSet.getInt(1);
        }
        connection.close();
        return maxID;
    }

    public static ArrayList<Player> getPlayers() {
        return playersList;
    }
}
