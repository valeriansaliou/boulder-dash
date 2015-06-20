package fr.enssat.BoulderDash.controllers;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.FrameToDisplay;
import fr.enssat.BoulderDash.helpers.AudioLoadHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * GameController
 *
 * This system creates the view.
 * The game loop is also handled there.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class GameController implements ActionListener {
	private LevelModel levelModel;
    private AudioLoadHelper audioLoadHelper;

    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
	public GameController(LevelModel levelModel) {
		this.levelModel = levelModel;
        new FrameToDisplay(this, levelModel);

        this.audioLoadHelper = new AudioLoadHelper();
        this.audioLoadHelper.playSound("new");
//        this.audioLoadHelper.startMusic("game");
	}

	/**
	 * Handles the 'action performed' event
     *
	 * @param  event  Action event
	 */
	public void actionPerformed(ActionEvent event) {
        switch(event.getActionCommand()) {
            case "Quit":
                System.exit(0);
                break;

            case "editor":
                // TODO
                break;

            case "pause":
                // TODO
                break;

            case "save":
                // TODO
                break;

            case "restart":
                this.levelModel.resetGame();
                break;
        }
	}

//	public void addNotify() { //TODO is this useful ?
//		frameToDisplay.getGameView().addNotify();
//
//		animator = new Thread(this);
//		animator.start();
//	}
}