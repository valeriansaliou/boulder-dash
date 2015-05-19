package fr.enssat.BoulderDash.helpers;

/**
 * Proceeds level load routine
 * Able to deserialize level data from storage, and format it to internal representation
 * To be used as a data factory from level model classes
 *
 * @author      Valérian Saliou <vsaliou@enssat.fr>
 * @version     1.0
 * @since       2015-06-19
 */
public class LevelLoadHelper {
    private static String pathToDataStore = "res/levels"
    private int levelId = -1;

    public LevelLoadHelper(int levelId) {
        this.setLevelId(levelId);
    }

    private getLevelPathInDataStore() {
        return pathToDataStore + "/" + this.getLevelId();
    }

    private loadLevelData() {
        String pathToData = this.getLevelPathInDataStore();
    }

    private processLevelData() {

    }

    public getLevelId() {
        return this.levelId;
    }

    private setLevelId(int levelId) {
        this.levelId = levelId;
    }
}
