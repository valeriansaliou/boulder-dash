package fr.enssat.BoulderDash.models;

public class AmoebaModel extends MovableElementModel {
    private static String pathToSprite = "insert/path/down/here";
    private static int priority = 2;

    public AmoebaModel(int x, int y) {
        super(pathToSprite, priority, x, y);
    }
}
