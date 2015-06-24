package fr.enssat.boulderdash.helpers;

import fr.enssat.BoulderDash.views.MenuLevelSelector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * LevelListHelper
 *
 * Level list helper
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-23
 */
public class LevelListHelper {
    private static String levelStorage = "res/levels";

    /**
     * Class constructor
     */
    public LevelListHelper() {
        // TODO
    }

    /**
     * Creates the level list
     *
     * @return  Level list selector
     */
    public MenuLevelSelector createLevelList() {
        String[] availableLevels = this.listAvailableLevels();

        // Proceed available levels listing
        MenuLevelSelector menuLevelList = new MenuLevelSelector(availableLevels);

        if(availableLevels.length > 0) {
            menuLevelList.setChoiceValue(availableLevels[0]);
        }

        if(availableLevels.length > 0) {
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
