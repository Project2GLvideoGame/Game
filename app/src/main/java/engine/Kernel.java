package engine;

import java.util.ArrayList;
import java.util.List;

import engine.event.CollisionEvent;
import engine.event.EventsManager;
import engine.graphic.Displayable;
import engine.graphic.GraphicEngine;
import engine.input.InputEngine;
import engine.physics.Physic;
import engine.physics.PhysicEngine;
import engine.sound.SoundEngine;
import engine.sound.Soundable;
import game.ai.AIEngine;
import game.ai.Intelligent;

public class Kernel implements Runnable{

    private Thread gameThread;

    private static List<GameObject> gameObjects = new ArrayList<>();

    //Engines
    private GraphicEngine graphicEngine;
    private PhysicEngine physicalEngine;
    private SoundEngine soundEngine;
    private AIEngine aiEngine;
    
    private EventsManager eventManager;
    

    public Kernel(){
        eventManager = new EventsManager();
        graphicEngine = new GraphicEngine();
        physicalEngine = new PhysicEngine(eventManager);
        soundEngine = new SoundEngine(eventManager);
        aiEngine = new AIEngine(eventManager);

        eventManager.subscribe(aiEngine, CollisionEvent.class);
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
                if(disp != null && physic != null)
                    graphicEngine.setPosition(disp, (int)physic.getX(), (int)physic.getY());
                //System.out.println(go.getClass() + " " + physic.getX() + " " + physic.getY());
            }

            physicalEngine.update();
            graphicEngine.repaint();
            //aiEngine.update();
        }
    }

    public GraphicEngine getGraphicEngine(){return graphicEngine;}

    public void addKeyListener(InputEngine keyL){graphicEngine.addKeyListener(keyL);}
    
    public SoundEngine getSoundEngine() {
        return soundEngine;
    }

    public int getScreenHeight(){return graphicEngine.getScreenHeight();}
    public int getScreenWidth(){return graphicEngine.getScreenWidth();}
    
    public void addGameObject(GameObject gameObject) {
        for (int i = 0; i < gameObject.getComponents().size(); i++) {
            Component component = gameObject.getComponents().get(i);
            if(component instanceof Displayable) graphicEngine.addDisplayable((Displayable)component);
            if(component instanceof Physic) physicalEngine.addComponent((Physic)component);
            if(component instanceof Soundable) soundEngine.addSoundableObject((Soundable)component);
            if(component instanceof Intelligent) aiEngine.addIAObjectIntelligent((Intelligent)component);
        }
        gameObjects.add(gameObject);
    }

}
