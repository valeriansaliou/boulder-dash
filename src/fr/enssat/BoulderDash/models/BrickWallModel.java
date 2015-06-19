package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;

/**
 * BrickWallModel
 * Represents the brick wall in the game
 * @author colinleverger
 */
public class BrickWallModel extends DisplayableElementModel implements PublisherInterface {
	private static String pathToSprite;
	private static String spriteName;
	private static boolean isDestructible ;
	private static boolean canMove ;
	private static boolean impactExplosive ;
	private static boolean animate ;
	private static int priority ;
	
	static {
		spriteName = "brickwall";
		isDestructible = true;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 3;
	}

	public BrickWallModel() {
		super(isDestructible, canMove, pathToSprite, priority, impactExplosive, animate);

        this.loadSprite(spriteName);
	}
}
