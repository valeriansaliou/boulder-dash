package fr.enssat.BoulderDash.models;

public class BrickWall extends ElementDiplayable{
	private int x;
	private int y;
	private String pathToSprite;
	private static boolean isDestructible = true;
	private static boolean canMove = false;
	
	public BrickWall(int x, int y){
		super(isDestructible,canMove);
		this.x = x;
		this.y = y;
		setPathToSprite("insert/path/down/here");
	}

	private void setPathToSprite(String path) {
		pathToSprite = 	path;
	}
	
	public String getPathToSprite(){
		return pathToSprite;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
