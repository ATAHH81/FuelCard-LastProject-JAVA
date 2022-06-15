package com.example.fuelcard.model;

public class EblaghieModel {

    private String name;
    private String content;
    private String username;

    public EblaghieModel(){}
    public EblaghieModel(String name, String content, String username) {
        this.name = name;
        this.content = content;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public EblaghieModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public EblaghieModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public EblaghieModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
