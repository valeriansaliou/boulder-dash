package fr.enssat.BoulderDash.controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enssat.BoulderDash.exceptions.LevelConstraintNotRespectedException;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.LevelEditorView;
import fr.enssat.BoulderDash.controllers.NavigationBetweenViewController;

import javax.swing.*;

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
     * Class constructor'
     *
     * @param  levelModel  Level model
     */
    public LevelEditorController(LevelModel levelModel, NavigationBetweenViewController nav) {
        this.levelModel = levelModel;
        this.levelModel.setShowCursor(true);

        this.nav = nav;
        this.nav.getAudioLoadHelper().stopMusic();
        
        this.levelEditorView = new LevelEditorView(this, levelModel);

        // Pre-bind event watcher (hack to fix a Java issue)
        this.levelModel.decrementCursorXPosition();
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
                this.nav.getAudioLoadHelper().startMusic("game");

                break;

            case "save":
                // Check constraints
                try {
                    this.levelModel.checkConstraints();

                    // Save action
                    // TODO

                    JFrame frameDialog = new JFrame("Information");
                    JOptionPane.showMessageDialog(frameDialog, "Niveau sauvegardé");
                } catch(LevelConstraintNotRespectedException e) {
                    JFrame frameDialog = new JFrame("Erreur");
                    JOptionPane.showMessageDialog(frameDialog, e.getMessage());
                }

                break;

            case "delete":
                break;

            case "clear":
                break;

            case "new":
                break;
        }

        this.getLevelEditorView().getLevelEditorGroundView().grabFocus();
    }

    /**
     * Gets the level editor view
     *
     * @return  Level editor view
     */
	public LevelEditorView getLevelEditorView() {
		return levelEditorView;
	}

    /**
     * Sets the level editor view
     *
     * @param  levelEditorView  Level editor view
     */
	public void setLevelEditorView(LevelEditorView levelEditorView) {
		this.levelEditorView = levelEditorView;
	}
    
    
}