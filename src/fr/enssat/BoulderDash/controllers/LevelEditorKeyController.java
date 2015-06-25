package fr.enssat.BoulderDash.controllers;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.LevelEditorView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * LevelEditorKeyController
 *
 * Manages the key events controller.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-21
 */
public class LevelEditorKeyController implements KeyListener {
    private LevelModel levelModel;
    private LevelEditorView levelEditorView;
	private boolean capLocks;

    /**
     * Class constructor
     *
     * @param  levelModel       Level model
     * @param  levelEditorView  Level editor view
     */
    public LevelEditorKeyController(LevelModel levelModel, LevelEditorView levelEditorView) {
        this.levelModel = levelModel;
        this.capLocks = false;
        this.levelEditorView = levelEditorView;
    }

    /**
     * Handles the 'key pressed' event
     *
     * @param  e  Key event
     */
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            // Direction: UP
            case KeyEvent.VK_UP:
                this.levelModel.decrementCursorYPosition();
                break;

            // Direction: DOWN
            case KeyEvent.VK_DOWN:
                this.levelModel.incrementCursorYPosition();
                break;

            // Direction: LEFT
            case KeyEvent.VK_LEFT:
                this.levelModel.decrementCursorXPosition();
                break;

            // Direction: RIGHT
            case KeyEvent.VK_RIGHT:
                this.levelModel.incrementCursorXPosition();
                break;

            // Key: SPACE
            case KeyEvent.VK_SPACE:
                this.levelModel.triggerBlockChange(this.levelEditorView.getPickedBlockValue());
                break;
            
            case 16:
                this.capLocks = !capLocks;
                break;
        }

        // Hold block change (quick edit)
        if(capLocks) {
        	this.levelModel.triggerBlockChange(this.levelEditorView.getPickedBlockValue());
        }
    }

    /**
     * Handles the 'key released' event
     *
     * @param  e  Key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing.
    }

    /**
     * Handles the 'key typed' event
     *
     * @param  e  Key event
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing.
    }
}
