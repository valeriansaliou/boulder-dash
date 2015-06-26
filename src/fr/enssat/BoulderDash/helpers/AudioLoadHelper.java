package fr.enssat.BoulderDash.helpers;

import fr.enssat.BoulderDash.bridges.SoundJLayerBridge;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

/**
 * AudioLoadHelper
 *
 * Manages audio
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class AudioLoadHelper {
    private static String pathToAudioStore = "./res/audio";

    private SoundJLayerBridge musicToPlay;
    private HashMap<String, SoundJLayerBridge> preloadedSounds;

    /**
     * Class constructor
     */
    public AudioLoadHelper() {
        this.preloadSounds();
    }

    /**
     * Gets music storage path
     *
     * @param   musicId  Music identifier
     * @return  Music path, with file extension
     */
    private String getMusicPathInAudioStore(String musicId) {
        return AudioLoadHelper.pathToAudioStore + "/music/" + musicId + ".mp3";
    }

    /**
     * Starts game music
     *
     * @param  musicId  Music identifier
     */
    public void startMusic(String musicId) {
        if(this.musicToPlay != null) {
            this.stopMusic();
        }

        this.musicToPlay = new SoundJLayerBridge(
                this.getMusicPathInAudioStore(musicId)
        );
        this.musicToPlay.play();
    }

    /**
     * Stops game music
     */
    public void stopMusic() {
        this.musicToPlay.stop();
    }

    /**
     * Preloads available sounds
     */
    private void preloadSounds() {
        // Initialize
        String curSoundIdPrep;
        this.preloadedSounds = new HashMap<String, SoundJLayerBridge>();

        // List sound files
        File soundsDir = new File(AudioLoadHelper.pathToAudioStore + "/sounds/");
        File [] soundFiles = soundsDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".mp3");
            }
        });

        // Cache them all!
        for (File curSoundId : soundFiles) {
            curSoundIdPrep = curSoundId.getName();
            curSoundIdPrep = curSoundIdPrep.substring(0, curSoundIdPrep.lastIndexOf('.'));

            this.preloadedSounds.put(curSoundIdPrep, new SoundJLayerBridge(
                    curSoundId.getPath()
            ));
        }
    }

    /**
     * Gets a preloaded sound
     *
     * @param   soundId  Sound identifier
     * @return  Preloaded sound instance
     */
    private SoundJLayerBridge getPreloadedSound(String soundId) {
        return this.preloadedSounds.get(soundId);
    }

    /**
     * Plays a sound
     *
     * @param  soundId  Sound identifier
     */
    public void playSound(String soundId) {
        this.getPreloadedSound(soundId).play();
    }
}
