package fr.enssat.BoulderDash.controllers;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.GameView;
// créé la fenetre, gère le jeu en lui mm, démarre timer, ...
public class GameController implements ActionListener{
	private LevelModel levelModel;

	public GameController(LevelModel levelModel){
		this.levelModel = levelModel;
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