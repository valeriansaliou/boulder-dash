package fr.enssat.BoulderDash.models;

public class MovableElementModel extends ElementDisplayable implements PublisherBridge {
    public MovableElementModel(String pathToSprite, int priority, int x, int y) {
        super(isDestructible, canMove, x, y, pathToSprite, priority,
                impactExplosive, animate);
    }
}
