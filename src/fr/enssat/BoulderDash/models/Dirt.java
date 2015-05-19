package fr.enssat.BoulderDash.models;

public class Dirt extends ElementDisplayable{
	
	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = true;
	private static boolean canMove = false;
	private static int priority = 3;
	private static boolean impactExplosive = false;
	private static boolean animate = false;
	
	public Dirt(int x, int y){
		super(isDestructible,canMove,x,y,pathToSprite,priority,impactExplosive,animate);
	}
}
