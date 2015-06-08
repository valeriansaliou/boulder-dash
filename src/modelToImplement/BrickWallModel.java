package modelToImplement;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;

public class BrickWallModel extends DisplayableElementModel implements PublisherInterface {

	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = true;
	private static boolean canMove = false;
	private static boolean impactExplosive = false;
	private static boolean animate = false;
	private static int priority = 10;

	public BrickWallModel(int x, int y) {
		super(isDestructible, canMove, x, y, pathToSprite, priority, impactExplosive, animate);
	}
}
