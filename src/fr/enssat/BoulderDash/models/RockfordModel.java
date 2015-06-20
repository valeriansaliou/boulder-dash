package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * RockfordModel
 *
 * Represents the hero of the game.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class RockfordModel extends DisplayableElementModel implements PublisherInterface {
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
	private boolean isStaying = true;
	private boolean isRunningLeft = false;
	private boolean isRunningRight = false;
	private boolean isRunningUpOrDown = false;

	private long previousTime;
	private int currentFrame;

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
			// update the animation
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
		isStaying = true;
		isRunningLeft = false;
		isRunningRight = false;
		isRunningUpOrDown = false;
		previousTime = 0;
		currentFrame = 0;
	}

	/**
	 * Starts moving Rockford to the left
	 */
	public void startRunningLeft() {
		isStaying = false;
		isRunningLeft = true;
		isRunningRight = false;
		isRunningUpOrDown = false;
		previousTime = 0;
	}

	/**
	 * Starts moving Rockford to the right
	 */
	public void startRunningRight() {
		isStaying = false;
		isRunningLeft = false;
		isRunningRight = true;
		isRunningUpOrDown = false;
		previousTime = 0;
	}

	/**
	 * Rockford running up or down (same picture)
	 */
	public void startRunningUpOrDown() {
		isStaying = false;
		isRunningLeft = false;
		isRunningRight = false;
		isRunningUpOrDown = true;
		previousTime = 0;
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
     * Gets whether Rockford is running up or down, or not
     *
     * @return  Rockford running up or down, or not
     */
	public boolean isRunningUpOrDown() {
		return this.isRunningUpOrDown;
	}

	/**
	 * Initializes all sprites from the main image
     * Takes the sub images and append them into storage arrays
	 */
	private void initSprites() {
		framesBlinking = new ArrayList<BufferedImage>();
		framesRunningLeft = new ArrayList<BufferedImage>();
		framesRunningRight = new ArrayList<BufferedImage>();
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
}
