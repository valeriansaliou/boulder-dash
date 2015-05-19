package fr.enssat.BoulderDash.models;

public class Boulder extends ElementDisplayable {

	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = false;
	private static boolean canMove = true;
	private static boolean impactExplosive = false;
	private static boolean animate = true;
	private static int priority = 10;
	private final double gravity = 9.85;
	private boolean isFalling;

	public Boulder(int x, int y) {
		super(isDestructible, canMove, x, y, pathToSprite, priority,
				impactExplosive, animate);
		this.isFalling = false;
	}

	public void fallingSpeed() {
		// TODO IN FUNCTION OF OUR NEED
	}

	public boolean isFalling() {
		return false;
		// TODO
	}

}
