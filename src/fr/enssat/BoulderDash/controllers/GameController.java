package fr.enssat.BoulderDash.controllers;

import java.awt.EventQueue;
import java.awt.Window;

import fr.enssat.BoulderDash.views.GameView;
// créé la fenetre, gère le jeu en lui mm, démarre timer, ...
public class GameController {

    GameView view = null;

    public void GameController() {
        // Initialize view
        this.setView(new GameView());
        System.out.println("here");
    }

    public GameView getView() {
        return this.view;
    }

    private void setView(GameView view) {
        this.view = view;
    }

    public void main(String[] args) {
    	final GameController _this = this;
    	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Set view visible
            	_this.getView().setVisible(true);
            }
        });
    }
}