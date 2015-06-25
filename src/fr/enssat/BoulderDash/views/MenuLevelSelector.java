package fr.enssat.BoulderDash.views;

import javax.swing.*;
import java.awt.event.ActionEvent;

import fr.enssat.BoulderDash.views.LevelEditorView;


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
    private LevelEditorView levelEditorView = null;

    /**
     * Class constructor
     */
    public MenuLevelSelector(String[] items) {
        super(items);
    }

    public MenuLevelSelector(String[] items, LevelEditorView levelEditorView) {
        this(items);
        this.levelEditorView = levelEditorView;
    }

    /**
     * Called when an action is performed
     *
     * @param  e  Action event
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox comboBoxSource = (JComboBox) e.getSource();
        this.choiceValue = (String) comboBoxSource.getSelectedItem();

        if(this.levelEditorView != null) {
            this.levelEditorView.menuLevelSelectorChanged(this);
        }
    }

    /**
     * Gets the choice value
     *
     * @return  Choice value
     */
    public String getChoiceValue() {
        return this.choiceValue;
    }

    /**
     * Selects a given value
     *
     * @param  value  Value to be selected
     */
    public void setSelectedValue(String value) {
        for (int i = 0; i < this.getItemCount(); i++) {
            if (this.getItemAt(i).toString().equals(value)) {
                this.setSelectedIndex(i);
                break;
            }
        }
    }

    /**
     * Sets the choice value
     *
     * @param  choiceValue  Choice value
     */
    public void setChoiceValue(String choiceValue) {
        this.choiceValue = choiceValue;
    }
}
