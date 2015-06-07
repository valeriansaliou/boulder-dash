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
		this.framesDiamond = new ArrayList<BufferedImage>();
		this.initSprites();
	}

	private void initSprites() {
		/* INIT SPRITE FOR DIAMOND */
		framesDiamond.add(grabSprite(loadSprite(spriteName), 0, 0, 16, 16));
		framesDiamond.add(grabSprite(loadSprite(spriteName), 24, 0, 16, 16));
		framesDiamond.add(grabSprite(loadSprite(spriteName), 48, 0, 16, 16));
		framesDiamond.add(grabSprite(loadSprite(spriteName), 72, 0, 16, 16));
		framesDiamond.add(grabSprite(loadSprite(spriteName), 96, 0, 16, 16));
		framesDiamond.add(grabSprite(loadSprite(spriteName), 120, 0, 16, 16));
		framesDiamond.add(grabSprite(loadSprite(spriteName), 144, 0, 16, 16));
		framesDiamond.add(grabSprite(loadSprite(spriteName), 168, 0, 16, 16));
	}
}