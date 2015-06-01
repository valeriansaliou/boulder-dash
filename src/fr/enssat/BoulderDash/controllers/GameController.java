package fr.enssat.BoulderDash.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.FrameToDisplay;
// GameController is creating the view
public class GameController implements ActionListener{
	private LevelModel levelModel;

	public GameController(LevelModel levelModel){
		this.levelModel = levelModel;
		JFrame gameView = new FrameToDisplay(this);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "Quit") {
			System.exit(0);
		}		
	}

	
	public LevelModel getLevelModel() {
		return levelModel;
	}

}