package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class MagicWallModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;

	static {
		spriteName = "field_00";
		isDestructible = false;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 10;
	}

	public MagicWallModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority,
				impactExplosive, animate);
	}
}
