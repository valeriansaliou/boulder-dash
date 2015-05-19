package fr.enssat.BoulderDash.models;

public class AmoebaModel extends MovableElementModel {
    private static String spriteName;
    private static int priority;

    static {
        spriteName = "field_00";
        priority = 2;
    }

    public AmoebaModel(int x, int y) {
        super(spriteName, priority, x, y);
    }
}