package modelToImplement;

import fr.enssat.BoulderDash.models.DisplayableElementModel;

public class AmoebaModel extends DisplayableElementModel {
	private static String spriteName;
	private static Boolean isDestructible;
	private static boolean impactExplosive;
	private static boolean isMoving;
	private static boolean animate;
	private static int priority;
	private static boolean falling;
	private static String collideSound;

	static {
		spriteName = "field_00";
		isDestructible = false;
		isMoving = true;
		impactExplosive = true;
		animate = true;
		priority = 2;
		falling = false;
		collideSound = null;
	}

	public AmoebaModel() {
		super(animate, animate, spriteName, priority, animate, animate, falling, collideSound);//TODO IT IS NOT IN THE RIGHT ORDER
	}
}