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
		setTitle("Level editor | Boulder Dash");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.createLayout();
	}

    /**
     * Creates the view layout
     */
	private void createLayout() {
		Container pane = getContentPane();
		GridLayout gridLayout = new GridLayout(1, 2);
		pane.setLayout(gridLayout);

		JPanel fieldArea = new JPanel();
		JPanel selectArea = new JPanel();

		pane.add(fieldArea);
		pane.add(selectArea);
	}
}