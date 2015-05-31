package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class RockfordModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private BufferedImage img;

	static {
		spriteName = "rockford";
		isDestructible = true;
		canMove = true;
		impactExplosive = true;
		animate = true;
		priority = 8;
	}

	public RockfordModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority,
				impactExplosive, animate);
		try {
			img = ImageIO.read(new File(getPathToSprite()));
		} catch (IOException exc) {
			System.out.println(getPathToSprite());
			exc.printStackTrace();
		}
		setImg(img);
	}
}
