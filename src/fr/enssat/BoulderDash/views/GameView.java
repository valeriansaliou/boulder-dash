package fr.enssat.BoulderDash.views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.controllers.KeyController;
import fr.enssat.BoulderDash.models.LevelModel;

//GameView is created by GameController
public class GameView extends JPanel implements Observer {
	private LevelModel levelModel;
	
	public GameView(GameController gameController) {
		this.levelModel = gameController.getLevelModel();

		levelModel.addObserver(this);
		addKeyListener(new KeyController(levelModel));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setFocusable(true);
		requestFocus();
	}

	public void drawTerrain(int x, int y, Graphics g) {
		for (int i = x; i < y; i++) {
			for (int j = x; j < y; j++) {
				g.drawImage(levelModel.getImage(i, j), i * 16, j * 16, this);
			}
		}
	}

	public void paint(Graphics g) {
		drawTerrain(levelModel.getStart(), levelModel.getEnd(), g);
	}

	@Override
	public void update(Observable obs, Object obj) {
		repaint();
	}
	
	//Q : - UPDATE ?
	//    - CHECK WHAT IS AT LEFT / RIGHT ?
}