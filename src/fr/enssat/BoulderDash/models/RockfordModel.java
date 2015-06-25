package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * RockfordModel
 *
 * Represents the hero of the game.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class RockfordModel extends DisplayableElementModel {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private static boolean falling;
	private static String collideSound;

    /**
     * Maps the sub images of the sprite file
     */
	private static ArrayList<BufferedImage> framesBlinking;
	private static ArrayList<BufferedImage> framesRunningLeft;
	private static ArrayList<BufferedImage> framesRunningRight;
	private static ArrayList<BufferedImage> framesRunningUpOrDown;

    /**
     * Defines the size of the sprite
     */
	private final int SIZ_X_OF_SPRITE = 16;
	private final int SIZ_Y_OF_SPRITE = 16;

    /**
     * Defines the current speed of the object
     */
	private long speed;

    /**
     * Maps possible states for Rockford
     */
	private boolean isCollisionDone = false;
	private boolean isStaying = true;
	private boolean isRunningLeft = false;
	private boolean isRunningRight = false;
	private boolean isRunningUp = false;
	private boolean isRunningDown = false;

	private long previousTime;
	private int currentFrame;
	private boolean hasExploded;

    /**
     * Static dataset
     * Specifies the physical parameters of the object
     */
	static {
		spriteName = "rockford";
		isDestructible = true;
		canMove = true;
		impactExplosive = true;
		animate = true;
		priority = 1;
		falling = false;
		collideSound = null;
	}

    /**
     * Class constructor
     */
	public RockfordModel() {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate, falling, collideSound);
		// Speed of the animation of the sprite
		this.setSpeed(100);
		// Init the sprites in arrays
		this.initSprites();
		this.hasExploded = false;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Updates the sprite animation
     * (And only that single thing)
	 */
	public void update(long time) {
		if (time - this.previousTime >= this.speed) {
			// Update the animation
			this.previousTime = time;
			try {
				currentFrame += 1;

				if (isStaying()) {
					this.setSprite(framesBlinking.get(currentFrame));
				} else if (isRunningLeft()) {
					this.setSprite(framesRunningLeft.get(currentFrame));
				} else if (isRunningRight()) {
					this.setSprite(framesRunningRight.get(currentFrame));
				} else if (isRunningUpOrDown()) {
					this.setSprite(framesRunningUpOrDown.get(currentFrame));
				}
			} catch (IndexOutOfBoundsException e) {
				this.currentFrame = 0;
			}
		}
	}

	/**
	 * Stops the Rockford movement
	 */
	public void startStaying() {
		isCollisionDone = false;
		isStaying = true;
		isRunningLeft = false;
		isRunningRight = false;
		isRunningUp = false;
		isRunningDown = false;
		previousTime = 0;
		currentFrame = 0;
	}

	/**
	 * Starts moving Rockford to the left
	 */
	public void startRunningLeft() {
		isCollisionDone = false;
		isStaying = false;
		isRunningLeft = true;
		isRunningRight = false;
		isRunningUp = false;
		isRunningDown = false;
		previousTime = 0;
	}

	/**
	 * Starts moving Rockford to the right
	 */
	public void startRunningRight() {
		isCollisionDone = false;
		isStaying = false;
		isRunningLeft = false;
		isRunningRight = true;
		isRunningUp = false;
		isRunningDown = false;
		previousTime = 0;
	}

	/**
	 * Rockford running up
	 */
	public void startRunningUp() {
		isCollisionDone = false;
		isStaying = false;
		isRunningLeft = false;
		isRunningRight = false;
		isRunningUp = true;
		isRunningDown = false;
		previousTime = 0;
	}

	/**
	 * Rockford running down
	 */
	public void startRunningDown() {
		isCollisionDone = false;
		isStaying = false;
		isRunningLeft = false;
		isRunningRight = false;
		isRunningUp = false;
		isRunningDown = true;
		previousTime = 0;
	}

    /**
     * Gets whether Rockford collision has been handled or not
     *
     * @return  Rockford collision handled or not
     */
    public boolean isCollisionDone() {
        return this.isCollisionDone;
    }

    /**
     * Sets whether Rockford collision has been handled or not
     *
     * @param  isCollisionDone  Rockford collision handled or not
     */
    public void setCollisionDone(boolean isCollisionDone) {
        this.isCollisionDone = isCollisionDone;
    }

	/**
	 * Gets whether Rockford is standing still or not
	 *
	 * @return  Rockford staying or not
	 */
	public boolean isStaying() {
		return this.isStaying;
	}

    /**
     * Gets whether Rockford is running to the left or not
     *
     * @return  Rockford running to the left or not
     */
	public boolean isRunningLeft() {
		return this.isRunningLeft;
	}

    /**
     * Gets whether Rockford is running to the right or not
     *
     * @return  Rockford running to the right or not
     */
	public boolean isRunningRight() {
		return this.isRunningRight;
	}

	/**
	 * Gets whether Rockford is running up or not
	 *
	 * @return  Rockford running up, or not
	 */
	public boolean isRunningUp() {
		return this.isRunningUp;
	}

	/**
	 * Gets whether Rockford is running down or not
	 *
	 * @return  Rockford running down, or not
	 */
	public boolean isRunningDown() {
		return this.isRunningDown;
	}

	/**
	 * Gets whether Rockford is running up or down, or not
	 *
	 * @return  Rockford running up or down, or not
	 */
	public boolean isRunningUpOrDown() {
		return this.isRunningUp() || this.isRunningDown();
	}

	/**
	 * Initializes all sprites from the main image
     * Takes the sub images and append them into storage arrays
	 */
	private void initSprites() {
		framesBlinking        = new ArrayList<BufferedImage>();
		framesRunningLeft     = new ArrayList<BufferedImage>();
		framesRunningRight    = new ArrayList<BufferedImage>();
		framesRunningUpOrDown = new ArrayList<BufferedImage>();

		/* INIT SPRITE ARRAYS FOR ROCKFORD */
		for (int i = 0; i < 8; i++) {
			framesBlinking.add(
					this.grabSprite(this.loadSprite(spriteName), 7 + (24 * i), 79, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE)
			);

			framesRunningLeft.add(
					this.grabSprite(this.loadSprite(spriteName), 7 + (24 * i), 103, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE)
			);

			framesRunningRight.add(
					this.grabSprite(this.loadSprite(spriteName), 7 + (24 * i), 127, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE)
			);
		}

		framesRunningUpOrDown.add(
				this.grabSprite(this.loadSprite(spriteName), 7, 7, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE)
		);
	}

	/**
	 * Return true if rockford has exploded (you = lose)
     *
	 * @return  Whether Rockford has exploded or not
	 */
	public boolean getHasExplosed() {
		return hasExploded;
	}
	
	/**
	 * Set rockford exploded state
     *
	 * @param  hasExploded  Whether Rockford has exploded or not
	 */
	public void setHasExplosed(boolean hasExploded){
		this.hasExploded = hasExploded;
	}
}
