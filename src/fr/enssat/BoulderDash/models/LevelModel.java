package fr.enssat.BoulderDash.models;

public abstract class LevelModel implements LevelLoadInterface implements SubscriberBridge {

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