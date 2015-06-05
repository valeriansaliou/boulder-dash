package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class SteelWallModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private BufferedImage img;

	static {
		spriteName = "steelwall";
		isDestructible = false;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 8;
	}

	public SteelWallModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority,
				impactExplosive, animate);
		loadSprite(spriteName);
	}
}