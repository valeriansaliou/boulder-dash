package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

/**
 * MagicWallModel, it represents the magic wall.
 * @author colinleverger
 *
 */
public class MagicWallModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;

	// Store the frames for the sprite
	private ArrayList<BufferedImage> framesMagicWall;

	private long previousTime;
	private int currentFrame;
	private long speed;

	static {
		spriteName = "magicwall";
		isDestructible = false;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 3;
	}

	public MagicWallModel() {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate);
		this.currentFrame = 0;
		this.speed =  100;
		this.initSprites();
	}

	/**
	 * Function to annimate the sprite
	 */
	public void update(long time) {
		if (time - previousTime >= speed) {
			// update the animation
			previousTime = time;
			try {
				currentFrame += 1;
				setSprite(framesMagicWall.get(this.currentFrame));
			} catch (IndexOutOfBoundsException e) {
				currentFrame = 0;
			}
		}
	}
	
	/**
	 * Init the subimages
	 */
	private void initSprites() {
		this.framesMagicWall = new ArrayList<BufferedImage>();
		/* INIT SPRITE FOR DIAMOND */
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 0, 0, 16, 16));
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 24, 0, 16, 16));
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 48, 0, 16, 16));
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 72, 0, 16, 16));
	}
}
