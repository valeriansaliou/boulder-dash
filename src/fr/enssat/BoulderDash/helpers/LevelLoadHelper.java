package fr.enssat.BoulderDash.helpers;

import fr.enssat.BoulderDash.models.BoulderModel;
import fr.enssat.BoulderDash.models.BrickWallModel;
import fr.enssat.BoulderDash.models.DiamondModel;
import fr.enssat.BoulderDash.models.DirtModel;
import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.EmptyModel;
import fr.enssat.BoulderDash.models.MagicWallModel;
import fr.enssat.BoulderDash.models.RockfordModel;
import fr.enssat.BoulderDash.models.SteelWallModel;
import fr.enssat.BoulderDash.exceptions.UnknownSpriteException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Proceeds level load routine Able to deserialize level data from storage, and
 * format it to internal representation To be used as a data factory from level
 * model classes
 *
 * @author Valérian Saliou <vsaliou@enssat.fr>
 * @version 1.0
 * @since 2015-06-19
 */

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
	private int limitsWidth = 2;
	private int limitsHeight = 2;
	private int limitsOffsetWidth = 1;
	private int limitsOffsetHeight = 1;

	private RockfordModel rockfordInstance;
	private int rockfordPositionX = 0;
	private int rockfordPositionY = 0;

	private ArrayList<DiamondModel> diamondList;
	private ArrayList<MagicWallModel> magicWallList;

	private DisplayableElementModel[][] groundGrid;

	public LevelLoadHelper(String levelId) {
		this.setLevelId(levelId);
		
		this.diamondList = new ArrayList<DiamondModel>();
		this.magicWallList = new ArrayList<MagicWallModel>();
		
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

		// Parse & process level data
		this.parseLevelData(pathToData);
		this.processLevelData();
	}

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
		// Returns level name value
		this.nameValue = this.xpathBuilder.compile("/bd-level/name").evaluate(this.levelDOM);
	}

	private void parseDateElement() throws XPathExpressionException, ParseException {
		// Returns level creation date value
		this.dateCreatedValue = dateFormatter.parse(xpathBuilder.compile("/bd-level/date[@format='utc']/created").evaluate(this.levelDOM));

		// Returns level modification date value
		this.dateModifiedValue = dateFormatter.parse(this.xpathBuilder.compile("/bd-level/date[@format='utc']/modified").evaluate(this.levelDOM));
	}

	private void parseSizeElement() throws XPathExpressionException {
		// Returns level width value
		this.widthSizeValue = Integer.parseInt(this.xpathBuilder.compile("/bd-level/size/width").evaluate(this.levelDOM));
		this.widthSizeValue += this.limitsWidth;

		// Returns level height value
		this.heightSizeValue = Integer.parseInt(this.xpathBuilder.compile("/bd-level/size/height").evaluate(this.levelDOM));
		this.heightSizeValue += this.limitsHeight;
	}

	private void parseGridElement() throws XPathExpressionException {
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

								// Process positions
								int pX = rowIndex + this.limitsOffsetWidth;
								int pY = lineIndex + this.limitsOffsetHeight;

								try {
									this.groundGrid[pX][pY] = this.constructGridElement(currentSpriteName, pX, pY);
								} catch (UnknownSpriteException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}

	private DisplayableElementModel constructGridElement(String spriteName, int rowIndex, int lineIndex) throws UnknownSpriteException {
		DisplayableElementModel element;

		// Instanciates the sprite element matching the given sprite name
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

			this.setDiamondList((DiamondModel) element);

			break;

		case "dirt":
			element = new DirtModel(rowIndex, lineIndex);
			break;

		case "firefly":
			element = new FireflyModel(rowIndex, lineIndex);
			break;

		case "magicwall":
			element = new MagicWallModel(rowIndex, lineIndex);
			
			this.setMagicWallList((MagicWallModel) element);
			
			break;

		case "rockford":
			element = new RockfordModel(rowIndex, lineIndex);

			this.setRockfordPositionX(rowIndex);
			this.setRockfordPositionY(lineIndex);
			this.setRockfordInstance((RockfordModel) element);

			break;

		case "steelwall":
			element = new SteelWallModel(rowIndex, lineIndex);
			break;

		default:
			throw new UnknownSpriteException("Unknown sprite element");
		}

		return element;
	}

	private void setMagicWallList(MagicWallModel magicWallItem) {
		this.magicWallList.add(magicWallItem);
	}

	public String getLevelId() {
		return this.levelId;
	}

	private void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getNameValue() {
		return this.nameValue;
	}

	private void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}

	public Date getDateCreatedValue() {
		return this.dateCreatedValue;
	}

	private void setDateCreatedValue(Date dateCreatedValue) {
		this.dateCreatedValue = dateCreatedValue;
	}

	public Date getDateModifiedValue() {
		return this.dateModifiedValue;
	}

	private void setDateModifiedValue(Date dateModifiedValue) {
		this.dateModifiedValue = dateModifiedValue;
	}

	public int getWidthSizeValue() {
		return this.widthSizeValue;
	}

	private void setWidthSizeValue(int widthSizeValue) {
		this.widthSizeValue = widthSizeValue;
	}

	public int getHeightSizeValue() {
		return this.heightSizeValue;
	}

	private void setHeightSizeValue(int heightSizeValue) {
		this.heightSizeValue = heightSizeValue;
	}

	public int getRockfordPositionX() {
		return this.rockfordPositionX;
	}

	public void setRockfordPositionX(int x) {
		this.rockfordPositionX = x;
	}

	public int getRockfordPositionY() {
		return this.rockfordPositionY;
	}

	public void setRockfordPositionY(int y) {
		this.rockfordPositionY = y;
	}

	public RockfordModel getRockfordInstance() {
		return this.rockfordInstance;
	}

	public void setRockfordInstance(RockfordModel rockfordInstance) {
		this.rockfordInstance = rockfordInstance;
	}

	public ArrayList<DiamondModel> getDiamondList() {
		return this.diamondList;
	}

	public void setDiamondList(DiamondModel diamondItem) {
		this.diamondList.add(diamondItem);
	}

	public DisplayableElementModel[][] getGroundGrid() {
		return this.groundGrid;
	}

	private void setGroundGrid(DisplayableElementModel[][] groundGrid) {
		this.groundGrid = groundGrid;
	}

	public ArrayList<MagicWallModel> getMagicWallsList() {
		return magicWallList;
	}
}
