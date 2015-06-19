package fr.enssat.boulderdash.bridges;

import javazoom.jl.player.advanced.*;

/**
 * SoundJLayerBridge
 *
 * Sound bridge to the JLayer library
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class SoundJLayerBridge extends PlaybackListener implements Runnable {
    private String filePath;
    private AdvancedPlayer player;
    private Thread playerThread;

    public SoundJLayerBridge(String filePath) {
        this.filePath = filePath;
    }

    public void play() {
        try {
            String urlAsString = "file:///"
                    + new java.io.File(".").getCanonicalPath()
                    + "/"
                    + this.filePath;

            this.player = new AdvancedPlayer(
                    new java.net.URL(urlAsString).openStream(),
                    javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice()
            );

            this.player.setPlayBackListener(this);

            this.playerThread = new Thread(this, "AudioPlayerThread");
            this.playerThread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        try {
            this.playerThread.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playbackStarted(PlaybackEvent playbackEvent) {
        System.out.println("playbackStarted()");
    }

    public void playbackFinished(PlaybackEvent playbackEvent) {
        System.out.println("playbackEnded()");
    }

    public void run() {
        try {
            this.player.play();
        } catch (javazoom.jl.decoder.JavaLayerException ex) {
            ex.printStackTrace();
        }
    }
}