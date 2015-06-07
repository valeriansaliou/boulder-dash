package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DisplayableElementModel {
	private static String spriteStoragePath = "./res/drawable/field/";

	private boolean isDestructible;
	private boolean isMoving;
	private boolean animate;
	private boolean impactExplosive;
	private String spriteName;
	private int x;
	private int y;
	private int priority;
	private BufferedImage sprite;

	public DisplayableElementModel(boolean isDestructible, boolean isMoving, int x, int y, String spriteName, int priority, boolean impactExplosive, boolean animate) {
		this.isMoving = isMoving;
		this.isDestructible = isDestructible;
		this.spriteName = spriteName;
		this.priority = priority;
		this.x = x;
		this.y = y;
		this.animate = animate;
		this.impactExplosive = impactExplosive;
		this.priority = priority;
	}

	public boolean isDestructible() {
		return isDestructible;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public String getSpriteName() {
		return spriteName;
	}

	public static String getSpriteStoragePath() {
		return spriteStoragePath;
	}

	public String getPathToSprite() {
		return getSpriteStoragePath() + getSpriteName() + ".gif";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isAnimate() {
		return animate;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	public boolean isImpactExplosive() {
		return impactExplosive;
	}

	public void setImpactExplosive(boolean impactExplosive) {
		this.impactExplosive = impactExplosive;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public BufferedImage loadSprite(String spriteName) {

		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(new File("res/drawable/field/" + spriteName + ".gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.sprite = sprite;
		return sprite;
	}

	public BufferedImage grabSprite(BufferedImage spriteSheet, int x, int y, int width, int height) {
		BufferedImage subImages = spriteSheet.getSubimage(x, y, width, height);

		this.sprite = subImages;
		return subImages;
	}
}
