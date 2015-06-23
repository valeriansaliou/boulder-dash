package fr.enssat.BoulderDash.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enssat.BoulderDash.helpers.AudioLoadHelper;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.FirstView;

/**
 * Controller to navigate between the different views
 * @author colinleverger
 *
 */
public class NavigationBetweenViewController implements ActionListener {

	private LevelEditorController levelEditor;
	private FirstView firstView;
    private AudioLoadHelper audioLoadHelper;
	private LevelModel levelModel;
	private GameController gameController;
    
    public NavigationBetweenViewController(){
    	this.audioLoadHelper = new AudioLoadHelper();
    	this.levelModel = new LevelModel("level01", audioLoadHelper);
    	
    	// Creation of the first view
    	this.firstView = new FirstView(this);
    	// Creation of the controllers which will control the others views
    	this.levelEditor = new LevelEditorController(this.levelModel,this);
    	this.gameController = new GameController(levelModel, audioLoadHelper,this);        
    }

	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()) {
        case "quit":
            System.exit(0);
            break;

        case "editor":
        	this.firstView.setVisible(false);
        	
        	this.levelEditor.getLevelEditorView().setVisible(true);
        	this.gameController.getGameView().setVisible(false);
            break;
            

        case "game":
        	this.firstView.setVisible(false);
        	
        	this.levelEditor.getLevelEditorView().setVisible(false);
        	this.gameController.getGameView().setVisible(true);
            break;
		}
		this.gameController.getGameView().getGameFieldView().grabFocus();
	}

	/**
	 * Get the first view
	 * @return firstView
	 */
	public FirstView getFirstView() {
		return firstView;
	}

	/**
	 * Set the first view
	 * @param firstView
	 */
	public void setFirstView(FirstView firstView) {
		this.firstView = firstView;
	}    
	
	
}
