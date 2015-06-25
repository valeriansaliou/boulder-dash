package fr.enssat.BoulderDash.controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enssat.BoulderDash.exceptions.LevelConstraintNotRespectedException;
import fr.enssat.BoulderDash.helpers.LevelSaveHelper;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.models.DisplayableElementModel;
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
        
        this.levelEditorView = new LevelEditorView(this, levelModel, nav);

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
            	this.nav.getMenuView().setVisible(true);
                this.nav.getAudioLoadHelper().startMusic("game");

                break;

            case "save":
                // Check constraints
                try {
                    this.levelModel.checkConstraints();

                    // Save action (direct save)
                    String levelId = this.levelEditorView.getSelectedLevel();

                    if(levelId == null || levelId.isEmpty()) {
                        // Create a new level
                        new LevelSaveHelper(levelModel.getGroundLevelModel());
                    } else {
                        // Overwrite existing level
                        new LevelSaveHelper(levelId, levelModel.getGroundLevelModel());
                    }

                    JFrame frameDialog = new JFrame("Information");
                    JOptionPane.showMessageDialog(frameDialog, "Niveau sauvegardé");
                } catch(LevelConstraintNotRespectedException e) {
                    JFrame frameDialog = new JFrame("Erreur");
                    JOptionPane.showMessageDialog(frameDialog, e.getMessage());
                }

                break;

            case "delete":
                String levelId = this.levelEditorView.getSelectedLevel();

                if(levelId == null || levelId.isEmpty()) {
                    JFrame frameDialog = new JFrame("Information");
                    JOptionPane.showMessageDialog(frameDialog, "Niveau non sauvegardé, inutile de le supprimer !");
                }
                break;

            case "new":
                // TODO
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
     * Gets level model
     *
     * @return  Level model
     */
    public LevelModel getLevelModel() {
        return this.levelModel;
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