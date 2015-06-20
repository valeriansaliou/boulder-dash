package fr.enssat.BoulderDash.views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.enssat.BoulderDash.models.LevelModel;

public class InformationPanel extends JPanel implements Observer{
	private LevelModel levelModel;
	private JTextArea text;
	
	public InformationPanel(LevelModel levelModel) {
		this.levelModel = levelModel;
		this.text = new JTextArea();
		this.levelModel.getGameInformationsModel().addObserver(this);
		this.text.setText("Score : " + levelModel.getGameInformationsModel().getScore() + 
				"\nRemaining diamonds : " + levelModel.getGameInformationsModel().getRemainingsDiamonds());
		this.add(this.text);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.text.setText("Score : " + levelModel.getGameInformationsModel().getScore() + 
				"\nRemaining diamonds : " + levelModel.getGameInformationsModel().getRemainingsDiamonds());
	}
}
