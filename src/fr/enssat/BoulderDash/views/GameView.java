package fr.enssat.BoulderDash.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//appelé par gameControler
public class GameView extends JFrame {

    public GameView() {
        this.initializeView();
    }

    private void initializeView() {
        setTitle("Boulder Dash");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.createLayout();
    }

    
    //definir le plateau
    //partir sur un affichage statique
    //...et des différents objets
    //controleur déplace le bonhomme
    //animations
    
    
    private void createLayout() {
        Container pane = getContentPane();
        GridLayout gridLayout = new GridLayout(1, 2);
        pane.setLayout(gridLayout);

        JPanel gameArea = new JPanel();
        JPanel infoArea = new JPanel();

        pane.add(gameArea);
        pane.add(infoArea);
    }
}