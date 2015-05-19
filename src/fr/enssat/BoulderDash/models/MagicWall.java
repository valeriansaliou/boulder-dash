package fr.enssat.BoulderDash.models;

public class MagicWall extends ElementDisplayable {

	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = false;
	private static boolean canMove = false;
	private static boolean impactExplosive = false;
	private static boolean animate = false;
	private static int priority = 10;
	private Boolean state;

	public MagicWall(int x, int y, Boolean state) {
		super(isDestructible, canMove, x, y, pathToSprite, priority,
				impactExplosive, animate);
		// magic wall can take two states :
		// - normal, which means that it'll destroy the stone block passing -->
		// FALSE
		// - magic, which means that it'll change the stone block passing into
		// diamonds --> TRUE
		this.setState(state);
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

}
