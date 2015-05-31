package fr.enssat.BoulderDash.models;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class DisplayableElementModel {
	private static String spriteStoragePath;

	private boolean isDestructible;
	private boolean isMoving;
	private boolean animate;
	private boolean impactExplosive;
	private String spriteName;
	private int x;
	private int y;
	private int priority;
	private BufferedImage img;
	
	public DisplayableElementModel(boolean isDestructible, boolean isMoving,
			int x, int y, String spriteName, int priority,
			boolean impactExplosive, boolean animate) {
		this.isMoving = isMoving;
		this.isDestructible = isDestructible;
		this.spriteName = spriteName;
		spriteStoragePath = "./res/drawable/field/";
		this.x = x;
		this.y = y;
		this.animate = animate;
		this.impactExplosive = impactExplosive;
		this.setPriority(priority);
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
		return getSpriteStoragePath() + this.getSpriteName() + ".png";
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

	public Image getImg() {
		return img;
	}
	
	public void setImg(BufferedImage img){
		this.img = img;
	}
}
