package fr.enssat.BoulderDash.views;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import fr.enssat.boulderdash.helpers.LevelSelectorHelper;
import fr.enssat.BoulderDash.controllers.LevelEditorController;
import fr.enssat.BoulderDash.controllers.LevelEditorKeyController;
import fr.enssat.BoulderDash.controllers.NavigationBetweenViewController;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.LevelEditorGroundView;
import fr.enssat.BoulderDash.views.AssetsLevelEditorComponent;
import fr.enssat.BoulderDash.views.MenuLevelSelector;


/**
 * LevelEditorView
 *
 * Specifies the level editor view.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class LevelEditorView extends JFrame implements Observer {
    private LevelEditorGroundView fieldPanel;
    private JPanel selectPanel;
    private AssetsLevelEditorComponent assetsComponent;
    private JPanel actionsComponent;
    private MenuLevelSelector menuLevelSelector;
    private NavigationBetweenViewController nav;

    private LevelEditorController levelEditorController;
    private LevelModel levelModel;

    private String pickedBlockValue;

    /**
     * Class constructor
     */
	public LevelEditorView(LevelEditorController levelEditorController, LevelModel levelModel, NavigationBetweenViewController nav) {
        this.levelEditorController = levelEditorController;
        this.levelModel = levelModel;
        this.nav = nav;

        this.levelModel.addObserver(this);

		this.initializeView();
        this.createLayout();

        this.fieldPanel.grabFocus();
	}

    /**
     * Initializes the view layout
     */
	private void initializeView() {
        this.setFocusable(true);
        this.setVisible(false);
        this.setResizable(false);

        // UI parameters
        this.setSize(903, 454);
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
        // List of levels
        LevelSelectorHelper levelSelectorHelper = new LevelSelectorHelper(true, this);
        this.menuLevelSelector = levelSelectorHelper.createLevelList();

        // Field + select panels
		this.fieldPanel = new LevelEditorGroundView(this.levelModel, this);
        this.selectPanel = new JPanel();

        this.assetsComponent = new AssetsLevelEditorComponent(this);
        this.actionsComponent = new JPanel();

        // Add actions
        this.actionsComponent.add(this.menuLevelSelector);
        this.actionsComponent.add(this.createButton("save", "Save"));
        this.actionsComponent.add(this.createButton("delete", "Delete"));
        this.actionsComponent.add(this.createButton("new", "New level"));
        this.actionsComponent.add(this.createButton("menu", "Menu"));

        // Add select panel subcomponents
        this.selectPanel.setLayout(new BoxLayout(this.selectPanel, BoxLayout.Y_AXIS));

        this.selectPanel.add(this.assetsComponent);
        this.selectPanel.add(this.actionsComponent);

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
        button.addActionListener(this.levelEditorController);
        button.setActionCommand(id);

        return button;
    }

    /**
     * Gets the level editor field view
     *
     * @return  Level editor field view
     */
    public LevelEditorGroundView getLevelEditorGroundView() {
        return this.fieldPanel;
    }

    /**
     * Gets picked block value
     *
     * @return  Picked block value
     */
    public String getPickedBlockValue() {
        return this.pickedBlockValue;
    }

    /**
     * Sets picked block value
     *
     * @param  pickedBlockValue  Picked block value
     */
    public void setPickedBlockValue(String pickedBlockValue) {
        this.pickedBlockValue = pickedBlockValue;
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

    /**
     * Menu level selector change handler
     *
     * @param  changedSelector  Changed selector
     */
    public void menuLevelSelectorChanged(MenuLevelSelector changedSelector) {
        LevelModel pickedLevelModel;
        String selectedLevel = changedSelector.getChoiceValue();

        if(selectedLevel != null && !selectedLevel.isEmpty()) {
            // Load existing model
            pickedLevelModel = new LevelModel(selectedLevel, this.nav.getAudioLoadHelper());
        } else {
            // New blank model for editor
            pickedLevelModel = new LevelModel(this.nav.getAudioLoadHelper());
        }

        this.levelEditorController = new LevelEditorController(pickedLevelModel, this.nav);

        this.levelEditorController.getLevelEditorView().setVisible(true);
        this.levelEditorController.getLevelEditorView().getLevelEditorGroundView().grabFocus();
    }
}