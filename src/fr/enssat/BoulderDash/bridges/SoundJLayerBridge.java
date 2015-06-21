package fr.enssat.BoulderDash.bridges;

import java.net.URL;

import javazoom.jl.player.advanced.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.FactoryRegistry;

/**
 * SoundJLayerBridge
 *
 * Sound bridge to the JLayer library.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class SoundJLayerBridge extends PlaybackListener implements Runnable {
    private String filePath;
    private AdvancedPlayer player;
    private Thread playerThread;

    /**
     * Class constructor
     *
     * @param  filePath  File path to sound file
     */
    public SoundJLayerBridge(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Play the target sound
     */
    public void play() {
        try {
            String urlAsString = "file:///"
                               + new java.io.File(".").getCanonicalPath()
                               + "/"
                               + this.filePath;

            this.player = new AdvancedPlayer(
                    new URL(urlAsString).openStream(),
                    FactoryRegistry.systemRegistry().createAudioDevice()
            );

            this.player.setPlayBackListener(this);

            this.playerThread = new Thread(this, "AudioPlayerThread");
            this.playerThread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Stops the target sound
     */
    public void stop() {
        try {
            this.playerThread.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Runs the player thread
     */
    public void run() {
        try {
            this.player.play();
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
    }
}