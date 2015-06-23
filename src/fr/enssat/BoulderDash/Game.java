package fr.enssat.BoulderDash;

import com.apple.eawt.Application;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.controllers.LevelEditorController;
import fr.enssat.BoulderDash.controllers.NavigationBetweenViewController;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.helpers.AudioLoadHelper;

import javax.swing.*;

import java.awt.*;


/**
 * Game
 *
 * Spawns the game.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class Game {
    /**
     * Class constructor
     *
     * @param  args  Command-line arguments
     */
    public static void main(String[] args) {
        //setAppleUI(); // Not working

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NavigationBetweenViewController navigation = new NavigationBetweenViewController();
            }
        });
    }

    /**
     * Sets Apple User Interface elements (look and feel)
     */
    public static void setAppleUI() {
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Boulder Dash");

            Image appIcon = Toolkit.getDefaultToolkit().getImage("res/app/app_icon.png");
            Application.getApplication().setDockIconImage(appIcon);

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        } catch (InstantiationException e) {
            System.out.println("InstantiationException: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException: " + e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException: " + e.getMessage());
        }
    }
}
