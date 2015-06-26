package fr.enssat.BoulderDash.helpers;

import fr.enssat.BoulderDash.exceptions.UnknownModelException;

import fr.enssat.BoulderDash.helpers.ModelConvertHelper;
import fr.enssat.BoulderDash.models.ExpandingWallModel;
import fr.enssat.BoulderDash.models.RockfordModel;
import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.EmptyModel;
import fr.enssat.BoulderDash.models.BrickWallModel;
import fr.enssat.BoulderDash.models.BoulderModel;
import fr.enssat.BoulderDash.models.DiamondModel;
import fr.enssat.BoulderDash.models.DirtModel;
import fr.enssat.BoulderDash.models.MagicWallModel;
import fr.enssat.BoulderDash.models.SteelWallModel;

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
 * LevelLoadHelper
 *
 * Proceeds level load routine
 * Able to deserialize level data from storage, and format it to
 * internal representation To be used as a data factory from level
 * model classes
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class LevelLoadHelper {
	private static String pathToDataStore = "./res/levels";
	private String levelId = null;
	private Document levelDOM;
	private XPath xpathBuilder;
    private SimpleDateFormat dateFormatter;

	// Parsed values
	private String nameValue = null;
	private Date dateCreatedValue = null;
	private Date dateModifiedValue = null;

	private int widthSizeValue = 0;
	private int heightSizeValue = 0;
	private int limitsWidth = 2;
	private int limitsHeight = 2;
	private int limitsOffsetWidth = 1;
	private int limitsOffsetHeight = 1;

	private RockfordModel rockfordInstance;
	private int rockfordPositionX = 0;
	private int rockfordPositionY = 0;
	
	private int diamondsToCatch;

	private DisplayableElementModel[][] groundGrid;

    /**
     * Class constructor
     *
     * @param  levelId  Level identifier
     */
	public LevelLoadHelper(String levelId) {
		this.setLevelId(levelId);
		this.diamondsToCatch = 0;

        // Requirements
        this.dateFormatter = new SimpleDateFormat("yyy-MM-dd/HH:mm:ss", Locale.ENGLISH);

		if (this.levelId != null) {
			// Let's go.
			this.loadLevelData();
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
     * Loads the level data into instance data space
     */
	private void loadLevelData() {
		this.xpathBuilder = XPathFactory.newInstance().newXPath();

		String pathToData = this.getLevelPathInDataStore();

		// Parse & process level data
		this.parseLevelData(pathToData);
		this.processLevelData();
	}

    /**
     * Parses the level data for the given file
     * Handles the task of reading and storing the parsed DOM
     *
     * @param  pathToLevelData  FS path to the level data
     */
	private void parseLevelData(String pathToLevelData) {
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

    /**
     * Processes the parsed level data
     */
	private void processLevelData() {
		// Parse elements from structure
		try {
			this.processNameElement();
			this.processDateElement();
			this.processSizeElement();
			this.processGridElement();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

    /**
     * Processes the 'name' element
     */
	private void processNameElement() throws XPathExpressionException {
		// Returns level name value
		this.nameValue = this.xpathBuilder.compile("/bd-level/name").evaluate(this.levelDOM);
	}

    /**
     * Processes the 'date' element
     */
	private void processDateElement() throws XPathExpressionException, ParseException {
		// Returns level creation date value
		this.dateCreatedValue = this.dateFormatter.parse(xpathBuilder.compile("/bd-level/date[@format='utc']/created").evaluate(this.levelDOM));

		// Returns level modification date value
		this.dateModifiedValue = this.dateFormatter.parse(this.xpathBuilder.compile("/bd-level/date[@format='utc']/modified").evaluate(this.levelDOM));
	}

    /**
     * Processes the 'size' element
     */
	private void processSizeElement() throws XPathExpressionException {
		// Returns level width value
		this.widthSizeValue = Integer.parseInt(this.xpathBuilder.compile("/bd-level/size/width").evaluate(this.levelDOM));
		this.widthSizeValue += this.limitsWidth;

		// Returns level height value
		this.heightSizeValue = Integer.parseInt(this.xpathBuilder.compile("/bd-level/size/height").evaluate(this.levelDOM));
		this.heightSizeValue += this.limitsHeight;
	}

    /**
     * Processes the 'grid' element
     */
	private void processGridElement() throws XPathExpressionException {
		// Initialize the grid
		this.groundGrid = new DisplayableElementModel[this.widthSizeValue][this.heightSizeValue];

		// Populate the grid
		NodeList lineNode = (NodeList) this.xpathBuilder.compile("/bd-level/grid[@state='initial']/line").evaluate(this.levelDOM, XPathConstants.NODESET);

		// Parse lines
		for (int y = 0; y < lineNode.getLength(); y++) {
			Node currentLineNode = lineNode.item(y);

			// Current line
			if (currentLineNode.getNodeType() == Node.ELEMENT_NODE) {
				Element currentLineElement = (Element) currentLineNode;
				int lineIndex = Integer.parseInt(currentLineElement.getAttribute("index"));

				NodeList rowNode = (NodeList) currentLineNode.getChildNodes();

				for (int x = 0; x < rowNode.getLength(); x++) {
					Node currentRowNode = rowNode.item(x);

					// Current row
					if (currentRowNode.getNodeType() == Node.ELEMENT_NODE) {
						Element currentRowElement = (Element) currentRowNode;
						int rowIndex = Integer.parseInt(currentRowElement.getAttribute("index"));

						NodeList spriteNode = currentRowElement.getElementsByTagName("sprite");

						if (spriteNode.getLength() > 0) {
							Node currentSpriteNode = spriteNode.item(0);

							if (currentSpriteNode.getNodeType() == Node.ELEMENT_NODE) {
								Element currentSpriteElement = (Element) currentSpriteNode;
								String currentSpriteName = currentSpriteElement.getAttribute("name");
                                String currentSpriteConvertibleValue = currentSpriteElement.getAttribute("convertible");
                                boolean currentSpriteConvertible = false;

                                // No name? Continue.
                                if(currentSpriteName == null || currentSpriteName.isEmpty()) {
                                    continue;
                                }

                                if(currentSpriteConvertibleValue.equals("1")) {
									currentSpriteConvertible = true;
                                }

								// Process positions
								int pX = rowIndex + this.limitsOffsetWidth;
								int pY = lineIndex + this.limitsOffsetHeight;

								try {
									this.groundGrid[pX][pY] = this.constructGridElement(currentSpriteName, pX, pY, currentSpriteConvertible);
								} catch (UnknownModelException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}

    /**
     * Constructs the grid element
     *
     * @param  spriteName  Sprite name
     * @param  rowIndex    Position in row (horizontal axis)
     * @param  lineIndex   Position in line (vertical axis)
     */
	private DisplayableElementModel constructGridElement(String spriteName, int rowIndex, int lineIndex, boolean convertible) throws UnknownModelException {
        ModelConvertHelper modelConvert = new ModelConvertHelper();
        DisplayableElementModel element = modelConvert.toModel(spriteName, convertible);

		// Custom actions?
		switch (spriteName) {
            case "diamond":
                diamondsToCatch += 1;
                break;

            case "rockford":
                this.setRockfordPositionX(rowIndex);
                this.setRockfordPositionY(lineIndex);
                this.setRockfordInstance((RockfordModel) element);
                break;
		}

		return element;
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
     * Gets the name value
     *
     * @return  Name value
     */
	public String getNameValue() {
		return this.nameValue;
	}

    /**
     * Sets the name value
     *
     * @param  nameValue  Name value
     */
	private void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}

    /**
     * Gets the creation date value
     *
     * @return  Creation date value
     */
	public Date getDateCreatedValue() {
		return this.dateCreatedValue;
	}

    /**
     * Sets the creation date value
     *
     * @param  dateCreatedValue  Creation date value
     */
	private void setDateCreatedValue(Date dateCreatedValue) {
		this.dateCreatedValue = dateCreatedValue;
	}

    /**
     * Gets the modified date value
     *
     * @return  Modified date value
     */
	public Date getDateModifiedValue() {
		return this.dateModifiedValue;
	}

    /**
     * Sets the modified date value
     *
     * @param  dateModifiedValue  Modified date value
     */
	private void setDateModifiedValue(Date dateModifiedValue) {
		this.dateModifiedValue = dateModifiedValue;
	}

    /**
     * Gets the width size value
     *
     * @return  Width size value
     */
	public int getWidthSizeValue() {
		return this.widthSizeValue;
	}

    /**
     * Sets the width size value
     *
     * @param  widthSizeValue  Width size value
     */
	private void setWidthSizeValue(int widthSizeValue) {
		this.widthSizeValue = widthSizeValue;
	}

    /**
     * Gets the height size value
     *
     * @return  Height size value
     */
	public int getHeightSizeValue() {
		return this.heightSizeValue;
	}

    /**
     * Sets the eight size value
     *
     * @param  heightSizeValue  Height size value
     */
	private void setHeightSizeValue(int heightSizeValue) {
		this.heightSizeValue = heightSizeValue;
	}

    /**
     * Gets the horizontal position of the Rockford element
     *
     * @return  Horizontal position of the Rockford element
     */
	public int getRockfordPositionX() {
		return this.rockfordPositionX;
	}

    /**
     * Sets the horizontal position of the Rockford element
     *
     * @param  x  Horizontal position of the Rockford element
     */
	public void setRockfordPositionX(int x) {
		this.rockfordPositionX = x;
	}

    /**
     * Gets the vertical position of the Rockford element
     *
     * @return  Vertical position of the Rockford element
     */
	public int getRockfordPositionY() {
		return this.rockfordPositionY;
	}

    /**
     * Sets the vertical position of the Rockford element
     *
     * @param  y  Vertical position of the Rockford element
     */
	public void setRockfordPositionY(int y) {
		this.rockfordPositionY = y;
	}

    /**
     * Gets the instance of Rockford
     *
     * @return  Rockford instance
     */
	public RockfordModel getRockfordInstance() {
		return this.rockfordInstance;
	}

    /**
     * Sets the instance of Rockford
     *
     * @param  rockfordInstance  Rockford instance
     */
	public void setRockfordInstance(RockfordModel rockfordInstance) {
		this.rockfordInstance = rockfordInstance;
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

	/**
	 * Gets the number of Diamonds to catch 
	 * @return number of Diamonds to catch
	 */
	public int getDiamondsToCatch() {
		return diamondsToCatch;
	}

	/** 
	 * Sets the number of Diamonds to catch
	 * @param diamondsToCatch
	 */
	public void setDiamondsToCatch(int diamondsToCatch) {
		this.diamondsToCatch = diamondsToCatch;
	}
	
	
}
