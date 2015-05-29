package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.LevelLoadInterface;
import fr.enssat.BoulderDash.interfaces.SubscriberInterface;

public abstract class LevelModel implements LevelLoadInterface, SubscriberInterface {

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