package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class MovableBlockModel extends StaticBlockModel implements PublisherInterface {
    public MovableBlockModel(String pathToSprite, int priority, int x, int y) {
        super(isDestructible, canMove, x, y, pathToSprite, priority,
                impactExplosive, animate);
    }
}
