package com.example.mafia.Game;

import com.example.mafia.Roles.Role;

import java.util.ArrayList;

public class Player {

    private String name;
    private Role role;

    public Player(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}
