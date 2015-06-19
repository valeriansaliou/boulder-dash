package fr.enssat.BoulderDash.views;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.models.LevelModel;


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

		this.gameView = new GameView(gameController, levelModel);
		this.actionPanel = new JPanel();
		this.informationPanel = new JPanel();

		// Add some buttons on the informationPanel
		this.newGame = createButton("New Game");
		this.editor = createButton("Editor");
		this.pause = createButton("Pause");
		this.save = createButton("Save");
		this.quit = createButton("Quit");

		add(this.actionPanel, BorderLayout.SOUTH);
		add(this.informationPanel, BorderLayout.NORTH);
		add(this.gameView, BorderLayout.CENTER);

		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 100);
		setSize(480, 550);

		// grab the focus to use the keys
		this.gameView.grabFocus();
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
	public JButton createButton(String name) {
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