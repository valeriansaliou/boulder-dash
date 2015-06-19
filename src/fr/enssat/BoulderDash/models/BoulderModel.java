package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;


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
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate, false);
        this.loadSprite(spriteName);
	}
}