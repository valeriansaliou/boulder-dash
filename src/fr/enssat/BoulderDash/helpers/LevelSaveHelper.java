package fr.enssat.BoulderDash.helpers;

import java.text.SimpleDateFormat;
import java.util.Locale;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element bdLevel = document.createElementNS("fr.enssat.boulderdash", "bd-level");
            document.appendChild(bdLevel);

            // append child elements to root element
            bdLevel.appendChild(dateNode(document, "2015-05-19/15:15:20", "2015-05-19/15:15:20"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the name node
     *
     * @param   document  Document
     * @param   name      'name' child value
     * @return  Name node
     */
    private static Node nameNode(Document document, String name) {
        return textNode(document, "name", name);
    }

    /**
     * Creates the date node
     *
     * @param   document       Document
     * @param   date_created   'date_created' child value
     * @param   date_modified  'date_modified' child value
     * @return  Date node
     */
    private static Node dateNode(Document document, String date_created, String date_modified) {
        Element dateElement = document.createElement("date");

        dateElement.setAttribute("format", "utc");

        dateElement.appendChild(textNode(document, "created", date_created));
        dateElement.appendChild(textNode(document, "modified", date_modified));

        return dateElement;
    }

    /**
     * Creates the size node
     *
     * @param   document  Document
     * @param   width     'width' child value
     * @param   height    'height' child value
     * @return  Size node
     */
    private static Node sizeNode(Document document, String width, String height) {
        Element sizeElement = document.createElement("size");

        sizeElement.appendChild(textNode(document, "width", width));
        sizeElement.appendChild(textNode(document, "height", height));

        return sizeElement;
    }

    /**
     * Creates a bare text node
     *
     * @param   document  Document
     * @param   name      Element name
     * @param   value     Element value
     * @return  Text node
     */
    private static Node textNode(Document document, String name, String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));

        return node;
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
