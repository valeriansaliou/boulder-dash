package fr.enssat.BoulderDash.controllers;

import java.awt.event.ActionEvent;

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
public class LevelEditorController {
    private LevelModel levelModel;

    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
    public LevelEditorController(LevelModel levelModel) {
        this.levelModel = levelModel;
        this.levelModel.setShowCursor(true);

        new LevelEditorView(this, levelModel);
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
        }
    }
}