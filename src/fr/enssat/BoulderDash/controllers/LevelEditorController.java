package fr.enssat.BoulderDash.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.LevelEditorView;

/**
 * LevelEditorController
 *
 * Manages the level editor controller.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class LevelEditorController implements ActionListener {
    private LevelModel levelModel;
	private LevelEditorView levelEditorView;
	private NavigationBetweenViewController nav;

    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
    public LevelEditorController(LevelModel levelModel, NavigationBetweenViewController nav) {
        this.levelModel = levelModel;
        this.levelModel.setShowCursor(true);

        this.nav = nav;
        
        this.levelEditorView = new LevelEditorView(this, levelModel);
    }

    /**
     * Handles the 'action performed' event
     *
     * @param  event  Action event
     */
    public void actionPerformed(ActionEvent event) {
        switch(event.getActionCommand()) {
            case "menu":
            	this.levelEditorView.setVisible(false);
            	this.nav.getFirstView().setVisible(true);
                break;
            //TODO other cases
        }
    }

	public LevelEditorView getLevelEditorView() {
		return levelEditorView;
	}

	public void setLevelEditorView(LevelEditorView levelEditorView) {
		this.levelEditorView = levelEditorView;
	}
    
    
}