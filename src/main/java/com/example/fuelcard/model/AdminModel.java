package com.example.fuelcard.model;

public class AdminModel {
    private String name;
    private String username;
    private String password;

    public AdminModel(){}

    public AdminModel(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public AdminModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AdminModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdminModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
