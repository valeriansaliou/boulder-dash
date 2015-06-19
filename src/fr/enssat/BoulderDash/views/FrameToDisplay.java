package fr.enssat.BoulderDash.views;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.controllers.KeyController;
import fr.enssat.BoulderDash.models.LevelModel;

//GameView is created by GameController
public class FrameToDisplay extends JFrame implements Observer {
	private GameView gameView;
	private JPanel actionPanel;
	private JPanel informationPanel;
	private JButton newGame, pause, quit, editor, save;
	private GameController gameController;
	private LevelModel levelModel;

	/*
	 * Construction of the main windows which will contain all the elements :
	 * gameView, informationPanel for scores and actionPanel to quit/newgame/etc
	 */
	public FrameToDisplay(GameController gameController, LevelModel levelModel) {
		this.gameController = gameController;
		this.levelModel = levelModel;

		this.gameView = new GameView(gameController, levelModel);
		this.actionPanel = new JPanel();
		this.informationPanel = new JPanel();

		// add some buttons on the informationPanel
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

	public GameView getGameView() {
		return this.gameView;
	}

	public JButton createButton(String nom) {
		JButton button = new JButton(nom);
		button.addActionListener(this.gameController);
		button.setActionCommand(nom);

		this.actionPanel.add(button);

		return button;
	}

	@Override
	public void update(Observable obs, Object obj) {
		// TODO
	}
}