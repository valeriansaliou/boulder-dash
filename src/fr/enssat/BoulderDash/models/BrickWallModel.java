package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

/**
 * BrickWallModel, it represents the brick wall in the game
 * @author colinleverger
 *
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
		priority = 10;
	}
	public BrickWallModel(int x, int y) {
		super(isDestructible, canMove, x, y, pathToSprite, priority, impactExplosive, animate);
		loadSprite(spriteName);
	}
}
