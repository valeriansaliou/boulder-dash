package fr.enssat.BoulderDash.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.models.LevelModel;

//GameView is created by GameController
public class GameView extends JPanel implements Observer {
	JPanel gameView;
	private GameController gameController;
	private LevelModel levelModel;
	
	public GameView(GameController gameController) {
		gameView = new JPanel();
		
		this.gameController = gameController;
		this.levelModel = gameController.getLevelModel();
		
		gameView.setBorder(BorderFactory.createLineBorder(Color.black));
	}


	public void drawCase(int x, int y, Graphics g) {
		g.drawImage(levelModel.getImage(x, y), x, y, this);
	}

	public void paint(Graphics g) {
		drawCase(0, 0, g);
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}
	
	public Component getPanel() {
		return gameView;
	}
}