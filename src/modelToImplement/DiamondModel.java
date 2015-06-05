package modelToImplement;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;

public class DiamondModel extends DisplayableElementModel implements PublisherInterface {
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
		impactExplosive = false;
		animate = true;
		priority = 1;
	}

	public DiamondModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority,
				impactExplosive, animate);
	}
}