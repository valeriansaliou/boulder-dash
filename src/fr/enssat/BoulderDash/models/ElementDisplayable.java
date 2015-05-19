package fr.enssat.BoulderDash.models;

public abstract class ElementDisplayable {
	private boolean isDestructible;
	private boolean isMoving;
	private String pathToSprite;
	private int x;
	private int y;
	private int priority;
	
	public ElementDisplayable(boolean isDestructible, boolean isMoving, int x, int y, String pathToSprite, int priority) {
		this.isMoving = isMoving;
		this.isDestructible = isDestructible;
		this.pathToSprite = pathToSprite;
		this.x = x;
		this.y = y;
		this.setPriority(priority);
	}

	public boolean isDestructible() {
		return isDestructible;
	}

	public boolean isMoving() {
		return isMoving;
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
