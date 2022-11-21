package game.Sound;

import game.Component;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Soundable extends Component  {

    Clip clip;
    //HashMap<String,URL> urls = new HashMap<>();
    List<URL> urls = new ArrayList<>();


    public Soundable(String...paths) {
        for(String path: paths)
         addUrl(path);
    }

    public void start(){
        clip.start();
    }

    public void setFile(URL url) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);

    }
    public void stop(){
        clip.stop();
    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    private void addUrl(String path){
        urls.add(getClass().getResource(path));
    }
    
    
}
