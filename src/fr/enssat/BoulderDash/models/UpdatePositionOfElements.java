package fr.enssat.BoulderDash.models;

public class UpdatePositionOfElements implements Runnable {
	private LevelModel levelModel;
	private Thread elementMovingThread;
	
	public UpdatePositionOfElements(LevelModel levelModel){
		this.levelModel = levelModel;
		this.elementMovingThread = new Thread();
		this.elementMovingThread.start();
	}
	/**
	 * Move Rockford inside the model
	 * 
	 * @param posX
	 * @param posY
	 */
	public void setPositionOfRockford(int posX, int posY) {
		int oldX = this.levelModel.getRockfordPositionX();
		int oldY = this.levelModel.getRockfordPositionY();

		if (this.levelModel.getGroundLevelModel()[posX][posY].getSpriteName() == "diamond") {
			this.levelModel.incrementScore();
		}
		// Check that we are not out of bound ...
		if (posX > 0 && posY > 0 
				&& posX < this.levelModel.getLevelLoadHelper().getHeightSizeValue()
				&& posY < this.levelModel.getLevelLoadHelper().getWidthSizeValue()) {
			// Create a new empty model in the old pos of Rockford
			this.levelModel.getGroundLevelModel()[oldX][oldY] = new EmptyModel();
			
			// Save the x / y pos of Rockford in the levelModel only
			this.levelModel.updateRockfordPosition(posX,posY);
			
			this.levelModel.getGroundLevelModel()[posX][posY] = this.levelModel.getRockford();
		}
	}
	
	/**
	 * Thread to move the elements one by one and slowly ... TODO
	 */
	public void run() {
		
	}
}
