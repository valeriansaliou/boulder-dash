package fr.enssat.BoulderDash.controllers;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.helpers.AudioLoadHelper;
import fr.enssat.BoulderDash.controllers.NavigationBetweenViewController;
import fr.enssat.BoulderDash.views.FirstView;
import fr.enssat.BoulderDash.views.GameView;

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
    private boolean firstClickOnPause;
	private FirstView firstView;
	private GameView gameView;
	
    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     * @param navigationBetweenViewController 
     */
	public GameController(LevelModel levelModel, AudioLoadHelper audioLoadHelper, NavigationBetweenViewController navigationBetweenViewController) {
        this.firstClickOnPause = true;
        
		this.levelModel = levelModel;
        this.audioLoadHelper = audioLoadHelper;
        this.gameView = new GameView(this, levelModel); 
        this.firstView = navigationBetweenViewController.getFirstView();
        this.getAudioLoadHelper().playSound("new");
		this.getAudioLoadHelper().startMusic("game");
	}

	/**
	 * Handles the 'action performed' event
     *
	 * @param  event  Action event
	 */
	public void actionPerformed(ActionEvent event) {
        switch(event.getActionCommand()) {
            case "pause":
            	if(this.firstClickOnPause) {
            		this.levelModel.setGamePaused(true);
            	} else if(!this.firstClickOnPause) {
            		this.levelModel.setGamePaused(false);
            	}

            	this.firstClickOnPause = !this.firstClickOnPause;
                break;

            case "restart":
                this.resetGame("restart");                
                break;
            
            case "menu":
            	this.firstView.setVisible(true);
            	this.resetGame("menu");
                break;
                
            case "load":
            	//TODO            
                break;
        }
        this.gameView.getGameFieldView().grabFocus();
	}

	/**
	 * Function to reset the game
	 */
    private void resetGame(String source) {
    	this.levelModel = new LevelModel("level01", audioLoadHelper);
		this.gameView.dispose();
		this.gameView = new GameView(this, levelModel);
		
		if(source.equals("restart")){
			this.gameView.setVisible(true);
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

    /**
     * Return the game view
     * @return gameView
     */
	public GameView getGameView() {
		return gameView;
	}

	/**
	 * Set the gameView
	 * @param gameView
	 */
	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}
}