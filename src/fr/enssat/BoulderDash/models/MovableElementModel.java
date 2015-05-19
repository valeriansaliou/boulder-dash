package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.bridges.PublisherBridge;

public class MovableElementModel extends ElementDisplayableModel implements PublisherBridge {
    public MovableElementModel(String pathToSprite, int priority, int x, int y) {
        super(isDestructible, canMove, x, y, pathToSprite, priority,
                impactExplosive, animate);
    }
}
