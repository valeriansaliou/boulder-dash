package fr.enssat.BoulderDash.helpers;

import jdk.internal.org.xml.sax.SAXException;
import sun.plugin.dom.core.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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
    private String levelId = "1";
    Document parsedDOM;

    public LevelLoadHelper(int levelId) {
        this.setLevelId(levelId);
    }

    private String getLevelPathInDataStore() {
        return pathToDataStore + "/" + this.getLevelId();
    }

    private void loadLevelData() {
        String pathToData = this.getLevelPathInDataStore();
    }

    private void buildLevelDataNode(String pathToLevelData) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // Parse data in level file
            this.parsedDOM = documentBuilder.parse(pathToLevelData);
        } catch(ParserConfigurationException | SAXException | IOException exc) {
            exc.printStackTrace();
        }
    }

    private void processLevelData() {
        // 
    }

    public String getLevelId() {
        return this.levelId;
    }

    private void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}
