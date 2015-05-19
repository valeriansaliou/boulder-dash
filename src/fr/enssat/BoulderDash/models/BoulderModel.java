package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.bridges.PublisherBridge;

public class BoulderModel extends ElementDisplayableModel implements PublisherBridge {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;

	private final double gravity = 9.85;
	private boolean isFalling;

	static {
		spriteName = "field_00";
		isDestructible = false;
		canMove = true;
		impactExplosive = false;
		animate = true;
		priority = 10;
	}

	public BoulderModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority,
				impactExplosive, animate);
	}

	public void fallingSpeed() {
		// TODO IN FUNCTION OF OUR NEED
	}

	public boolean isFalling() {
		return false;
		// TODO
	}
}