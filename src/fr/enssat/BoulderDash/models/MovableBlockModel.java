package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class MovableBlockModel extends StaticBlockModel implements PublisherInterface {
    public MovableBlockModel(String pathToSprite, int priority, int x, int y) {
    		super(true, true, y, y, pathToSprite, y, true, true);
    }
}
