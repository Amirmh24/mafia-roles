package com.example.mafia.Roles;

import androidx.annotation.NonNull;

import com.example.mafia.Utils;


public abstract class Role {
    private final Side side;

    public static Role createRole(String str) {
        try {
            str = "com.example.mafia.Roles." + Utils.firstCapital(str);
            return (Role) Class.forName(str).newInstance();
        }catch (Exception ignored) {
            return null;
        }
    }

    public static String getRoleTitle(String str) {
        if (str.startsWith("role_"))
            return str.substring(5);
        return str;
    }

    Role(Side side) {
        this.side = side;
    }

    public Side getSide() {
        return side;
    }

    @NonNull
    @Override
    public String toString() {
        String str=getClass().toString();
        str=str.substring(str.lastIndexOf('_')+1);
        return str;
    }
}
