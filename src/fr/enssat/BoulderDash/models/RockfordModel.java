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
	private BufferedImage img;

	private long speed;

	private volatile boolean isStaying = false;

	private long previousTime;
	private int frameAtPause, currentFrame;

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
		this.setSpeed(200);
		framesBlinking.add(grabSprite(loadSprite(spriteName), 7, 7, 16, 16));
		framesBlinking.add(grabSprite(loadSprite(spriteName), 7, 31, 16, 16));
		framesBlinking.add(grabSprite(loadSprite(spriteName), 7, 55, 16, 16));
		framesBlinking.add(grabSprite(loadSprite(spriteName), 7, 79, 16, 16));
	}
	
	public BufferedImage getImg(){
		return img;		
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void update(long time) {
		if (isStaying) {
			if (time - previousTime >= speed) {
				System.out.println("HERE");
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

	public void start() {
		isStaying = true;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}

	public void stop() {
		isStaying = false;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}

	public void pause() {
		frameAtPause = currentFrame;
		isStaying = false;
	}

	public void resume() {
		currentFrame = frameAtPause;
		isStaying = true;
	}

}
