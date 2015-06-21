package fr.enssat.BoulderDash.controllers;

import fr.enssat.BoulderDash.models.LevelModel;

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

    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
    public LevelEditorKeyController(LevelModel levelModel) {
        this.levelModel = levelModel;
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
                break;

            // Direction: DOWN
            case KeyEvent.VK_DOWN:
                break;

            // Direction: LEFT
            case KeyEvent.VK_LEFT:
                break;

            // Direction: RIGHT
            case KeyEvent.VK_RIGHT:
                break;
        }
    }

    /**
     * Handles the 'key released' event
     *
     * @param  e  Key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO
    }

    /**
     * Handles the 'key typed' event
     *
     * @param  e  Key event
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO
    }
}
