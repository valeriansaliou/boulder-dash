package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;

/**
 * SteelWallModel, it represents the steelWall
 * @author colinleverger
 *
 */
public class SteelWallModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;

	static {
		spriteName = "steelwall";
		isDestructible = false;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 3;
	}

	public SteelWallModel() {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate);
		this.loadSprite(spriteName);
	}
}
