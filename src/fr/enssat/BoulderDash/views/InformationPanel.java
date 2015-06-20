package fr.enssat.BoulderDash.views;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.enssat.BoulderDash.models.LevelModel;

public class InformationPanel extends JPanel{
	private LevelModel levelModel;
	private JTextArea text;
	
	public InformationPanel(LevelModel levelModel) {
		this.levelModel = levelModel;
		this.text = new JTextArea();
		this.text.setText("Score : " + levelModel.getScore() + "\nRemaining diamonds : " + levelModel.getRemainingDiamondsNumber());
		this.add(this.text);
	}
	
	public void updateText() {
		this.text.setText("Score : " + this.levelModel.getScore() + "\nRemaining diamonds : " + this.levelModel.getRemainingDiamondsNumber());
		//FIXME
	}
}
