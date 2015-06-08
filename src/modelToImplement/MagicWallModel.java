package modelToImplement;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;

public class MagicWallModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;

	private ArrayList<BufferedImage> framesMagicWall;

	static {
		spriteName = "field_00";
		isDestructible = false;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 10;
	}

	public MagicWallModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority, impactExplosive, animate);
		this.framesMagicWall = new ArrayList<BufferedImage>();
	}

	private void initSprites() {
		/* INIT SPRITE FOR DIAMOND */
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 0, 0, 16, 16));
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 24, 0, 16, 16));
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 48, 0, 16, 16));
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 72, 0, 16, 16));
	}
}
