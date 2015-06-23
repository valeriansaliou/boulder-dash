package fr.enssat.BoulderDash.views;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.apple.eawt.Application;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.controllers.NavigationBetweenViewController;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.GameGroundView;
import fr.enssat.BoulderDash.views.InformationPanel;


/**
 * GameView
 *
 * Specifies the game view itself.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class GameView extends JFrame implements Observer {
	private GameGroundView gameGroundView;
	private JPanel actionPanel;
	private JPanel informationPanel;
	private JButton newGame, pause, load;
	private GameController gameController;
	private LevelModel levelModel;
	private JButton menu;

    /**
     * Class constructor
     *
     * @param  gameController  Game controller
     * @param  levelModel      Level model
     */
	public GameView(GameController gameController, LevelModel levelModel) {
		this.gameController = gameController;
		this.levelModel = levelModel;
		
        this.initializeView();
        this.createLayout();

        this.gameGroundView.grabFocus();
	}

    /**
     * Initializes the view
     */
    private void initializeView() {
        this.setVisible(false);
        this.setResizable(false);

        // UI parameters
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 200, 100);
        this.setSize(432, 536);

        // App parameters
        this.setTitle("Boulder Dash");

        Image appIcon = Toolkit.getDefaultToolkit().getImage("res/app/app_icon.png");
        this.setIconImage(appIcon);
    }

    /**
     * Creates the view layout
     */
    private void createLayout() {
        this.gameGroundView = new GameGroundView(this.gameController, this.levelModel);
        this.actionPanel = new JPanel();
        this.informationPanel = new InformationPanel(this.levelModel);

        // Add some buttons on the informationPanel
        this.newGame = this.createButton("restart", "Restart");
        this.pause = this.createButton("pause", "Pause");
        this.menu = this.createButton("menu", "Menu");

        this.add(this.actionPanel, BorderLayout.SOUTH);
        this.add(this.informationPanel, BorderLayout.NORTH);
        this.add(this.gameGroundView, BorderLayout.CENTER);
    }

    /**
     * Gets the game field view
     *
     * @return  Game field view
     */
	public GameGroundView getGameFieldView() {
		return this.gameGroundView;
	}

    /**
     * Creates the given button
     *
     * @param   name  Button name
     * @return  Created button
     */
	public JButton createButton(String id, String name) {
		JButton button = new JButton(name);
		button.addActionListener(this.gameController);
		button.setActionCommand(id);

		this.actionPanel.add(button);

		return button;
	}

	/**
     * Updates the frame
     *
     * @param   obs  Observable item
     * @param   obj  Object item
     */
	@Override
	public void update(Observable obs, Object obj) {
		// TODO
	}

    /**
     * Paints the frame
     *
     * @param   g  Graphics object
     */
    /*@Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }*/
}