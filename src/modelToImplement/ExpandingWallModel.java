package modelToImplement;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;

public class ExpandingWallModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private static boolean falling;

	static {
		spriteName = "field_00";
		isDestructible = true;
		canMove = false;
		impactExplosive = false;
		animate = true;
		priority = 10;
		falling = false;
	}

	public ExpandingWallModel() {
		super(animate, animate, spriteName, priority, animate, animate, falling);//TODO IT IS NOT IN THE RIGHT ORDER
	}
}