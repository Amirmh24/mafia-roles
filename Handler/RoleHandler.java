package com.example.mafia.Handler;

import com.example.mafia.Roles.Side;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleHandler {
    private static String[] rg = {"joker", "mask", "crazy", "wolf", "ghost", "thief", "witch"};
    private static String[] rw = {"doctor", "detective", "citizen", "sniper", "mayor", "armored", "judge", "butler", "priest", "admiral", "healer", "pitman", "hacker", "gunman", "cowboy", "madam", "lawyer", "reporter", "bodyguard", "traitor"};
    private static String[] rb = {"godfather", "mistress", "negotiator", "surgeon", "mafia", "mafias", "silencer", "terrorist", "bomber", "poisoner", "nato", "yakuza", "coercive", "intriguant", "shady", "spy"};
    private static String nullRole = "role_null";
    public static int maxCount = GameHandler.getMaxPlayerCount(), cols = 9, rows = maxCount / cols + 1;


    private static ArrayList<String> selectedRoles = new ArrayList<>();
    private static List<String> rolesG = Arrays.asList(rg);
    private static List<String> rolesW = Arrays.asList(rw);
    private static List<String> rolesB = Arrays.asList(rb);
    private static String[] roles = new String[maxCount];

    public static String[] getRoles() {
        return roles;
    }


    public static void clearRoles() {
        Arrays.fill(roles, nullRole);
        selectedRoles.clear();
    }

    public static void setRole(int i, String role) {
        if (roles[i].equals(nullRole) && !role.equals(nullRole))
            selectedRoles.add(role);
        roles[i] = role;
    }

    public static void removeRole(int i) {
        selectedRoles.remove(roles[i]);
        roles[i] = nullRole;
    }

    public static int getCount() {
        return selectedRoles.size();
    }

    public static String getRole(int i) {
        return roles[i];
    }

    public static List<String> getRoles(Side side) {
        if (side == Side.GRAY) return rolesG;
        else if (side == Side.WHITE) return rolesW;
        else return rolesB;
    }

    public static int getNextNullSlot() {
        for (int i = 0; i < maxCount; i++)
            if (roles[i].equals(nullRole)) return i;
        return -1;
    }

    public static int getIndex(String role) {
        for (int i = 0; i < maxCount; i++)
            if (roles[i].equals(role))
                return i;
        return -1;
    }

    public static ArrayList<String> getSelectedRoles() {
        return selectedRoles;
    }
}
