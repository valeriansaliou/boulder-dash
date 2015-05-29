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

// tout ce qu'on va appeler depuis le contrôleur LevelEditeurControler ou GameController (c'est ocmmun aux deux)
// retourne la représentation interne des niveaux sous forme d'objet java
public class LevelLoadHelper {
    private static String pathToDataStore = "res/levels";
    private int levelId = -1;

    public LevelLoadHelper(int levelId) {
        this.setLevelId(levelId);
    }

    private String getLevelPathInDataStore() {
        return pathToDataStore + "/" + this.getLevelId();
    }

    private void loadLevelData() {
        String pathToData = this.getLevelPathInDataStore();
    }

    private void processLevelData() {

    }

    public int getLevelId() {
        return this.levelId;
    }

    private void setLevelId(int levelId) {
        this.levelId = levelId;
    }
}
