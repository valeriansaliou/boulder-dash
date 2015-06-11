package fr.enssat.BoulderDash.models;

/**
 * EmptyModel, represent "nothing"
 * @author colinleverger
 *
 */
public class EmptyModel extends DisplayableElementModel {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;

	static {
		spriteName = "black";
		isDestructible = false;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 0;
	}

	public EmptyModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority, impactExplosive, animate);
		loadSprite(spriteName);
	}
}
