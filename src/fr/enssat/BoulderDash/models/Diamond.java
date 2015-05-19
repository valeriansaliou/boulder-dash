package fr.enssat.BoulderDash.models;

public class Diamond extends ElementDisplayable{

	private static String pathToSprite = "insert/path/down/here";
	private static boolean isDestructible = true;
	private static boolean canMove = false;
	private static int priority = 1;
	private static boolean impactExplosive = false;
	private static boolean animate = true;
	
	public Diamond(int x, int y){
		super(isDestructible,canMove,x,y,pathToSprite,priority,impactExplosive,animate);
	}
	
}
