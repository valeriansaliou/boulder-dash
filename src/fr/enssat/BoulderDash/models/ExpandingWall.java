package fr.enssat.BoulderDash.models;

public class ExpandingWall extends ElementDisplayable{
	
	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = true;
	private static boolean canMove = false;
	private static int priority = 10;
	private static boolean impactExplosive = false;
	private static boolean animate = true;
	
	public ExpandingWall(int x, int y){
		super(isDestructible,canMove,x,y,pathToSprite,priority,impactExplosive,animate);
	}
	
}
