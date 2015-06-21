package fr.enssat.BoulderDash.views;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import fr.enssat.BoulderDash.models.LevelModel;


/**
 * LevelEditorView
 *
 * Specifies the level editor view.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class LevelEditorView extends JFrame implements Observer {
    private JPanel fieldPanel;
    private JPanel selectPanel;
    private LevelModel levelModel;

    /**
     * Class constructor
     */
	public LevelEditorView(LevelModel levelModel) {
        this.levelModel = levelModel;

		this.initializeView();
        this.createLayout();
	}

    /**
     * Initializes the view layout
     */
	private void initializeView() {
        this.setFocusable(true);
        this.setVisible(true);
        this.setResizable(false);

        // UI parameters
        this.setSize(800, 510);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // App parameters
        this.setTitle("Level Editor | Boulder Dash");

        Image appIcon = Toolkit.getDefaultToolkit().getImage("res/app/app_icon.png");
        this.setIconImage(appIcon);
	}

    /**
     * Creates the view layout
     */
	private void createLayout() {
		GridLayout gridLayout = new GridLayout(1, 2);
        this.setLayout(gridLayout);

		this.fieldPanel = new JPanel();
		this.selectPanel = new JPanel();

        this.add(this.fieldPanel, BorderLayout.WEST);
        this.add(this.selectPanel, BorderLayout.EAST);
	}

    /**
     * Updates the view
     *
     * @param   obs  Observable item
     * @param   obj  Object item
     */
    @Override
    public void update(Observable obs, Object obj) {
        // TODO
    }
}