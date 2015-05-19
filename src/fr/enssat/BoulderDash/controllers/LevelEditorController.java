package fr.enssat.BoulderDash.controllers;

public class LevelEditorController {

    LevelEditorView view = null;

    public LevelEditorController() {
        // Initialize view
        this.setView(new GameView());
    }

    public LevelEditorView getView() {
        return this.view;
    }

    private void setView(LevelEditorView view) {
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