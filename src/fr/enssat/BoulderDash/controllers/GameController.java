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
	public GameController(LevelModel levelModel, AudioLoadHelper audioLoadHelper) {
		this.levelModel = levelModel;
        this.audioLoadHelper = audioLoadHelper;

        new FrameToDisplay(this, levelModel);

        this.getAudioLoadHelper().playSound("new");
//        this.getAudioLoadHelper().startMusic("game");
	}

	/**
	 * Handles the 'action performed' event
     *
	 * @param  event  Action event
	 */
	public void actionPerformed(ActionEvent event) {
        switch(event.getActionCommand()) {
            case "quit":
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

    /**
     * Gets the audio load helper instance
     *
     * @return  Audio load helper instance
     */
    public AudioLoadHelper getAudioLoadHelper() {
        return this.audioLoadHelper;
    }

//	public void addNotify() { //TODO is this useful ?
//		frameToDisplay.getGameView().addNotify();
//
//		animator = new Thread(this);
//		animator.start();
//	}
}