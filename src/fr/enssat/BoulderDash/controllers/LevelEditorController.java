package fr.enssat.BoulderDash.controllers;

import java.awt.event.ActionEvent;

/**
 * LevelEditorController
 *
 * Manages the level editor controller.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class LevelEditorController {
    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
    public LevelEditorController() {
        // TODO: as needed
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