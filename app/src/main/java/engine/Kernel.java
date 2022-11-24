package engine;

import java.util.ArrayList;
import java.util.List;

import engine.event.EventsManager;
import engine.graphic.Displayable;
import engine.graphic.GraphicEngine;
import engine.input.InputEngine;
import engine.input.State;
import engine.physics.Physic;
import engine.physics.PhysicEngine;

public class Kernel implements Runnable{

    private Thread gameThread;

    private static List<GameObject> gameObjects = new ArrayList<>();

    private GraphicEngine graphicEngine;
    private PhysicEngine physicalEngine;
    private InputEngine inputEngine;

    private EventsManager eventManager;
    
    private GameObject player;


    public Kernel() throws Exception{
        eventManager = new EventsManager();
        graphicEngine = new GraphicEngine();
        physicalEngine = new PhysicEngine(eventManager);
        inputEngine = new InputEngine();

        inputEngine.setKernel(this);
        graphicEngine.addKeyListener(inputEngine);
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
                System.out.println(physic.getX() + " " + physic.getY());
            }

            physicalEngine.update();
            graphicEngine.repaint();
                
        }
    }


    public void movePlayer(int direction) {
        player.getComponent(Physic.class).setDirection(direction);
    }

    public GameObject getPlayer(){return player;}
    public GraphicEngine getGraphicEngine(){return graphicEngine;}

    public void changeState(State state) {
        inputEngine.changeState(state);
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
        for (Component component : gameObject.getComponents()) {
            
        }
    }

    private void addDisplayable(Displayable displayable){
        graphicEngine.addDisplayable(displayable);
    }
    private void addPhysic(Physic physic){
        physicalEngine.addComponent(physic);
    }
}
