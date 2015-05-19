package fr.enssat.BoulderDash.models;

public abstract class ElementDiplayable {
	private boolean isDestructible;
	private boolean isDisplayedOnScreen;
	private boolean canMove;
	
	public ElementDiplayable(boolean isDestructible, boolean isDisplayedOnScreen, boolean canMove) {
		this.setDestructible(isDestructible);
		this.isDisplayedOnScreen = isDisplayedOnScreen;
		this.canMove = canMove;
	}

	public boolean isDestructible() {
		return isDestructible;
	}

	public void setDestructible(boolean isDestructible) {
		this.isDestructible = isDestructible;
	}

	public boolean isDisplayedOnScreen() {
		return isDisplayedOnScreen;
	}

	public void setDisplayedOnScreen(boolean isDisplayedOnScreen) {
		this.isDisplayedOnScreen = isDisplayedOnScreen;
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	
	
}
