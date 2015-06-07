package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;

public class RockfordModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private static ArrayList<BufferedImage> framesBlinking;
	private static ArrayList<BufferedImage> framesRunningLeft;
	private static ArrayList<BufferedImage> framesRunningRight;
	private static ArrayList<BufferedImage> framesRunningUpOrDown;

	private final int SIZ_X_OF_SPRITE = 16;
	private final int SIZ_Y_OF_SPRITE = 16;

	private BufferedImage img;

	private long speed;

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
		priority = 8;
	}

	public RockfordModel(int x, int y) {
		super(isDestructible, canMove, x, y, spriteName, priority, impactExplosive, animate);

		this.initSprites();
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void update(long time) {
		if (time - previousTime >= speed) {
			// update the animation
			previousTime = time;
			try {
				currentFrame += 1;
				if (isStaying())
					setSprite(framesBlinking.get(currentFrame));
				else if (isRunningLeft())
					setSprite(framesRunningLeft.get(currentFrame));
				else if (isRunningRight())
					setSprite(framesRunningRight.get(currentFrame));
				else if (isRunningUpOrDown())
					setSprite(framesRunningUpOrDown.get(currentFrame));
			} catch (IndexOutOfBoundsException e) {
				currentFrame = 0;
			}
		}
	}

	public void startStaying() {
		isStaying = true;
		isRunningLeft = false;
		isRunningRight = false;
		isRunningUpOrDown = false;
		previousTime = 0;
		currentFrame = 0;
	}

	public void startRunningLeft() {
		isStaying = false;
		isRunningLeft = true;
		isRunningRight = false;
		isRunningUpOrDown = false;
		previousTime = 0;
	}

	public void startRunningRight() {
		isStaying = false;
		isRunningLeft = false;
		isRunningRight = true;
		isRunningUpOrDown = false;
		previousTime = 0;
	}

	public void startRunningUpOrDown() {
		isStaying = false;
		isRunningLeft = false;
		isRunningRight = false;
		isRunningUpOrDown = true;
		previousTime = 0;
	}

	public boolean isStaying() {
		return isStaying;
	}

	public boolean isRunningLeft() {
		return isRunningLeft;
	}

	public boolean isRunningRight() {
		return isRunningRight;
	}

	public boolean isRunningUpOrDown() {
		return isRunningUpOrDown;
	}

	/*
	 * initialise all the sprite from the main image; takes the subimage and put
	 * them into an array
	 */
	private void initSprites() {
		framesBlinking = new ArrayList<BufferedImage>();
		framesRunningLeft = new ArrayList<BufferedImage>();
		framesRunningRight = new ArrayList<BufferedImage>();
		framesRunningUpOrDown = new ArrayList<BufferedImage>();
		/* INIT SPRITE ARRAYS FOR ROCKFORD */
		for (int i = 0; i < 8; i++) {
			framesBlinking.add(grabSprite(loadSprite(spriteName), 7 + (24 * i), 79, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE));
			framesRunningLeft.add(grabSprite(loadSprite(spriteName), 7 + (24 * i), 103, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE));
			framesRunningRight.add(grabSprite(loadSprite(spriteName), 7 + (24 * i), 127, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE));
		}
		framesRunningUpOrDown.add(grabSprite(loadSprite(spriteName), 7, 7, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE));
		this.setSpeed(100);
	}
}
