package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import engine.input.State;
import engine.event.*;
import engine.graphic.Displayable;
import engine.graphic.GraphicEngine;
import engine.input.InputEngine;
import engine.physics.Physic;
import engine.physics.PhysicEngine;
import engine.sound.SoundEngine;
import engine.sound.Soundable;
import game.Game;
import game.ai.AIEngine;
import game.ai.Intelligent;
import game.entity.Player;

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
        this.eventsManager.subscribe(graphicEngine, MoveEvent.class);
        this.graphicEngine.getScene().addKeyListener(inputEngine);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {

            if(!GraphicEngine.refreshFrequences()) continue;
            
            physicalEngine.update();
            aiEngine.update();
            //game.update();
            inputEngine.update();
            SwingUtilities.invokeLater(()->graphicEngine.update());
        }
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
        //gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        for (int i = 0; i < gameObject.getComponents().size(); i++) {
            Component component = gameObject.getComponents().get(i);
            if(component instanceof Displayable) graphicEngine.removeDisplayable((Displayable)component);
            if(component instanceof Physic) physicalEngine.removePhysicalObject((Physic)component);
            if(component instanceof Soundable) soundEngine.removeSoundableObject((Soundable)component);
            if(component instanceof Intelligent) aiEngine.removeIAObjectIntelligent((Intelligent)component);
        }
        //gameObjects.add(gameObject);
    }



}
