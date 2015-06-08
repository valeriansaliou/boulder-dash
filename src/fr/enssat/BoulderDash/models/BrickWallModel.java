package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class BrickWallModel extends DisplayableElementModel implements PublisherInterface {

	private static String pathToSprite;
	private static String spriteName;
	private BufferedImage img;
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
		this.img = loadSprite(spriteName);
	}
}
