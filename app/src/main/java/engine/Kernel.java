package engine;

import java.util.ArrayList;
import java.util.List;

import engine.event.EventsManager;
import engine.graphic.Displayable;
import engine.graphic.GraphicEngine;
import engine.input.InputEngine;
import engine.physics.Physic;
import engine.physics.PhysicEngine;
import engine.sound.SoundEngine;
import engine.sound.Soundable;

public class Kernel implements Runnable{

    private Thread gameThread;

    private static List<GameObject> gameObjects = new ArrayList<>();

    private GraphicEngine graphicEngine;
    private PhysicEngine physicalEngine;
    private SoundEngine soundEngine;
    
    private EventsManager eventManager;
    

    public Kernel(){
        eventManager = new EventsManager();
        graphicEngine = new GraphicEngine();
        physicalEngine = new PhysicEngine(eventManager);
        soundEngine = new SoundEngine(eventManager);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        while(gameThread != null){
            
            if(!GraphicEngine.refreshFrequences()) continue;

            for(GameObject go : gameObjects){
                Physic physic = go.getComponent(Physic.class);
                Displayable disp = go.getComponent(Displayable.class);
                graphicEngine.setPosition(disp, (int)physic.getX(), (int)physic.getY());
                //System.out.println(physic.getX() + " " + physic.getY());
            }

            physicalEngine.update();
            graphicEngine.repaint();
                
        }
    }

    public GraphicEngine getGraphicEngine(){return graphicEngine;}

    public void addKeyListener(InputEngine keyL){graphicEngine.addKeyListener(keyL);}
    
    public SoundEngine getSoundEngine() {
        return soundEngine;
    }
    
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
        for (Component component : gameObject.getComponents()) {
            if(component instanceof Displayable) graphicEngine.addDisplayable((Displayable)component);
            if(component instanceof Physic) physicalEngine.addComponent((Physic)component);
            if(component instanceof Soundable) soundEngine.addSoundableObject((Soundable)component);
        }
    }

}
