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
	private GameView gamePanel;
	private JPanel actionPanel;
	private JPanel informationPanel;
	private JButton newGame, pause, quit,editor;
	private GameController gameController;
	private LevelModel levelModel;

	public FrameToDisplay(GameController gameController) {
		this.gameController = gameController;
		this.levelModel = gameController.getLevelModel();
		
		gamePanel = new GameView(gameController);		
		actionPanel = new JPanel();
		informationPanel = new JPanel();

		newGame = createButton("New Game");
		editor = createButton("Editor");
		pause = createButton("Pause");
		pause = createButton("Quit");
		
		add(actionPanel, BorderLayout.SOUTH);		
		add(informationPanel, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);

		setVisible(true);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 100);
		setSize(480, 550);
		// grab the focus to use the keys
		gamePanel.grabFocus();
	}

	public JButton createButton(String nom) {
		JButton button = new JButton(nom);
		button.addActionListener(gameController);
		button.setActionCommand(nom);
		actionPanel.add(button);
		return button;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	}
}