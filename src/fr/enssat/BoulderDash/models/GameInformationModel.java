package fr.enssat.BoulderDash.models;

import java.util.Observable;

/**
 * GameInformationModel will contain all the data which will
 * go to the InformationPanel.
 * 
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 * 
 */
public class GameInformationModel extends Observable  {
	private int score;
	private int remainingsDiamonds;
	private int timer;

	public GameInformationModel(int remainingsDiamonds) {
		this.score = 0;
		this.remainingsDiamonds = remainingsDiamonds;
		this.timer = 0;
	}

	/**
	 * Returns the actual score
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Returns the actual number of remaining diamonds
	 * @return remainingDiamonds
	 */
	public int getRemainingsDiamonds() {
		return remainingsDiamonds;
	}

	/**
	 * Sets the number of remainingDiamonds
	 * @param remainingDiamonds
	 */
	public void setRemainingsDiamonds(int remainingDiamonds) {
		this.remainingsDiamonds = remainingDiamonds;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	/**
	 * Increment the score & notify observers
	 */
	public void incrementScore() {
		this.score += 1;	
		this.myNotify();
	}
	
	/**
	 * Generic function which will notify the observers.
	 */
	private void myNotify(){
		this.notifyObservers();
		this.setChanged();
	}

	/**
	 * Decrement of one the number total of remainings diamonds.
	 */
	public void decrementRemainingsDiamonds() {
		this.remainingsDiamonds -= 1;
		this.myNotify();
	}

}
