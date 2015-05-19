package fr.enssat.BoulderDash.models;

public class BrickWall extends ElementDiplayable{

	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = true;
	private static boolean canMove = false;
	private static int priority = 10;
	
	public BrickWall(int x, int y){
		super(isDestructible,canMove,x,y,pathToSprite,priority);
	}
}
