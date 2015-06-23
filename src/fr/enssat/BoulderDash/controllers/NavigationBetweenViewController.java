package fr.enssat.BoulderDash.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enssat.BoulderDash.helpers.AudioLoadHelper;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.FirstView;
import fr.enssat.BoulderDash.controllers.LevelEditorController;
import fr.enssat.BoulderDash.controllers.GameController;

/**
 * Controller to navigate between the different views
 * 
 * @author Colin Leverger <me@colinleverger.fr>
 *
 */
public class NavigationBetweenViewController implements ActionListener {
	private LevelEditorController levelEditorController;
	private FirstView firstView;
	private AudioLoadHelper audioLoadHelper;
	private LevelModel levelModelForGame, levelModelForEditor;
	private GameController gameController;

	public NavigationBetweenViewController() {
		this.audioLoadHelper = new AudioLoadHelper();

        // Play game music
        this.getAudioLoadHelper().startMusic("game");

		// Creation of the first view
		this.firstView = new FirstView(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()) {
            case "quit":
                System.exit(0);
                break;

            case "editor":
                // New blank model for editor
                this.levelModelForEditor = new LevelModel(audioLoadHelper);
                this.levelEditorController = new LevelEditorController(this.levelModelForEditor, this);

                this.levelEditorController.getLevelEditorView().setVisible(true);

                if (gameController != null) {
                    this.gameController.getGameView().setVisible(false);
                }

                break;

            case "game":
                // Reinit the levelModelForGame...
                String pickedLevelIdentifier = this.firstView.getLevelIdentifier();

                this.levelModelForGame = new LevelModel(pickedLevelIdentifier, audioLoadHelper);
                this.gameController = new GameController(levelModelForGame, audioLoadHelper, this);

                if (levelEditorController != null) {
                    this.levelEditorController.getLevelEditorView().setVisible(false);
                }

                this.gameController.getGameView().setVisible(true);
                this.gameController.getGameView().getGameFieldView().grabFocus();

			    break;
		}

		this.firstView.setVisible(false);
	}

    /**
     * Get the audio load helper
     *
     * @return  Audio load helper
     */
    public AudioLoadHelper getAudioLoadHelper() {
        return this.audioLoadHelper;
    }

    /**
     * Get the first view
     *
     * @return  First view
     */
    public FirstView getFirstView() {
        return this.firstView;
    }

	/**
	 * Set the first view
	 * 
	 * @param firstView
	 */
	public void setFirstView(FirstView firstView) {
		this.firstView = firstView;
	}
}
