package fr.enssat.BoulderDash.models;

public class BrickWall {
	private int x;
	private int y;
	private boolean isDestructible;
	private boolean isDisplayedOnScreen;
	private boolean canMoove;
	
	public BrickWall(int x, int y){
		this.x = x;
		this.y = y;
		this.isDestructible = true;
		this.isDisplayedOnScreen = true;
		this.canMoove = false;
	}
}
