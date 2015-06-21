package fr.enssat.BoulderDash.helpers;

import java.text.SimpleDateFormat;
import java.util.Locale;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * LevelSaveHelper
 *
 * Proceeds level save routine
 * Able to iterate on internal representation of a map and serialize it to XML
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-21
 */
public class LevelSaveHelper {
    private static String pathToDataStore = "res/levels";
    private String levelId = null;
    private DisplayableElementModel[][] groundGrid;
    private SimpleDateFormat dateFormatter;

    /**
     * Class constructor
     *
     * @param  levelId  Level identifier
     */
    public LevelSaveHelper(String levelId, DisplayableElementModel[][] groundGrid) {
        this.setLevelId(levelId);
        this.setGroundGrid(groundGrid);

        // Requirements
        this.dateFormatter = new SimpleDateFormat("yyy-MM-dd/HH:mm:ss", Locale.ENGLISH);

        if (this.levelId != null) {
            // Let's go.
            this.saveLevelData();
        }
    }

    /**
     * Gets level storage path
     *
     * @return  Level path, with file extension
     */
    private String getLevelPathInDataStore() {
        return this.pathToDataStore + "/" + this.getLevelId() + ".xml";
    }

    /**
     * Saves the level data into XML storage
     */
    private void saveLevelData() {
        // TODO
    }

    /**
     * Gets the level identifier
     *
     * @return  Level identifier
     */
    public String getLevelId() {
        return this.levelId;
    }

    /**
     * Sets the level identifier
     *
     * @param  levelId  Level identifier
     */
    private void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    /**
     * Gets the ground grid
     *
     * @return  Ground grid
     */
    public DisplayableElementModel[][] getGroundGrid() {
        return this.groundGrid;
    }

    /**
     * Sets the ground grid
     *
     * @param  groundGrid  Ground grid
     */
    private void setGroundGrid(DisplayableElementModel[][] groundGrid) {
        this.groundGrid = groundGrid;
    }
}
