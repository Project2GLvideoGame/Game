package engine.sound;


import javax.sound.sampled.*;

import engine.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Soundable extends Component  {

    HashMap<String,Clip> clips = new HashMap<>();

    public Soundable(Track...tracks) {
        for(Track track: tracks){
            addTrack(track);
        }
    }

    public void start(String name){
        clips.get(name).start();
    }

    public void stop(String name){
        clips.get(name).stop();
    }

    public void loop(String name, int time){
        clips.get(name).loop(time);
    }
    private void addTrack(Track track){
        clips.put(track.getName(), track.getClip());
    }
    
    
}
