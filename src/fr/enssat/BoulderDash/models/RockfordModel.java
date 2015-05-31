package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class RockfordModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;

	static {
		spriteName = "field_00";
		isDestructible = true;
		canMove = true;
		impactExplosive = true;
		animate = true;
		priority = 8;
	}

	public RockfordModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority,
				impactExplosive, animate);
	}
}
