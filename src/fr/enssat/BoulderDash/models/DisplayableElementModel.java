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
	private boolean falling;

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
	public DisplayableElementModel(boolean destructible, boolean moving, String spriteName, int priority, boolean impactExplosive, boolean animate, boolean falling) {
		this.moving = moving;
		this.destructible = destructible;
		this.spriteName = spriteName;
		this.priority = priority;
		this.animate = animate;
		this.impactExplosive = impactExplosive;
		this.priority = priority;
		this.falling = falling;
	}

	/**
	 * Gets the 'destructible' value
     *
	 * @return  Whether object is destructible or not
	 */
	public boolean isDestructible() {
		return this.destructible;
	}

	/**
	 * Gets the 'moving' value
     *
     * @return  Whether object is moving or not
	 */
	public boolean isMoving() {
		return this.moving;
	}

	/**
	 * Gets the sprite name value
     *
     * @return  Sprite name value
	 */
	public String getSpriteName() {
		return this.spriteName;
	}

	/**
	 * Gets the folder path of the sprite storage
     *
     * @return  Folder path of the sprite storage
	 */
	private static String getSpriteStorageFolderPath() {
		return spriteStorageFolderPath;
	}

	/**
	 * Gets the path to the sprite file in storage
     *
     * @return  Path to the sprite file in storage
	 */
	public String getPathToSprite() {
		return getSpriteStorageFolderPath() + getSpriteName() + ".gif";
	}

	/**
	 * Gets the priority of the object
     *
     * @return  Object priority
	 */
	public int getPriority() {
		return this.priority;
	}
	
	/**
     * Sets the priority of the object
     *
     * @param  priority  Object priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Gets the 'animate' value
     *
     * @return  Whether object is animated or not
	 */
	public boolean isAnimate() {
		return this.animate;
	}

	/**
	 * Sets the 'animate' value
     *
     * @return  animate  Whether object is animated or not
	 */
	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	/**
	 * Gets the 'impact explosive' value
     *
     * @return  Whether object explodes on impact or not
	 */
	public boolean isImpactExplosive() {
		return this.impactExplosive;
	}

	/**
	 * Sets the 'impact explosive' value
     *
     * @return  impactExplosive  Whether object explodes on impact or not
	 */
	public void setImpactExplosive(boolean impactExplosive) {
		this.impactExplosive = impactExplosive;
	}
	
	/**
	 * Sets the sprite
     *
     * @param  sprite  Sprite object
	 */
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

    /**
     * Gets the sprite
     *
     * @return  Sprite object
     */
	public BufferedImage getSprite() {
		return sprite;
	}

    /**
     * Loads the target sprite
     *
     * @param   spriteName  Sprite name
     * @return  Sprite object
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
	 * Grabs the sprite from the large image containing all the static sprites items
     *
	 * @param   spriteSheet  Sprite sheet instance
	 * @param   x            Sub image horizontal offset on sprite sheet
	 * @param   y            Sub image vertical offset on sprite sheet
	 * @param   width        Sub image width on sprite sheet
	 * @param   height       Sub image height on sprite sheet
	 * @return  Target sub image
	 */
	public BufferedImage grabSprite(BufferedImage spriteSheet, int x, int y, int width, int height) {
		BufferedImage subImage = spriteSheet.getSubimage(x, y, width, height);

		this.sprite = subImage;
		return subImage;
	}
	
    /**
     * Gets the falling state of the object
     *
     * @return  Whether object is falling or not
     */
	public boolean isFalling() {
		return this.falling;
	}

    /**
     * Sets the falling state of the object
     *
     * @param  falling  Whether object is falling or not
     */
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	/**
	 * Function to update the sprites
	 * @param currentTimeMillis
	 */
	public void update(long currentTimeMillis) {}
}
