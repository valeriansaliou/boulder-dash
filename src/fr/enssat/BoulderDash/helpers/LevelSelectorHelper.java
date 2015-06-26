package fr.enssat.BoulderDash.helpers;

import fr.enssat.BoulderDash.views.MenuLevelSelector;
import fr.enssat.BoulderDash.views.LevelEditorView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * LevelSelectorHelper
 *
 * Level selector helper
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-23
 */
public class LevelSelectorHelper {
    private static String levelStorage = "./res/levels";
    private boolean hasEmptyElement = false;
    private LevelEditorView levelEditorView = null;

    /**
     * Class constructor
     */
    public LevelSelectorHelper(boolean hasEmptyElement) {
        this.hasEmptyElement = hasEmptyElement;
    }

    public LevelSelectorHelper(boolean hasEmptyElement, LevelEditorView levelEditorView) {
        this(hasEmptyElement);

        this.levelEditorView = levelEditorView;
    }

    /**
     * Creates the level list
     *
     * @return  Level list selector
     */
    public MenuLevelSelector createLevelList() {
        String[] availableLevels = this.listAvailableLevels();

        // Proceed available levels listing
        MenuLevelSelector menuLevelList = new MenuLevelSelector(availableLevels, this.levelEditorView);

        if(availableLevels.length > 0) {
            menuLevelList.setChoiceValue(availableLevels[0]);
            menuLevelList.setSelectedIndex(0);
        };

        menuLevelList.addActionListener(menuLevelList);

        return menuLevelList;
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

        // Add empty element?
        if(this.hasEmptyElement) {
            stockList.add("");
        }

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
}
