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
<<<<<<< HEAD
		this.text.setText("Score : " + levelModel.getGameInformationsModel().getScore() + 
				"\nRemaining diamonds : " + levelModel.getGameInformationsModel().getRemainingsDiamonds());
=======
		this.text.setText("Score : " + this.levelModel.getGameInformationsModel().getScore() +
				"\nRemaining diamonds : " + this.levelModel.getGameInformationsModel().getRemainingsDiamonds());
>>>>>>> 58ed28a217960a0c8c3c2f7094eb7e42d4f08e1a
	}
}
