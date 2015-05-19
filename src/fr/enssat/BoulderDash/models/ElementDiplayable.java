package fr.enssat.BoulderDash.models;

public abstract class ElementDiplayable {
	private boolean isDestructible;
	private boolean isMoving;
	
	public ElementDiplayable(boolean isDestructible, boolean canMove) {
		this.setDestructible(isDestructible);
		this.setMoving(canMove);
	}

	public boolean isDestructible() {
		return isDestructible;
	}

	private void setDestructible(boolean isDestructible) {
		this.isDestructible = isDestructible;
	}

	public boolean isMoving() {
		return isMoving;
	}

	private void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

}
