package fr.enssat.BoulderDash.views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.apple.eawt.Application;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.GameView;


/**
 * FrameToDisplay
 *
 * Specifies a displayable frame.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class FrameToDisplay extends JFrame implements Observer {
	private GameView gameView;
	private JPanel actionPanel;
	private JPanel informationPanel;
	private JButton newGame, pause, quit, editor, save;
	private GameController gameController;
	private LevelModel levelModel;

    /**
     * Class constructor
     *
     * @param  gameController  Game controller
     * @param  levelModel      Level model
     */
	public FrameToDisplay(GameController gameController, LevelModel levelModel) {
		this.gameController = gameController;
		this.levelModel = levelModel;

        this.setVisible(true);
        this.setResizable(false);

        // UI parameters
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 200, 100);
        this.setSize(432, 510);

        // App parameters
        this.setTitle("Boulder Dash");

        Image appIcon = Toolkit.getDefaultToolkit().getImage("res/app/app_icon.png");
        this.setIconImage(appIcon);

        // Create the layout
        this.createLayout();

		// Grab the focus to use the keys
		this.gameView.grabFocus();
	}

    /**
     * Creates the view layout
     */
    private void createLayout() {
        this.gameView = new GameView(this.gameController, this.levelModel);
        this.actionPanel = new JPanel();
        this.informationPanel = new InformationPanel(this.levelModel);

        // Add some buttons on the informationPanel
        this.newGame = this.createButton("restart", "Restart");
        this.editor = this.createButton("editor", "Editor");
        this.pause = this.createButton("pause", "Pause");
        this.save = this.createButton("save", "Save");
        this.quit = this.createButton("quit", "Quit");

        this.add(this.actionPanel, BorderLayout.SOUTH);
        this.add(this.informationPanel, BorderLayout.NORTH);
        this.add(this.gameView, BorderLayout.CENTER);
    }

    /**
     * Gets the game view
     *
     * @return  Game view
     */
	public GameView getGameView() {
		return this.gameView;
	}

    /**
     * Creates the given button
     *
     * @param   name  Button name
     * @return  Created button
     */
	public JButton createButton(String id, String name) {
		JButton button = new JButton(name);
		button.addActionListener(this.gameController);
		button.setActionCommand(name);

		this.actionPanel.add(button);

		return button;
	}

    /**
     * Updates the frame
     *
     * @param   obs  Observable item
     * @param   obj  Object item
     */
	@Override
	public void update(Observable obs, Object obj) {
		// TODO
	}
}