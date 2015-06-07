package fr.enssat.BoulderDash.helpers;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private String levelId = null;
    private Document levelDOM;
    private XPath xpathBuilder;
    final private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd/HH:mm:ss", Locale.ENGLISH);

    // Parsed values
    private String nameValue = null;
    private Date dateCreatedValue = null;
    private Date dateModifiedValue = null;

    public LevelLoadHelper(String levelId) {
        this.setLevelId(levelId);

        if (this.levelId != null) {
            // Let's go.
            this.loadLevelData();
        }
    }

    private String getLevelPathInDataStore() {
        return pathToDataStore + "/" + this.getLevelId() + ".xml";
    }

    private void loadLevelData() {
        this.xpathBuilder = XPathFactory.newInstance().newXPath();
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
        // Parse elements from structure
        try {
            this.parseNameElement();
            this.parseNameElement();
            this.parseNameElement();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private void parseNameElement() throws XPathExpressionException {
        this.nameValue = this.xpathBuilder.compile(
                "/bd-level/name"
        ).evaluate(this.levelDOM);

        System.out.print("this.nameValue > " + this.nameValue);
    }

    private void parseDateElement() throws XPathExpressionException, ParseException {
        this.dateCreatedValue = dateFormatter.parse(xpathBuilder.compile(
                "/bd-level/date[@format='utc']/created"
        ).evaluate(this.levelDOM));

        this.dateModifiedValue = dateFormatter.parse(this.xpathBuilder.compile(
                "/bd-level/date[@format='utc']/modified"
        ).evaluate(this.levelDOM));
    }

    private void parseGridElement() {
        // TODO
    }

    public String getLevelId() {
        return this.levelId;
    }

    private void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}
