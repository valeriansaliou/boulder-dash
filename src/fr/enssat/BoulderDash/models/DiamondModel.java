package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.interfaces.PublisherInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * DiamondModel
 *
 * Represents a diamond in the game.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class DiamondModel extends DisplayableElementModel implements PublisherInterface {
	private static String spriteName;
	private static boolean isDestructible;
	private static boolean canMove;
	private static boolean impactExplosive;
	private static boolean animate;
	private static int priority;
	private static String collideSound;
	private long previousTime;
	private int currentFrame;

	private final int SIZ_X_OF_SPRITE = 16;
	private final int SIZ_Y_OF_SPRITE = 16;
	private long speed;

	private ArrayList<BufferedImage> framesDiamond;

    /**
     * Static dataset
     * Specifies the physical parameters of the object
     */
	static {
		spriteName = "diamond";
		isDestructible = true;
		canMove = true;
		impactExplosive = false;
		animate = true;
		priority = 0;
		collideSound = "coin";
	}

    /**
     * Class constructor
     */
	public DiamondModel() {
<<<<<<< HEAD
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate, false);
=======
		super(isDestructible, canMove, spriteName, priority, impactExplosive, animate, false, collideSound);
>>>>>>> 58ed28a217960a0c8c3c2f7094eb7e42d4f08e1a

		this.initSprites();
	}

    /**
     * Updates the sprite (animation loop)
     *
     * @param  time  Current time
     */
	public void update(long time) {
		if (time - previousTime >= speed) {
			// Update the animation
			previousTime = time;

            try {
				this.currentFrame += 1;
                this.setSprite(framesDiamond.get(this.currentFrame));
			} catch (IndexOutOfBoundsException e) {
				this.currentFrame = 0;
			}
		}
	}

    /**
     * Initialize the sprites
     * This is an animated element, hence this method
     */
	private void initSprites() {
		/* Initialize object sprites */
		this.framesDiamond = new ArrayList<BufferedImage>();

		for (int i = 0; i < 8; i++) {
            this.framesDiamond.add(
                    this.grabSprite(loadSprite(spriteName), i * 24, 0, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE)
            );
		}
	}
}