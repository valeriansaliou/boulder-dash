package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.bridges.PublisherBridge;

public class RockfordModel extends ElementDisplayableModel implements PublisherBridge {
	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = true;
	private static boolean canMove = true;
	private static boolean impactExplosive = true;
	private static boolean animate = true;
	private static int priority = 8;

	public RockfordModel(int x, int y) {
		super(isDestructible, canMove, x, y, pathToSprite, priority,
				impactExplosive, animate);
	}
}
