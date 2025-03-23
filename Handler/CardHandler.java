package com.example.mafia.Handler;

import com.example.mafia.Utils;

import java.util.ArrayList;


public class CardHandler {
    private static ArrayList<String> shuffledNames = new ArrayList<>();
    private static ArrayList<String> shuffledRoles = new ArrayList<>();

    public static ArrayList<String> getShuffledNames() {
        return shuffledNames;
    }
    public static ArrayList<String> getShuffledRoles() {
        return shuffledRoles;
    }

    public static void shuffle() {
        GameHandler.clearPlayers();
        shuffledNames.clear();
        shuffledRoles.clear();
        for (String name:NameHandler.getNames())
            shuffledNames.add(Utils.random.nextInt(shuffledNames.size()+1),name);
        for (String role : RoleHandler.getSelectedRoles())
            shuffledRoles.add(Utils.random.nextInt(shuffledRoles.size()+1),role);
        for (int i = 0; i < shuffledNames.size(); i++)
            GameHandler.addPlayer(shuffledNames.get(i),shuffledRoles.get(i));

    }

    public static void seen(int index){
        shuffledNames.remove(index);
        shuffledRoles.remove(index);
    }
}
