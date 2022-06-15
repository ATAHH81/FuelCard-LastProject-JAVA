package com.example.fuelcard.model;

public class MizanSookhtModel {

    private int person;
    private int taxi;
    private int bus;

    public MizanSookhtModel(){}
    public MizanSookhtModel(int person, int taxi, int bus) {
        this.person = person;
        this.taxi = taxi;
        this.bus = bus;
    }

    public int getPerson() {
        return person;
    }

    public MizanSookhtModel setPerson(int person) {
        this.person = person;
        return this;
    }

    public int getTaxi() {
        return taxi;
    }

    public MizanSookhtModel setTaxi(int taxi) {
        this.taxi = taxi;
        return this;
    }

    public int getBus() {
        return bus;
    }

    public MizanSookhtModel setBus(int bus) {
        this.bus = bus;
        return this;
    }
}
