package fr.enssat.BoulderDash.views;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import fr.enssat.BoulderDash.views.MenuImage;
import fr.enssat.BoulderDash.views.MenuLevelSelector;
import fr.enssat.BoulderDash.controllers.NavigationBetweenViewController;


/**
 * FirstView
 *
 * Menu view
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-23
 */
public class FirstView extends JFrame {
    private static String levelStorage = "res/levels";

	private JButton game;
	private JButton editor;
	private JButton quit;
	private NavigationBetweenViewController navigationBetweenViewController;
    private MenuImage menuImage;
    private MenuLevelSelector menuLevelList;
	private JPanel actionPanel;

    /**
     * Class constructor
     */
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
        this.createLevelList();

        JPanel targetPanel = new JPanel();

        this.menuImage = new MenuImage();
    	this.actionPanel = new JPanel();
    	
        // Add some buttons on the actionPanel
        this.game = this.createButton("game", "Game");
        this.editor = this.createButton("editor", "Editor");
        this.quit = this.createButton("quit", "Quit");

        this.add(this.menuImage, BorderLayout.CENTER);
        this.add(targetPanel, BorderLayout.SOUTH);

        targetPanel.add(this.menuLevelList, BorderLayout.NORTH);
        targetPanel.add(this.actionPanel, BorderLayout.SOUTH);
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

    /**
     * Class constructor
     */
    private void createLevelList() {
        String[] availableLevels = this.listAvailableLevels();

        // Proceed available levels listing
        this.menuLevelList = new MenuLevelSelector(availableLevels);

        if(availableLevels.length > 0) {
            this.menuLevelList.setSelectedIndex(0);
        };

        this.menuLevelList.addActionListener(this.menuLevelList);
    }

    /**
     * Lists available levels and store them in instance context
     *
     * @return  Available levels
     */
    private String[] listAvailableLevels() {
        List<String> stockList = new ArrayList<String>();

        File directory = new File(levelStorage);
        File[] fileList = directory.listFiles();
        String fileName, fileNameExtValue;
        int fileNameExtIndex;

        for (File file : fileList){
            fileName = file.getName();
            fileNameExtIndex = fileName.lastIndexOf('.');

            if (fileNameExtIndex > 0) {
                fileNameExtValue = fileName.substring(fileNameExtIndex, fileName.length());

                if(fileNameExtValue.equals(".xml")) {
                    fileName = fileName.substring(0, fileNameExtIndex);
                    stockList.add(fileName);
                }
            }
        }

        // Convert to String[] (required)
        String[] itemsArr = new String[stockList.size()];
        itemsArr = stockList.toArray(itemsArr);

        return itemsArr;
    }

    /**
     * Gets the selected level identifier
     *
     * @return  Level identifier
     */
    public String getLevelIdentifier() {
        return this.menuLevelList.getChoiceValue();
    }
}
