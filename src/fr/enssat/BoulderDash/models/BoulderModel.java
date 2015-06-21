package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * BoulderModel
 *
 * Represents the boulders.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class BoulderModel extends DisplayableElementModel {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private static String collideSound;

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
		collideSound = "die";
	}

    /**
     * Class constructor
     */
	public BoulderModel(boolean convertible) {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate, false, collideSound, convertible);
        this.loadSprite(spriteName);
	}

    public BoulderModel() {
        this(false);
    }
}