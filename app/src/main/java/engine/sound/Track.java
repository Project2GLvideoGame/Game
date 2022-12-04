package engine.sound;

import javax.sound.sampled.*;


public class Track {
    private String name;
    private Clip clip;

    /**
     * Initialisation des pistes à partir d'un chemin
     * @param name
     * Le nom de la piste
     * @param clipPath
     * Le chemin de la piste
     */
    public Track(String name, String clipPath) {

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(clipPath));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Clip getClip() {
        return clip;
    }
}
