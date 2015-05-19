package fr.enssat.BoulderDash.helpers;

public class LevelLoadHelper {
    private static String pathToDataStore = "res/levels"

    public LevelLoadHelper() {

    }

    private getLevelPathInDataStore(Level level) {
        return pathToDataStore + "/" + level.getName();
    }
}
