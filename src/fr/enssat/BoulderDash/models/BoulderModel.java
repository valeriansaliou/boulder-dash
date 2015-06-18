package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

/**
 * BoulderModel, it represents the boulders.
 * @author colinleverger
 *
 */
public class BoulderModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private boolean falling;
	
	static {
		spriteName = "boulder";
		isDestructible = false;
		canMove = true;
		impactExplosive = false;
		animate = true;
		priority = 2;
	}

	public BoulderModel() {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate);
		this.falling = false;
		loadSprite(spriteName);
	}

	public boolean isFalling() {
		return this.falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}
}