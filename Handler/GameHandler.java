package com.example.mafia.Handler;

import com.example.mafia.Game.Player;
import com.example.mafia.Roles.Role;

import java.util.ArrayList;

public class GameHandler {
    private static final int maxPlayerCount = 25;
    private static ArrayList<Player> alivePlayers = new ArrayList<>();
    private static ArrayList<Player> initPlayers = new ArrayList<>();
    private static ArrayList<String> initRoles = new ArrayList<>();
    private static String[] roleSeqNight = {"butler", "godfather", "surgeon", "silencer", "detective", "sniper", "doctor", "priest"};
    private static int curRoleIndex = -1;

    public static int getMaxPlayerCount() {
        return maxPlayerCount;
    }

    public static void addPlayer(String name, String role) {

        Player player = new Player(name, Role.createRole(role));
        initPlayers.add(player);
        alivePlayers.add(player);
        initRoles.add(role);
    }

    public static void resetNight() {
        curRoleIndex = -1;
    }

    public static void clearPlayers() {
        initPlayers.clear();
        alivePlayers.clear();
        initRoles.clear();
    }

    public static ArrayList<Player> getInitPlayers() {
        return initPlayers;
    }

    public static ArrayList<Player> getAlivePlayers() {
        return alivePlayers;
    }

    public static ArrayList<String> getAlivePlayersNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Player player : alivePlayers)
            arrayList.add(player.getName());
        return arrayList;
    }

    public static boolean exist(String role) {
        if (role.startsWith("role_"))
            return initRoles.contains(role);
        return initRoles.contains("role_" + role);
    }

    public static String getNameByRole(String role) {
        for (Player player : initPlayers)
            if (player.getRole().toString().equals(role))
                return player.getName();
        return null;
    }

    public static String nextRole() {
        curRoleIndex++;
        if (curRoleIndex >= roleSeqNight.length)
            return "end";
        while (!exist(roleSeqNight[curRoleIndex])) {
            curRoleIndex++;
            if (curRoleIndex >= roleSeqNight.length)
                return "end";
        }
        return roleSeqNight[curRoleIndex];
    }
}
