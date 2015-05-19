package fr.enssat.BoulderDash.models;

public class Dirt extends ElementDiplayable{
	
	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = true;
	private static boolean canMove = false;
	private static int priority = 1;
	
	public Dirt(int x, int y){
		super(isDestructible,canMove,x,y,pathToSprite,priority);
	}
}
