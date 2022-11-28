package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.event.*;
import engine.graphic.Displayable;
import engine.graphic.GraphicEngine;
import engine.input.InputEngine;
import engine.input.State;
import engine.physics.Physic;
import engine.physics.PhysicEngine;
import engine.sound.SoundEngine;
import engine.sound.Soundable;
import game.Game;
import game.ai.AIEngine;
import game.ai.Intelligent;

public class Kernel implements Runnable  {

    private static Kernel instance = null;
    public static synchronized void start(Game game) {
        if (instance == null) instance = new Kernel(game);
    }
    public static Kernel getInstance() {
        return instance;
    }

    private Thread gameThread;
    private static List<GameObject> gameObjects = new ArrayList<>();

    // Engines
    private GraphicEngine graphicEngine;
    private PhysicEngine physicalEngine;
    private SoundEngine soundEngine;
    private AIEngine aiEngine;
    private InputEngine inputEngine;
    public EventsManager eventsManager;

    private Game game;

    public Kernel(Game game) {
        this.eventsManager = new EventsManager();
        this.game = game;
        this.graphicEngine = new GraphicEngine(eventsManager);
        this.physicalEngine = new PhysicEngine(eventsManager);
        this.soundEngine = new SoundEngine(eventsManager);
        this.aiEngine = new AIEngine(eventsManager);
        this.inputEngine = new InputEngine(game, eventsManager);

        this.eventsManager.subscribe(aiEngine, CollisionEvent.class);
        this.eventsManager.subscribe(inputEngine, StateEvent.class);
        this.graphicEngine.getScene().addKeyListener(inputEngine);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {

            for(GameObject go : gameObjects) {
                Physic physic = go.getComponent(Physic.class);
                Displayable displayable = go.getComponent(Displayable.class);
                if(displayable != null && physic != null)
                    graphicEngine.setPosition(displayable, (int)physic.getX(), (int)physic.getY());
                //System.out.println(go.getClass() + " " + physic.getX() + " " + physic.getY());
            }
            // Engine Update
            physicalEngine.update();
            aiEngine.update();
            game.update();
            inputEngine.update();
            if(!graphicEngine.refreshFrequences()) continue;
            graphicEngine.getScene().repaint();

        }
    }

    public GraphicEngine getGraphicEngine() {
        return graphicEngine;
    }

    public SoundEngine getSoundEngine() {
        return soundEngine;
    }

    public int getScreenHeight() {
        return graphicEngine.getScreenHeight();

    }

    public int getScreenWidth() {
        return graphicEngine.getScreenWidth();
    }

    public void addGameObject(GameObject gameObject) {
        for (int i = 0; i < gameObject.getComponents().size(); i++) {
            Component component = gameObject.getComponents().get(i);
            if(component instanceof Displayable) graphicEngine.addDisplayable((Displayable)component);
            if(component instanceof Physic) physicalEngine.addPhysicalObject((Physic)component);
            if(component instanceof Soundable) soundEngine.addSoundableObject((Soundable)component);
            if(component instanceof Intelligent) aiEngine.addIAObjectIntelligent((Intelligent)component);
        }
        gameObjects.add(gameObject);
    }
}
