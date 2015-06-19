package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * EmptyModel
 *
 * Represents "nothing".
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class EmptyModel extends DisplayableElementModel {
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
		spriteName = "black";
		isDestructible = false;
		canMove = false;
		impactExplosive = false;
		animate = false;
		priority = 0;
	}

    /**
     * Class constructor
     */
	public EmptyModel() {
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate);

		this.loadSprite(spriteName);
	}
}
