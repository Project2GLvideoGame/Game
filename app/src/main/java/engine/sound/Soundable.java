package engine.sound;


import javax.sound.sampled.*;

import engine.Component;

import java.util.HashMap;

public class Soundable extends Component  {


    HashMap<String,Clip> clips = new HashMap<>();

    public Soundable(Track...tracks) {
        for(Track track: tracks){
            addTrack(track);
        }
    }
    public HashMap<String, Clip> getClips() {
        return clips;
    }
    public void playSoundtrack(String name){
        clips.get(name).start();
        loop(name, Clip.LOOP_CONTINUOUSLY);
    }
    
    public void playSoundEffect(String name){
        clips.get(name).loop(0);

    }

    public void stop(String name){
        clips.get(name).stop();
    }

    public void loop(String name, int time){
        clips.get(name).loop(time);
        clips.get(name).setMicrosecondPosition(0);
    }
    private void addTrack(Track track){
        clips.put(track.getName(), track.getClip());
    }

    public void stopAllMusic(){
        for (String key : clips.keySet()) {
            clips.get(key).stop();
            
        }
    }
    
    
}
