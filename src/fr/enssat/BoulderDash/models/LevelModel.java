package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.bridges.LevelLoadBridge;

public abstract class LevelModel implements LevelLoadBridge implements SubscriberBridge {

    private String name = "";
    private int id = -1;

    public LevelModel() {

    }

    public String getName() {
        return this.name;
    }

    protected String setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    protected String setId(integer id) {
        this.id = id;
    }
}