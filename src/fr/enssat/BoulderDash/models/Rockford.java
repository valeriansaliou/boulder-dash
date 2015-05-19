package fr.enssat.BoulderDash.models;

public class Rockford extends ElementDisplayable {
	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = true;
	private static boolean canMove = true;
	private static int priority = 8;
	
	public Rockford(int x, int y){
		super(isDestructible,canMove,x,y,pathToSprite,priority);
	}
}
