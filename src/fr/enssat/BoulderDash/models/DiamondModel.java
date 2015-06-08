package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class DiamondModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private long previousTime;
	private int currentFrame;

	private final int SIZ_X_OF_SPRITE = 16;
	private final int SIZ_Y_OF_SPRITE = 16;
	private long speed;

	private ArrayList<BufferedImage> framesDiamond;

	static {
		spriteName = "diamond";
		isDestructible = true;
		canMove = true;
		impactExplosive = false;
		animate = true;
		priority = 1;
	}

	public DiamondModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority, impactExplosive, animate);
		this.initSprites();
	}

	public void update(long time) {
		if (time - previousTime >= speed) {
			// update the animation
			previousTime = time;
			try {
				currentFrame += 1;
				setSprite(framesDiamond.get(currentFrame));
			} catch (IndexOutOfBoundsException e) {
				currentFrame = 0;
			}
		}
	}
	
	private void initSprites() {
		/* INIT SPRITE FOR DIAMOND */

		this.framesDiamond = new ArrayList<BufferedImage>();

		for (int i = 0; i < 8; i++) {
			framesDiamond.add(grabSprite(loadSprite(spriteName), i * 24, 0, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE));
		}
	}
	
}