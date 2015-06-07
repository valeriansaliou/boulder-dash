package fr.enssat.BoulderDash.helpers;

import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;

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
    private Document levelDOM;

    // Parsed values
    private String nameValue = null;
    private Date dateCreatedValue = null;
    private Date dateModifiedValue = null;

    public LevelLoadHelper(String levelId) {
        this.setLevelId(levelId);

        // Let's go.
        this.loadLevelData();
    }

    private String getLevelPathInDataStore() {
        return pathToDataStore + "/" + this.getLevelId();
    }

    private void loadLevelData() {
        String pathToData = this.getLevelPathInDataStore();

        this.buildLevelDataNode(pathToData);
        this.processLevelData();
    }

    private void buildLevelDataNode(String pathToLevelData) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // Parse data in level file
            this.levelDOM = documentBuilder.parse(pathToLevelData);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLevelData() {
        Element documentElement = this.levelDOM.getDocumentElement();

        // Parse elements from structure
        this.parseNameElement(documentElement.getElementsByTagName("name"));
        this.parseNameElement(documentElement.getElementsByTagName("date"));
        this.parseNameElement(documentElement.getElementsByTagName("grid"));
    }

    private void parseNameElement(NodeList nameSel) {
        // Unicity: {1}
        if (nameSel != null && nameSel.getLength() > 0) {
            NodeList subList = nameSel.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                this.nameValue = subList.item(0).getNodeValue();
            }
        }
    }

    private void parseDateElement(NodeList dateSel) {
        // Unicity: {1}
        if (dateSel != null && dateSel.getLength() > 0) {
            NodeList subList = dateSel.item(0).getChildNodes();

            NodeList createdSel = subList.getElementsByTagName("created");
            NodeList modifiedSel = subList.getElementsByTagName("modified");

            // Read /bd-level/date/created value
            if (createdSel != null && createdSel.getLength() > 0) {
                NodeList createdSubList = createdSel.item(0).getChildNodes();

                if (createdSubList != null && createdSubList.getLength() > 0) {
                    this.dateCreatedValue = createdSubList.item(0).getNodeValue();
                }
            }

            // Read /bd-level/date/modified value
            if (modifiedSel != null && modifiedSel.getLength() > 0) {
                NodeList modifiedSubList = modifiedSel.item(0).getChildNodes();

                if (modifiedSubList != null && modifiedSubList.getLength() > 0) {
                    this.dateModifiedValue = modifiedSubList.item(0).getNodeValue();
                }
            }
        }
    }

    private void parseGridElement(NodeList gridSel) {
        // TODO
    }

    public String getLevelId() {
        return this.levelId;
    }

    private void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}
