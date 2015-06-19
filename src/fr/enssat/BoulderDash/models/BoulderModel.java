package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * BoulderModel
 *
 * Represents the boulders.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class BoulderModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private boolean falling;

    /**
     * Static dataset
     * Specifies the physical parameters of the object
     */
	static {
		spriteName = "boulder";
		isDestructible = false;
		canMove = true;
		impactExplosive = false;
		animate = true;
		priority = 2;
	}

    /**
     * Class constructor
     */
	public BoulderModel() {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate);

        this.falling = false;

        this.loadSprite(spriteName);
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
}