package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class BoulderModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private BufferedImage img;

	static {
		spriteName = "boulder";
		isDestructible = false;
		canMove = true;
		impactExplosive = false;
		animate = true;
		priority = 10;
	}

	public BoulderModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority, impactExplosive, animate);
		this.img = loadSprite(spriteName);
	}
}