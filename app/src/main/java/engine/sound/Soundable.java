package engine.sound;


import javax.sound.sampled.*;

import engine.Component;

import java.util.HashMap;

public class Soundable extends Component  {


    HashMap<String,Clip> clips = new HashMap<>();

    public Soundable(Track...tracks) {
        for (int i = 0; i < tracks.length; i++) {
            Track track = tracks[i];
            addTrack(track);
        }
    }
    public HashMap<String, Clip> getClips() {
        return clips;
    }
    /**
     * @param name
     * Le nom de la piste
     */
    public void playSoundtrack(String name){
        clips.get(name).start();
        loop(name, Clip.LOOP_CONTINUOUSLY);
    }
    
    /**
     * Lancer l'effets sonore
     * @param name
     * Le nom de la piste
     */
    public void playSoundEffect(String name){
        clips.get(name).loop(0);

    }
    /**
     * Arreter la piste
     * @param name
     * Le nom de la piste
     */
    public void stop(String name){
        clips.get(name).stop();
    }

    /**
     * Boucler sur la piste
     * @param name
     * Le nom de la piste
     * @param time
     * Nombre de fois qu'on boucle sur la piste
     */
    public void loop(String name, int time){
        clips.get(name).loop(time);
        clips.get(name).setMicrosecondPosition(0);
    }
    /** Ajouter une piste à la Map clips
     * @param track
     */
    private void addTrack(Track track){
        clips.put(track.getName(), track.getClip());
    }
    /**
     * Arreter toute la musique du GameObject
     */
    public void stopAllMusic(){
        for (String key : clips.keySet()) {
            clips.get(key).stop();
            
        }
    }
    
    
}
