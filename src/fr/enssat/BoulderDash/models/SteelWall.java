package fr.enssat.BoulderDash.models;

public class SteelWall extends ElementDiplayable{
	private static String pathToSprite = "insert/path/down/here";;
	private static boolean isDestructible = false;
	private static boolean canMove = false;
	private static int priority = 8;
	
	public SteelWall(int x, int y){
		super(isDestructible,canMove,x,y,pathToSprite,priority);
	}
}
