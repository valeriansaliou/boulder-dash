package fr.enssat.BoulderDash.controllers;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.helpers.AudioLoadHelper;
import fr.enssat.BoulderDash.controllers.LevelEditorController;
import fr.enssat.BoulderDash.views.FirstView;
import fr.enssat.BoulderDash.views.GameGroundView;
import fr.enssat.BoulderDash.views.GameView;

import java.awt.Window;
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
	private NavigationBetweenViewController navigationBetweenViewController;
	private GameView gameView;
	
    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     * @param navigationBetweenViewController 
     */
	public GameController(LevelModel levelModel, AudioLoadHelper audioLoadHelper, NavigationBetweenViewController navigationBetweenViewController) {
		this.levelModel = levelModel;
        this.audioLoadHelper = audioLoadHelper;
        this.firstClickOnPause = true;
        this.navigationBetweenViewController = navigationBetweenViewController;
        this.gameView = new GameView(this, levelModel); 
        this.firstView = navigationBetweenViewController.getFirstView();
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
            case "pause":
            	if(this.firstClickOnPause){
            		this.levelModel.setGamePaused(true);
            	} else if(!this.firstClickOnPause){
            		this.levelModel.setGamePaused(false);
            	}
            	this.firstClickOnPause = !this.firstClickOnPause;
                break;

            case "restart":
                this.resetGame();                
                break;
            
            case "menu":
            	this.firstView.setVisible(true);
            	this.gameView.setVisible(false);
            	//this.levelModel.resetGame(); //TODO
                break;
                
            case "load":
            	//TODO            
                break;
        }
        gameView.getGameFieldView().grabFocus();
	}

	/**
	 * Function to reset the game
	 */
    private void resetGame() {
    	levelModel = new LevelModel("level01", audioLoadHelper);
		this.resetGameView(); 
	}
    
	public void resetGameView() {
		this.gameView.removeAll();
		gameView = new GameView(this, levelModel);
	}

	/**
     * Gets the audio load helper instance
     *
     * @return  Audio load helper instance
     */
    public AudioLoadHelper getAudioLoadHelper() {
        return this.audioLoadHelper;
    }

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}
    
    
}