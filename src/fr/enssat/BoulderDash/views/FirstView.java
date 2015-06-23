package fr.enssat.BoulderDash.views;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.enssat.BoulderDash.views.MenuImage;
import fr.enssat.BoulderDash.controllers.NavigationBetweenViewController;

public class FirstView extends JFrame {
	private JButton game;
	private JButton editor;
	private JButton quit;
	private NavigationBetweenViewController navigationBetweenViewController;
    private MenuImage menuImage;
	private JPanel actionPanel;

	public FirstView(NavigationBetweenViewController navigationBetweenViewController) {
		this.navigationBetweenViewController = navigationBetweenViewController;
		this.initializeView();
		this.createLayout();
	}

    /**
     * Initializes the view
     */
    private void initializeView() {
        this.setVisible(true);
        this.setResizable(false);

        // UI parameters
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 200, 100);
        this.setSize(432, 536);

        // App parameters
        this.setTitle("Boulder Dash menu");
    }

    /**
     * Creates the view layout
     */
    private void createLayout() {
        this.menuImage = new MenuImage();
    	this.actionPanel = new JPanel();
    	
        // Add some buttons on the actionPanel
        this.game = this.createButton("game", "Game");
        this.editor = this.createButton("editor", "Editor");
        this.quit = this.createButton("quit", "Quit");

        this.add(this.menuImage, BorderLayout.CENTER);
        this.add(this.actionPanel, BorderLayout.SOUTH);
    }


    /**
     * Creates the given button
     *
     * @param   name  Button name
     * @return  Created button
     */
	public JButton createButton(String id, String name) {
		JButton button = new JButton(name);
		button.addActionListener(this.navigationBetweenViewController);
		button.setActionCommand(id);

		this.actionPanel.add(button);

		return button;
	}
}
