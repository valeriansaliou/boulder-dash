package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * CursorModel
 *
 * Represents the field cursor pointer.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-22
 */
public class CursorModel extends DisplayableElementModel {
    private static String spriteName;
    private static boolean isDestructible;
    private static boolean canMove;
    private static boolean impactExplosive;
    private static boolean animate;
    private static int priority;
    private static boolean falling;
    private static String collideSound;

    /**
     * Static dataset
     * Specifies the physical parameters of the object
     */
    static {
        spriteName = "cursor";
        isDestructible = false;
        canMove = false;
        impactExplosive = false;
        animate = false;
        priority = 0;
        falling = false;
        collideSound = null;
    }

    /**
     * Class constructor
     */
    public CursorModel() {
        super(isDestructible, canMove, spriteName, priority, impactExplosive, animate, falling, collideSound);

        this.loadSprite(spriteName);
    }
}