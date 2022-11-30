package engine;

import javax.swing.SwingUtilities;
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

public class Kernel implements Runnable  {

    private static Kernel instance = null;
    private static Game   game;
    public GraphicEngine graphicEngine;
    private PhysicEngine  physicalEngine;
    private SoundEngine   soundEngine;
    private AIEngine      aiEngine;
    private InputEngine   inputEngine;
    public  EventsManager eventsManager;
    public boolean gameOver = false;
    
    
    public Kernel() {

        this.eventsManager  = new EventsManager();

        game           = new Game(eventsManager);

        this.graphicEngine  = new GraphicEngine(eventsManager);
        this.physicalEngine = new PhysicEngine(eventsManager);
        this.soundEngine    = new SoundEngine(eventsManager);
        this.aiEngine       = new AIEngine(eventsManager);
        this.inputEngine    = new InputEngine(game, eventsManager);

        this.graphicEngine.getScene().addKeyListener(inputEngine);
        
        this.eventsManager.subscribe(aiEngine, CollisionEvent.class);
        this.eventsManager.subscribe(game, CollisionEvent.class);
        this.eventsManager.subscribe(game, DestroyEvent.class);
        this.eventsManager.subscribe(inputEngine, StateEvent.class);
        this.eventsManager.subscribe(graphicEngine, StateEvent.class);
        this.eventsManager.subscribe(graphicEngine, MoveEvent.class);
    }

    public static synchronized void start() {
        if (instance == null){
            instance = new Kernel();
            game.init();
            new Thread(instance).start();
        }
    }

    @Override
    public void run() {
        while(!gameOver) {

            if(!Frequences.shouldRefresh()) continue;
            
            physicalEngine.update();
            inputEngine.update();
            aiEngine.update();
            SwingUtilities.invokeLater(()->graphicEngine.update());
            game.update();
        }
    }


    
    public static Kernel getInstance() {
        return instance;
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
            if(component instanceof Physic)      physicalEngine.addPhysicalObject((Physic)component);
            if(component instanceof Soundable)   soundEngine.addSoundableObject((Soundable)component);
            if(component instanceof Intelligent) aiEngine.addIAObjectIntelligent((Intelligent)component);
        }
    }

    public void removeGameObject(GameObject gameObject) {
        for (int i = 0; i < gameObject.getComponents().size(); i++) {
            Component component = gameObject.getComponents().get(i);
            if(component instanceof Displayable) graphicEngine.removeDisplayable((Displayable)component);
            if(component instanceof Physic)      physicalEngine.removePhysicalObject((Physic)component);
            if(component instanceof Soundable)   soundEngine.removeSoundableObject((Soundable)component);
            if(component instanceof Intelligent) aiEngine.removeIAObjectIntelligent((Intelligent)component);
        }
        eventsManager.submit(new DestroyEvent(gameObject) );
        System.out.println("on destroy "+gameObject.getClass() );
    }



}
