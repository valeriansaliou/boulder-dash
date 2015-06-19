package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;

/**
 * RockfordModel represents the hero of the game.
 * @author colinleverger
 *
 */
public class RockfordModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	
	// ArrayList of BufferedImage to get all the subimages from the sprite file...
	private static ArrayList<BufferedImage> framesBlinking;
	private static ArrayList<BufferedImage> framesRunningLeft;
	private static ArrayList<BufferedImage> framesRunningRight;
	private static ArrayList<BufferedImage> framesRunningUpOrDown;

	private final int SIZ_X_OF_SPRITE = 16;
	private final int SIZ_Y_OF_SPRITE = 16;

	private long speed;

	// Differents states possible for Rockford
	private boolean isStaying = true;
	private boolean isRunningLeft = false;
	private boolean isRunningRight = false;
	private boolean isRunningUpOrDown = false;

	private long previousTime;
	private int currentFrame;

	static {
		spriteName = "rockford";
		isDestructible = true;
		canMove = true;
		impactExplosive = true;
		animate = true;
		priority = 1;
	}

	public RockfordModel() {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate);
		// Speed of the animation of the sprite
		this.setSpeed(100);
		// Init the sprites in arrays
		this.initSprites();
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Update the sprite animation (only that)
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
					System.out.println("update left");
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
	 * Rockford stayin
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
	 * Rockford running left
	 */
	public void startRunningLeft() {
		isStaying = false;
		isRunningLeft = true;
		isRunningRight = false;
		isRunningUpOrDown = false;
		previousTime = 0;
	}

	/**
	 * Rockford running right
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

	public boolean isStaying() {
		return this.isStaying;
	}

	public boolean isRunningLeft() {
		return this.isRunningLeft;
	}

	public boolean isRunningRight() {
		return this.isRunningRight;
	}

	public boolean isRunningUpOrDown() {
		return this.isRunningUpOrDown;
	}

	/*
	 * Initialise all the sprite from the main image; takes the subimage and put
	 * them into the arrays
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
