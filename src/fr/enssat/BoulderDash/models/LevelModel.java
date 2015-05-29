package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.bridges.LevelLoadBridge;
import fr.enssat.BoulderDash.bridges.SubscriberBridge;

public abstract class LevelModel implements LevelLoadBridge, SubscriberBridge {

    private String name = "";
    private int id = -1;

    public LevelModel() {

    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    protected void setId(int id) {
        this.id = id;
    }
}