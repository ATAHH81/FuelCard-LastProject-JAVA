package com.example.fuelcard.model;

public class UserModel {

    private String name;
    private String nationalCode;
    private String password;
    private String carCardNumber;
    private String carSanadNumber;
    private String carName;
    private String carModel;
    private int etebar;

    public UserModel(){}
    public UserModel(String name, String nationalCode, String password, String carCardNumber, String carSanadNumber, String carName, String carModel, int etebar) {
        this.name = name;
        this.nationalCode = nationalCode;
        this.password = password;
        this.carCardNumber = carCardNumber;
        this.carSanadNumber = carSanadNumber;
        this.carName = carName;
        this.carModel = carModel;
        this.etebar = etebar;
    }

    public String getName() {
        return name;
    }

    public UserModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public UserModel setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCarCardNumber() {
        return carCardNumber;
    }

    public UserModel setCarCardNumber(String carCardNumber) {
        this.carCardNumber = carCardNumber;
        return this;
    }

    public String getCarSanadNumber() {
        return carSanadNumber;
    }

    public UserModel setCarSanadNumber(String carSanadNumber) {
        this.carSanadNumber = carSanadNumber;
        return this;
    }

    public String getCarName() {
        return carName;
    }

    public UserModel setCarName(String carName) {
        this.carName = carName;
        return this;
    }

    public String getCarModel() {
        return carModel;
    }

    public UserModel setCarModel(String carModel) {
        this.carModel = carModel;
        return this;
    }

    public int getEtebar() {
        return etebar;
    }

    public UserModel setEtebar(int etebar) {
        this.etebar = etebar;
        return this;
    }
}
