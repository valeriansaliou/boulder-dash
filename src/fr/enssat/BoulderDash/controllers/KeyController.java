package fr.enssat.BoulderDash.controllers;

import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.LevelModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * KeyController
 *
 * Manages the key events controller.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class KeyController implements KeyListener {
	private LevelModel levelModel;
	private RockfordUpdateController updatePosRockford;
	private BoulderAndDiamondController updateFallingElements;

    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
	public KeyController(LevelModel levelModel) {
		this.levelModel = levelModel;
		this.updateFallingElements = new BoulderAndDiamondController(levelModel);
		this.updatePosRockford = new RockfordUpdateController(levelModel);
	}

    /**
     * Handles the 'key pressed' event
     *
     * @param  e  Key event
     */
	public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            // Direction: UP
            case KeyEvent.VK_UP:
                DisplayableElementModel upElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX()][levelModel.getRockfordPositionY() - 1];

                if (upElement.getPriority() < levelModel.getRockford().getPriority()) {
                    this.updatePosRockford.moveRockford(levelModel.getRockfordPositionX(), levelModel.getRockfordPositionY() - 1);
                    this.levelModel.getRockford().startRunningUp();
                }

                break;

            // Direction: DOWN
            case KeyEvent.VK_DOWN:
                DisplayableElementModel downElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX()][levelModel.getRockfordPositionY() + 1];

                if (downElement.getPriority() < levelModel.getRockford().getPriority()) {
                    this.updatePosRockford.moveRockford(levelModel.getRockfordPositionX(), levelModel.getRockfordPositionY() + 1);
                    this.levelModel.getRockford().startRunningDown();
                }

                break;

            // Direction: LEFT
            case KeyEvent.VK_LEFT:
                DisplayableElementModel leftElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX() - 1][levelModel.getRockfordPositionY()];

                if (leftElement.getPriority() < levelModel.getRockford().getPriority()) {
                    this.updatePosRockford.moveRockford(levelModel.getRockfordPositionX() - 1, levelModel.getRockfordPositionY());
                    this.levelModel.getRockford().startRunningLeft();
                }

                break;

            // Direction: RIGHT
            case KeyEvent.VK_RIGHT:
                DisplayableElementModel rightElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX() + 1][levelModel.getRockfordPositionY()];

                if (rightElement.getPriority() < levelModel.getRockford().getPriority()) {
                    this.updatePosRockford.moveRockford(levelModel.getRockfordPositionX() + 1, levelModel.getRockfordPositionY());
                    this.levelModel.getRockford().startRunningRight();
                }

                break;
        }
	}

    /**
     * Handles the 'key released' event
     *
     * @param  e  Key event
     */
	@Override
	public void keyReleased(KeyEvent e) {
		this.levelModel.getRockford().startStaying();
	}

    /**
     * Handles the 'key typed' event
     *
     * @param  e  Key event
     */
	@Override
	public void keyTyped(KeyEvent e) {
        // TODO
	}
}
