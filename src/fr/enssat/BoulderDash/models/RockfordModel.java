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
		framesBlinking = new ArrayList<BufferedImage>();
		framesRunningLeft = new ArrayList<BufferedImage>();
		framesRunningRight = new ArrayList<BufferedImage>();
		framesRunningUpOrDown = new ArrayList<BufferedImage>();
		this.initSprites();
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void update(long time) {
		if (isStaying) {
			if (time - previousTime >= speed) {
				// update the animation
				previousTime = time;
				try {
					currentFrame += 1;
					setSprite(framesBlinking.get(currentFrame));
				} catch (IndexOutOfBoundsException e) {
					currentFrame = 0;
					setSprite(framesBlinking.get(currentFrame));
				}
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
		currentFrame = 0;
	}

	public void startRunningRight() {
		isStaying = false;
		isRunningLeft = false;
		isRunningRight = true;
		isRunningUpOrDown = false;
		previousTime = 0;
		currentFrame = 0;
	}

	public void startRunningUpOrDown() {
		isStaying = false;
		isRunningLeft = false;
		isRunningRight = false;
		isRunningUpOrDown = true;
		previousTime = 0;
		currentFrame = 0;
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

	private void initSprites() {
		/* INIT SPRITE FOR ROCKFORD STANDING */
		framesBlinking.add(grabSprite(loadSprite(spriteName), 7, 79, 16, 16));
		framesBlinking.add(grabSprite(loadSprite(spriteName), 31, 79, 16, 16));
		framesBlinking.add(grabSprite(loadSprite(spriteName), 55, 79, 16, 16));
		framesBlinking.add(grabSprite(loadSprite(spriteName), 79, 79, 16, 16));
		framesBlinking.add(grabSprite(loadSprite(spriteName), 103, 79, 16, 16));
		framesBlinking.add(grabSprite(loadSprite(spriteName), 127, 79, 16, 16));
		framesBlinking.add(grabSprite(loadSprite(spriteName), 151, 79, 16, 16));
		framesBlinking.add(grabSprite(loadSprite(spriteName), 175, 79, 16, 16));
		/* INIT SPRITE FOR ROCKFORD RUNNING LEFT */
		framesRunningLeft.add(grabSprite(loadSprite(spriteName), 7, 103, 16, 16));
		framesRunningLeft.add(grabSprite(loadSprite(spriteName), 31, 103, 16, 16));
		framesRunningLeft.add(grabSprite(loadSprite(spriteName), 55, 103, 16, 16));
		framesRunningLeft.add(grabSprite(loadSprite(spriteName), 79, 103, 16, 16));
		framesRunningLeft.add(grabSprite(loadSprite(spriteName), 103, 103, 16, 16));
		framesRunningLeft.add(grabSprite(loadSprite(spriteName), 127, 103, 16, 16));
		framesRunningLeft.add(grabSprite(loadSprite(spriteName), 151, 103, 16, 16));
		framesRunningLeft.add(grabSprite(loadSprite(spriteName), 175, 103, 16, 16));
		/* INIT SPRITE FOR ROCKFORD RUNNING RIGHT */
		framesRunningRight.add(grabSprite(loadSprite(spriteName), 7, 127, 16, 16));
		framesRunningRight.add(grabSprite(loadSprite(spriteName), 31, 127, 16, 16));
		framesRunningRight.add(grabSprite(loadSprite(spriteName), 55, 127, 16, 16));
		framesRunningRight.add(grabSprite(loadSprite(spriteName), 79, 127, 16, 16));
		framesRunningRight.add(grabSprite(loadSprite(spriteName), 103, 127, 16, 16));
		framesRunningRight.add(grabSprite(loadSprite(spriteName), 127, 127, 16, 16));
		framesRunningRight.add(grabSprite(loadSprite(spriteName), 151, 127, 16, 16));
		framesRunningRight.add(grabSprite(loadSprite(spriteName), 175, 127, 16, 16));
		/* INIT SPRITE FOR ROCKFORD RUNNING RIGHT */
		framesRunningUpOrDown.add(grabSprite(loadSprite(spriteName), 7, 7, 16, 16));
		this.setSpeed(200);
	}
}
