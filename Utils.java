package com.example.mafia;

import java.util.Random;

public class Utils {
    public static Random random=new Random();

    public static String firstCapital(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
