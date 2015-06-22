package fr.enssat.BoulderDash.views;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;


/**
 * AssetsLevelEditorComponent
 *
 * Information panel element.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-22
 */
public class AssetsLevelEditorComponent extends JPanel implements ActionListener {
    static List<String> choiceList = Arrays.asList(
            "Boulder", "Diamond", "Dirt", "Brick Wall", "Expanding Wall", "Magic Wall", "Steel Wall", "Rockford"
    );

    public AssetsLevelEditorComponent() {
        super(new BorderLayout());

        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));

        String curListChoice;

        for(int i = 0; i < choiceList.size(); i++) {
            curListChoice = choiceList.get(i);

            // Create radio buttons from list
            JRadioButton curButton = new JRadioButton(curListChoice);
            //boulderButton.setMnemonic(KeyEvent.VK_A);
            curButton.setActionCommand(curListChoice);

            // Group the radio buttons
            buttonGroup.add(curButton);

            // Register a listener for the radio buttons
            curButton.addActionListener(this);

            // Put the radio buttons in a column in a panel
            radioPanel.add(curButton);
        }

        this.add(radioPanel, BorderLayout.LINE_START);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    /** Listens to the radio buttons. */
    public void actionPerformed(ActionEvent e) {
        // TODO; e.getActionCommand()
    }
}