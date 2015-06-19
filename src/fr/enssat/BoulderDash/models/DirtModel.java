package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;


/**
 * DirtModel
 *
 * Represents the dirt in the game.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class DirtModel extends DisplayableElementModel implements PublisherInterface {
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
		spriteName = "dirt";
		isDestructible = true;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 0;
	}

    /**
     * Class constructor
     */
	public DirtModel() {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate);

        this.loadSprite(spriteName);
	}
}