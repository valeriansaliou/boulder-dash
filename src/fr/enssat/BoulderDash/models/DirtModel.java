package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
/**
 * DirtModel, it represents the dirt in the game.
 * @author colinleverger
 *
 */
public class DirtModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;

	static {
		spriteName = "dirt";
		isDestructible = true;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 0;
	}

	public DirtModel() {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate);
		loadSprite(spriteName);
	}

	public String getSpriteName() {
		return spriteName;
	}

}