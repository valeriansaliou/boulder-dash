package fr.enssat.BoulderDash.views;

import javax.swing.*;
import java.awt.*;


/**
 * LevelEditorView
 *
 * Specifies the level editor view.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class LevelEditorView extends JFrame {
    private JPanel fieldPanel;
    private JPanel selectPanel;

    /**
     * Class constructor
     */
	public LevelEditorView() {
		this.initializeView();
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

		this.createLayout();
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
}