package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * MagicWallModel
 *
 * Represents the magic wall.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class MagicWallModel extends DisplayableElementModel {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private static boolean falling;
	private static String collideSound;

    /**
     * Stores the frames
     * Used for the sprites
     */
	private ArrayList<BufferedImage> framesMagicWall;

	private long previousTime;
	private int currentFrame;
	private long speed;

    /**
     * Static dataset
     * Specifies the physical parameters of the object
     */
	static {
		spriteName = "magicwall";
		isDestructible = false;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 3;
		falling = false;
		collideSound = "touch";
	}

    /**
     * Class constructor
     */
	public MagicWallModel() {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate, falling, collideSound);
		this.currentFrame = 0;
		this.speed =  100;
		this.initSprites();
	}

	/**
	 * Function to animate the sprite
	 */
	public void update(long time) {
		if (time - previousTime >= speed) {
			// Update animation
			previousTime = time;

			try {
				currentFrame += 1;

				this.setSprite(framesMagicWall.get(this.currentFrame));
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
