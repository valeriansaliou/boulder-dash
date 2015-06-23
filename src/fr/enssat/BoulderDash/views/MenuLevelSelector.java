package fr.enssat.boulderdash.views;

import javax.swing.*;
import java.awt.event.ActionEvent;


/**
 * MenuLevelSelector
 *
 * Specifies the menu level selector
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-23
 */
public class MenuLevelSelector extends JComboBox {
    private String choiceValue;

    /**
     * Class constructor
     */
    public MenuLevelSelector(String[] items) {
        super(items);
    }

    /**
     * Called when an action is performed
     *
     * @param  e  Action event
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox comboBoxSource = (JComboBox) e.getSource();
        this.choiceValue = (String) comboBoxSource.getSelectedItem();
    }

    /**
     * Gets the choice value
     *
     * @return  Choice value
     */
    public String getChoiceValue() {
        return this.choiceValue;
    }
}
