package fr.enssat.BoulderDash.models;

public class ButterflyModel extends MovableElementModel {
    private static String spriteName;
    private static int priority;

    static {
        spriteName = "field_00";
        priority = 2;
    }

    public ButterflyModel(int x, int y) {
        super(spriteName, priority, x, y);
    }
}
