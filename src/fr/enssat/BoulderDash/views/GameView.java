package fr.enssat.BoulderDash.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.models.LevelModel;
//appelé par gameControler
public class GameView extends JFrame implements Observer{
	private JPanel gamePanel;
	private JPanel actionPanel;
	private JPanel informationPanel;
	private JButton newGame, pause, quit;
	private GameController gameController;
	private LevelModel levelModel;
	
	public GameView(GameController gameController){
		gamePanel = new JPanel();
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		actionPanel = new JPanel();
		informationPanel = new JPanel();
		this.gameController = gameController;
		this.levelModel = gameController.getLevelModel();
		
		newGame = createButton("New Game");
		pause = createButton("Pause");
		pause = createButton("Quit"); 

		add(actionPanel, BorderLayout.SOUTH);
		add(gamePanel, BorderLayout.CENTER);
		add(informationPanel, BorderLayout.NORTH);
		
		setVisible(true);
		levelModel.addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 100);
		setSize(500, 500);
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
		// TODO Auto-generated method stub
		
	}
}