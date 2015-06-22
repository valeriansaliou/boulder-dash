package fr.enssat.BoulderDash.views;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import fr.enssat.BoulderDash.controllers.LevelEditorController;
import fr.enssat.BoulderDash.controllers.LevelEditorKeyController;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.LevelEditorFieldView;
import fr.enssat.BoulderDash.views.AssetsLevelEditorComponent;


/**
 * LevelEditorView
 *
 * Specifies the level editor view.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class LevelEditorView extends JFrame implements Observer {
    private LevelEditorFieldView fieldPanel;
    private JPanel selectPanel;
    private AssetsLevelEditorComponent assetsComponent;
    private JPanel actionsComponent;

    private LevelEditorController levelEditorController;
    private LevelModel levelModel;

    /**
     * Class constructor
     */
	public LevelEditorView(LevelEditorController levelEditorController, LevelModel levelModel) {
        this.levelEditorController = levelEditorController;
        this.levelModel = levelModel;

        this.levelModel.addObserver(this);

        this.addKeyListener(new LevelEditorKeyController(this.levelModel));

		this.initializeView();
        this.createLayout();
	}

    /**
     * Initializes the view layout
     */
	private void initializeView() {
        this.setFocusable(true);
        this.setVisible(true);
        this.setResizable(true);

        // UI parameters
        this.setSize(1000, 500);
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
		this.fieldPanel = new LevelEditorFieldView(this.levelModel);
        this.selectPanel = new JPanel();

        this.assetsComponent = new AssetsLevelEditorComponent();
        this.actionsComponent = new JPanel();

        // Add actions
        this.actionsComponent.add(this.createButton("save", "Save"));
        this.actionsComponent.add(this.createButton("delete", "Delete"));
        this.actionsComponent.add(this.createButton("clear", "Clear"));
        this.actionsComponent.add(this.createButton("new", "New map..."));

        // Add select panel subcomponents
        this.selectPanel.add(this.assetsComponent, BorderLayout.NORTH);
        this.selectPanel.add(this.actionsComponent, BorderLayout.SOUTH);

        // Add top components
        this.add(this.fieldPanel, BorderLayout.CENTER);
        this.add(this.selectPanel, BorderLayout.WEST);
	}

    /**
     * Creates the given button
     *
     * @param   name  Button name
     * @return  Created button
     */
    public JButton createButton(String id, String name) {
        JButton button = new JButton(name);
        //button.addActionListener(this.gameController);
        button.setActionCommand(id);

        return button;
    }

    /**
     * Updates the view
     *
     * @param   obs  Observable item
     * @param   obj  Object item
     */
    @Override
    public void update(Observable obs, Object obj) {
        // Nothing done.
    }
}