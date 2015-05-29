package fr.enssat.BoulderDash.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LevelEditorView extends JFrame {

    public LevelEditorView() {
        this.initializeView();
    }

    private void initializeView() {
        setTitle("Level editor | Boulder Dash");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.createLayout();
    }

    private void createLayout() {
        Container pane = getContentPane();
        GridLayout gridLayout = new GridLayout(1, 2);
        pane.setLayout(gridLayout);

        JPanel fieldArea = new JPanel();
        JPanel selectArea = new JPanel();

        pane.add(fieldArea);
        pane.add(selectArea);
    }
}