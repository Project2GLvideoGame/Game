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

/**
 * Élément central de notre moteur de jeu, le Kernel est l'instance par laquelle tout transite, mais aussi
 * le seul moyen d'articuler les moteurs via le Gameplay. Il utilise pour cela le patron de conception Singleton.
 */
public class Kernel implements Runnable  {

    private static Kernel instance = null;
    private static Game   game;
    public GraphicEngine graphicEngine;
    public PhysicEngine physicEngine;
    private SoundEngine   soundEngine;
    private AIEngine      aiEngine;
    private InputEngine   inputEngine;
    public  EventsManager eventsManager;
    public boolean gameOver = false;
    private int score = 0;


    /**
     * Permet d'initialiser un Kernel en créant tous les moteurs associés et en les abonnant aux bons
     * événements.
     */
    public Kernel() {

        this.eventsManager  = new EventsManager();

        game           = new Game(eventsManager);

        this.graphicEngine  = new GraphicEngine(eventsManager);
        this.physicEngine = new PhysicEngine(eventsManager);
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
        this.eventsManager.subscribe(soundEngine,SoundEvent.class);



    }

    /**
     * Méthode statique qui représente le moyen de créer une instance du Kernel. Conformément au patron de
     * conception Singleton, on vérifie ici s'il n'existe pas déjà une instance du Kernel avant d'initialiser
     * le Kernel avec son constructeur et de le lancer sur un thread.
     */
    public static synchronized void start() {
        if (instance == null){
            instance = new Kernel();
            game.init();
            new Thread(instance).start();
        }
    }

    /**
     * Correspond à la boucle "infinie" du Kernel lui permettant notamment de rafraîchir ses moteurs à l'aide
     * de la méthode update() de chacun d'entre eux. La boucle tourne tant que le jeu n'est pas fini (tant que
     * l'attribut gameOver n'est pas faux).
     */
    @Override
    public void run() {
        while(!gameOver) {

            if(!Frequences.shouldRefresh()) continue;
            
            physicEngine.update();
            inputEngine.update();
            aiEngine.update();
            soundEngine.update();
            SwingUtilities.invokeLater(()->graphicEngine.update());
            game.update();
        }
    }





    public int getScore(){
        return this.score;
    }

    public void addToScore(int points){
        this.score += points;
    }


    /**
     * Permet de récupérer l'instance en cours du Kernel
     * @return L'instance en cours du Kernel si déjà créée, ou null sinon
     */
    public static Kernel getInstance() {
        return instance;
    }


    /**
     * Permet de renvoyer la hauteur de l'écran.
     * @return La hauteur de l'écran
     */
    public int getScreenHeight() {
        return graphicEngine.getScreenHeight();
    }

    /**
     * Permet de renvoyer la largeur de l'écran.
     * @return La largeur de l'écran
     */
    public int getScreenWidth() {
        return graphicEngine.getScreenWidth();
    }


    /**
     * Représente le seul moyen d'ajouter un GameObject. La méthode parcourt chacun des Component du GameObject
     * et ajoute le Component aux moteurs concernés. Par exemple, si on crée un GameObject avec pour Component
     * Displayable et Physic, on ajoutera le Component Physic à la liste du moteur physique et
     * le Component Displayable à la liste du moteur graphique.
     * @param gameObject
     */
    public void addGameObject(GameObject gameObject) {
        for (int i = 0; i < gameObject.getComponents().size(); i++) {
            Component component = gameObject.getComponents().get(i);
            if(component instanceof Displayable) graphicEngine.addDisplayable((Displayable)component);
            if(component instanceof Physic)      physicEngine.addPhysicalObject((Physic)component);
            if(component instanceof Soundable){   soundEngine.addSoundableObject((Soundable)component);}
            if(component instanceof Intelligent) aiEngine.addIAObjectIntelligent((Intelligent)component);
        }
    }

    /**
     * Représente le seul moyen de retirer un GameObject. La méthode parcourt chacun des Component du GameObject
     * à supprimer et supprime chaque Component des moteurs concernés de la même façon que pour la méthode
     * addGameObject. Enfin, on soumet un événement à notre EventManager pour alerter les moteurs concernés
     * de la suppression de ce GameObject. Typiquement, le moteur graphique l'utilisera pour le supprimer de
     * l'affichage.
     * @param gameObject Le GameObject à supprimer
     */
    public void removeGameObject(GameObject gameObject) {
        for (int i = 0; i < gameObject.getComponents().size(); i++) {
            Component component = gameObject.getComponents().get(i);
            if(component instanceof Displayable) graphicEngine.removeDisplayable((Displayable)component);
            if(component instanceof Physic)      physicEngine.removePhysicalObject((Physic)component);
            if(component instanceof Soundable)   soundEngine.removeSoundableObject((Soundable)component);
            if(component instanceof Intelligent) aiEngine.removeIAObjectIntelligent((Intelligent)component);
        }
        eventsManager.submit(new DestroyEvent(gameObject) );
        System.out.println("on destroy " + gameObject.getClass() );
    }
}
