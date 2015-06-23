package fr.enssat.BoulderDash.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enssat.BoulderDash.helpers.AudioLoadHelper;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.FirstView;
import fr.enssat.BoulderDash.views.GameView;

public class NavigationBetweenViewController implements ActionListener {

	private LevelEditorController levelEditor;
	private FirstView firstView;
    private AudioLoadHelper audioLoadHelper;
	private LevelModel levelModel;
	private GameController gameController;
    
    public NavigationBetweenViewController(){
    	audioLoadHelper = new AudioLoadHelper();
    	levelModel = new LevelModel("level01", audioLoadHelper);
        firstView = new FirstView(this);
        levelEditor = new LevelEditorController(this.levelModel);
        gameController = new GameController(levelModel, audioLoadHelper,this);
        
        
    }

	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()) {
        case "quit":
            System.exit(0);
            break;

        case "editor":
        	this.levelEditor.getLevelEditorView().setVisible(true);
        	this.firstView.setVisible(false);
        	this.gameController.getGameView().setVisible(false);
//        	gameController.resetAll();
            break;
            

        case "game":
        	levelEditor.getLevelEditorView().setVisible(false);
        	this.firstView.setVisible(false);
        	gameController.getGameView().setVisible(true); 
//        	levelEditor.resetAll();
            break;
		}
		
	}

	public FirstView getFirstView() {
		return firstView;
	}

	public void setFirstView(FirstView firstView) {
		this.firstView = firstView;
	}    
	
	
}
