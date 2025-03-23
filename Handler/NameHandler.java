package com.example.mafia.Handler;

import java.util.ArrayList;

public class NameHandler {
    private static ArrayList<String> names = new ArrayList<>();

    public static ArrayList<String> getNames() {
        return names;
    }

    public static void add() {
        int i=1;
        while (names.contains("Player "+i))
            i++;
        names.add(0,"Player "+(i));
    }

    public static void addName(String string) {
        names.add(string);
    }

    public static void clearNames() {
        names = new ArrayList<>();
    }

    public static void setName(int i, String name) {
        names.set(i,name);
    }

    public static void removeName(int index) {
        names.remove(index);
    }

    public static int getCount() {
        return names.size();
    }

    public static String getName(int i) {
        return names.get(i);
    }
}
