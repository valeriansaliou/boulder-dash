package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class DirtModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private BufferedImage img;

	static {
		spriteName = "dirt";
		isDestructible = true;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 3;
	}

	public DirtModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority, impactExplosive, animate);
		try {
			img = ImageIO.read(new File(getPathToSprite()));
		} catch (IOException exc) {
			System.out.println(getPathToSprite());
			exc.printStackTrace();
		}
		setImg(img);
	}

	public String getSpriteName() {
		return spriteName;
	}

}