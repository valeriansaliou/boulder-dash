package fr.enssat.BoulderDash.helpers;

import fr.enssat.BoulderDash.models.BoulderModel;
import fr.enssat.BoulderDash.models.DiamondModel;
import fr.enssat.BoulderDash.models.DirtModel;
import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.EmptyModel;
import fr.enssat.BoulderDash.models.RockfordModel;
import fr.enssat.BoulderDash.models.SteelWallModel;

import fr.enssat.boulderdash.exceptions.UnknownSpriteException;
import modelToImplement.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
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
    private int widthSizeValue = 0;
    private int heightSizeValue = 0;
    private DisplayableElementModel[][] groundGrid;

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
            this.parseDateElement();
            this.parseSizeElement();
            this.parseGridElement();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseNameElement() throws XPathExpressionException {
        this.nameValue = this.xpathBuilder.compile(
                "/bd-level/name"
        ).evaluate(this.levelDOM);

        System.out.println("");
        System.out.print("this.nameValue > " + this.nameValue);
    }

    private void parseDateElement() throws XPathExpressionException, ParseException {
        this.dateCreatedValue = dateFormatter.parse(xpathBuilder.compile(
                "/bd-level/date[@format='utc']/created"
        ).evaluate(this.levelDOM));

        this.dateModifiedValue = dateFormatter.parse(this.xpathBuilder.compile(
                "/bd-level/date[@format='utc']/modified"
        ).evaluate(this.levelDOM));

        System.out.println("");
        System.out.print("this.dateCreatedValue > " + (new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).format(this.dateCreatedValue));
    }

    private void parseSizeElement() throws XPathExpressionException {
        this.widthSizeValue = Integer.parseInt(this.xpathBuilder.compile(
                "/bd-level/size/width"
        ).evaluate(this.levelDOM));

        this.heightSizeValue = Integer.parseInt(this.xpathBuilder.compile(
                "/bd-level/size/height"
        ).evaluate(this.levelDOM));
    }

    private void parseGridElement() throws XPathExpressionException {
        // Initialize the grid
        this.groundGrid = new DisplayableElementModel[this.widthSizeValue][this.heightSizeValue];

        // Populate the grid
        NodeList lineNode = (NodeList)this.xpathBuilder.compile(
                "/bd-level/grid[@state='initial']/line"
        ).evaluate(this.levelDOM, XPathConstants.NODESET);

        // Parse lines
        for(int y = 0; y < lineNode.getLength(); y++) {
            Node currentLineNode = lineNode.item(y);

            // Current line
            if(currentLineNode.getNodeType() == Node.ELEMENT_NODE) {
                Element currentLineElement = (Element)currentLineNode;
                int lineIndex = Integer.parseInt(currentLineElement.getAttribute("index"));

                NodeList rowNode = (NodeList)currentLineNode.getChildNodes();

                for(int x = 0; x < rowNode.getLength(); x++) {
                    Node currentRowNode = rowNode.item(x);

                    // Current row
                    if(currentRowNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element currentRowElement = (Element)currentRowNode;
                        int rowIndex = Integer.parseInt(currentRowElement.getAttribute("index"));

                        Element spriteElement = (Element)currentRowElement.getElementsByTagName("sprite").item(0);
                        String spriteName = spriteElement.getAttribute("name");

                        try {
                            this.groundGrid[rowIndex][lineIndex] = this.constructGridElement(spriteName, rowIndex, lineIndex);
                        } catch (UnknownSpriteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private DisplayableElementModel constructGridElement(String spriteName, int rowIndex, int lineIndex) throws UnknownSpriteException {
        DisplayableElementModel element;

        switch (spriteName) {
            case "amoeba":
                element = new AmoebaModel(rowIndex, lineIndex);
                break;

            case "black":
                element = new EmptyModel(rowIndex, lineIndex);
                break;

            case "boulder":
                element = new BoulderModel(rowIndex, lineIndex);
                break;

            case "brickwall":
                element = new BrickWallModel(rowIndex, lineIndex);
                break;

            case "butterfly":
                element = new ButterflyModel(rowIndex, lineIndex);
                break;

            case "diamond":
                element = new DiamondModel(rowIndex, lineIndex);
                break;

            case "dirt":
                element = new DirtModel(rowIndex, lineIndex);
                break;

            case "firefly":
                element = new FireflyModel(rowIndex, lineIndex);
                break;

            case "magicwall":
                element = new MagicWallModel(rowIndex, lineIndex);
                break;

            case "rockford":
                element = new RockfordModel(rowIndex, lineIndex);
                break;

            case "steelwall":
                element = new SteelWallModel(rowIndex, lineIndex);
                break;

            default:
                throw new UnknownSpriteException("Unknown sprite element");
        }

        return element;
    }

    public String getLevelId() {
        return this.levelId;
    }

    private void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}
