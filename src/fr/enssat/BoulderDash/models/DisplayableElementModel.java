package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * DisplayableElementModel
 *
 * Represents a abstract displayable element
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public abstract class DisplayableElementModel {
	private static String spriteStorageFolderPath = "./res/drawable/field/";

	private boolean destructible;
	private boolean moving;
	private boolean animate;
	private boolean impactExplosive;
	private String spriteName;
	private int priority;
	private BufferedImage sprite;

	/**
     * Class constructor
     *
	 * @param destructible
	 * @param moving
	 * @param spriteName
	 * @param priority
	 * @param impactExplosive
	 * @param animate
	 */
	public DisplayableElementModel(boolean destructible, boolean moving, String spriteName, int priority, boolean impactExplosive, boolean animate) {
		this.moving = moving;
		this.destructible = destructible;
		this.spriteName = spriteName;
		this.priority = priority;
		this.animate = animate;
		this.impactExplosive = impactExplosive;
		this.priority = priority;
	}

	/**
	 * Getter for destructible
	 * @return destructible
	 */
	public boolean isDestructible() {
		return this.destructible;
	}

	/**
	 * Getter for moving
	 * @return moving
	 */
	public boolean isMoving() {
		return this.moving;
	}

	/**
	 * Getter for spriteName
	 * @return spriteName
	 */
	public String getSpriteName() {
		return this.spriteName;
	}

	/**
	 * Getter for the spriteStorageFolderPath
	 * @return spriteStorageFolderPath
	 */
	private static String getSpriteStorageFolderPath() {
		return spriteStorageFolderPath;
	}

	/**
	 * Getter for pathToSprite
	 * @return
	 */
	public String getPathToSprite() {
		return getSpriteStorageFolderPath() + getSpriteName() + ".gif";
	}

	/**
	 * Getter for priority of the element
	 * @return priority
	 */
	public int getPriority() {
		return this.priority;
	}
	
	/**
	 * Setter for priority
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Getter for animate
	 * @return animate
	 */
	public boolean isAnimate() {
		return this.animate;
	}

	/**
	 * Setter for animate
	 * @param animate
	 */
	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	/**
	 * Getter for impactExplosive
	 * @return impactExplosive
	 */
	public boolean isImpactExplosive() {
		return this.impactExplosive;
	}

	/**
	 * Setter for impactExplosive
	 * @param impactExplosive
	 */
	public void setImpactExplosive(boolean impactExplosive) {
		this.impactExplosive = impactExplosive;
	}
	
	/**
	 * @param sprite
	 */
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	/**
	 * Returns the sprite initialised
	 * @return sprite
	 */
	public BufferedImage getSprite() {
		return sprite;
	}

	/**
	 * Function to load the sprite base image
	 * @param spriteName
	 * @return
	 */
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

	/**
	 * Function to grab the sprite from the big image which contain all the sprites
	 * @param spriteSheet
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage grabSprite(BufferedImage spriteSheet, int x, int y, int width, int height) {
		BufferedImage subImages = spriteSheet.getSubimage(x, y, width, height);

		this.sprite = subImages;
		return subImages;
	}

	/**
	 * Function to update the sprites
	 * @param currentTimeMillis
	 */
	public void update(long currentTimeMillis) {}
}
