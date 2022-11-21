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
import engine.sound.SoundEngine;
import engine.sound.Soundable;
import engine.sound.Track;

public class Kernel implements Runnable{

    private Thread gameThread;

    private static List<GameObject> gameObjects = new ArrayList<>();

    private GraphicEngine graphicEngine;
    private PhysicEngine physicalEngine;
    private InputEngine inputEngine;
    private SoundEngine soundEngine;

    private EventsManager eventManager;
    
    private GameObject player;


    public Kernel() throws Exception{
        eventManager = new EventsManager();
        graphicEngine = new GraphicEngine();
        physicalEngine = new PhysicEngine(eventManager);
        inputEngine = new InputEngine();
        soundEngine = new SoundEngine(eventManager);

        inputEngine.setKernel(this);
        graphicEngine.addKeyListener(inputEngine);

        //Gameplay work
        player = new GameObject(new Physic(100, 100, 60, 64),
                                new Displayable(100, 100, 64, 64, 6, "/player/pacman_run1.png", "/player/pacman_run2.png", "/player/pacman_run3.png", "/player/pacman_run4.png"),
                                new Soundable(new Track("move", "/audio/pacman.wav")) );
        
        GameObject wall = new GameObject(new Physic(350, 300, 50, 150),
                                new Displayable(350, 300, 50, 150, "/wall.jpg"));

        //Player
        graphicEngine.addDisplayable(player.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(player.getComponent(Physic.class));
        player.getComponent(Physic.class).setSpeed(3);
        soundEngine.addSoundableObject(player.getComponent(Soundable.class));
        soundEngine.play();

        //Wall
        graphicEngine.addDisplayable(wall.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(wall.getComponent(Physic.class));

        gameObjects.add(player);
        gameObjects.add(wall);
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


    public void movePlayer(int direction) {
        player.getComponent(Physic.class).setDirection(direction);
    }

    public GameObject getPlayer(){return player;}
    public GraphicEngine getGraphicEngine(){return graphicEngine;}

    public void changeState(State state) {
        inputEngine.changeState(state);
    }

    public SoundEngine getSoundEngine() {
        return soundEngine;
    }
}
