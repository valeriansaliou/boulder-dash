package fr.enssat.BoulderDash.controllers;

public class GameController {

    GameView view = null;

    public GameController() {
        // Initialize view
        this.setView(new GameView());
    }

    public GameView getView() {
        return this.view;
    }

    private void setView(GameView view) {
        this.view = view;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Set view visible
                this.getView().setVisible(true);
            }
        });
    }
}