package com.example.mafia.Handler;

import android.content.Context;
import android.util.Log;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataHandler {
    public static void saveNames(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("names.txt", Context.MODE_PRIVATE));
            for (String name : NameHandler.getNames())
                outputStreamWriter.write(name + "\n");
            outputStreamWriter.close();
        } catch (Exception e) {
            Log.e("Exception", "File write failed");
        }
    }

    public static void saveRoles(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("roles.txt", Context.MODE_PRIVATE));
            for (String role : RoleHandler.getRoles())
                outputStreamWriter.write(role + "\n");
            outputStreamWriter.close();
        } catch (Exception e) {
            Log.e("Exception", "File write failed");
        }
    }

    public static void loadNames(Context context) {
        NameHandler.clearNames();
        try {
            InputStream inputStream = context.openFileInput("names.txt");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String name = "";
                while ((name = bufferedReader.readLine()) != null) {
                    NameHandler.addName(name);
                }
                inputStream.close();
            }
            if (NameHandler.getNames().size() == 0) {
                NameHandler.clearNames();
                for (int i = 0; i < 5; i++)
                    NameHandler.add();
            }

        } catch (Exception e) {
            Log.e("Exception", "File read failed");
        }
    }

    public static void loadRoles(Context context) {
        RoleHandler.clearRoles();
        try {
            InputStream inputStream = context.openFileInput("roles.txt");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String role = "";
                int i=0;
                while ((role = bufferedReader.readLine()) != null) {
                    RoleHandler.setRole(i,role);
                    i++;
                }
                inputStream.close();
            }
        } catch (Exception e) {
            Log.e("Exception", "File read failed");
        }
    }
}
