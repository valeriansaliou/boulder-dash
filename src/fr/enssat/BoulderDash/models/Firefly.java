package fr.enssat.BoulderDash.models;

public class Firefly extends ElementDisplayable{
	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = true;
	private static boolean canMove = true;
	private static int priority = 2;
	private static boolean impactExplosive = true;
	private static boolean animate = true;
	
	public Firefly(int x, int y){
		super(isDestructible,canMove,x,y,pathToSprite,priority,impactExplosive,animate);
	}
}
