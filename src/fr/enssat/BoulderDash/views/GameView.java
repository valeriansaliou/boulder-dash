package fr.enssat.BoulderDash.views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.apple.eawt.Application;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.GameFieldView;
import fr.enssat.BoulderDash.views.InformationPanel;


/**
 * GameView
 *
 * Specifies the game view itself.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class GameView extends JFrame implements Observer {
	private GameFieldView gameFieldView;
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
	public GameView(GameController gameController, LevelModel levelModel) {
		this.gameController = gameController;
		this.levelModel = levelModel;

        this.initializeView();
        this.createLayout();

        this.gameFieldView.grabFocus();
	}

    /**
     * Initializes the view
     */
    private void initializeView() {
        this.setVisible(true);
        this.setResizable(false);

        // UI parameters
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 200, 100);
        this.setSize(432, 536);

        // App parameters
        this.setTitle("Boulder Dash");

        Image appIcon = Toolkit.getDefaultToolkit().getImage("res/app/app_icon.png");
        this.setIconImage(appIcon);
    }

    /**
     * Creates the view layout
     */
    private void createLayout() {
        this.gameFieldView = new GameFieldView(this.gameController, this.levelModel);
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
        this.add(this.gameFieldView, BorderLayout.CENTER);
    }

    /**
     * Gets the game field view
     *
     * @return  Game field view
     */
	public GameFieldView getGameFieldView() {
		return this.gameFieldView;
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
		button.setActionCommand(id);

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