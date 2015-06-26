package fr.enssat.BoulderDash.views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class HelpView extends JFrame{
	
	/**
	 * Generate the HelpView
	 */
	public HelpView(){
		this.initializeView();
		this.createLayout();
	}

    /**
     * Initializes the view
     */
    private void initializeView() {
        this.setVisible(true);
        this.setResizable(false);

        // UI parameters
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 560, 150);

        // App parameters
        this.setTitle("Boulder Dash | Help");
    }

    /**
     * Creates the view layout
     */
    private void createLayout() {    	
    	JTextArea help = new JTextArea();
    	help.setEditable(false);
    	help.setText("To use the editor, you should :\n"
    			+ "- Select an item on the list,\n"
    			+ "- Move the RED cursur with the arrows\n"
    			+ "- To place the selected item on the field, use SPACEBAR.\n"
    			+ "If you want to lock the placement of the things, hit shift once (to unlock, rehit shift)\n"
    			+ "Then, you can save & load your creation on game.\n"
    			+ "You have to place at least 3 diamonds and 1 rockford!\n"
    			+ "Have fun ;-)");
    	
        this.add(help, BorderLayout.CENTER);
    }
}
