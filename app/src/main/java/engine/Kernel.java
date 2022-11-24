package engine;

import java.util.ArrayList;
import java.util.List;

import engine.ai.AIAlgoEnnemis;
import engine.ai.AIEngine;
import engine.ai.Intelligent;
import engine.ai.InvisibleWall;
import engine.ai.Player;
import engine.ai.Ennemies.Crab;
import engine.event.CollisionEvent;
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

    private AIEngine aiEngine;
    private GraphicEngine graphicEngine;
    private PhysicEngine physicalEngine;
    private InputEngine inputEngine;

    private EventsManager eventManager;
    
    private Player player;


    public Kernel() throws Exception{
        eventManager = new EventsManager();
        graphicEngine = new GraphicEngine();
        aiEngine = new AIEngine(eventManager);
        physicalEngine = new PhysicEngine(eventManager);
        inputEngine = new InputEngine();

        inputEngine.setKernel(this);
        graphicEngine.addKeyListener(inputEngine);
        eventManager.subscribe(aiEngine, CollisionEvent.class);

        //Gameplay work

        // GameObject wall = new GameObject(new Physic(350, 300, 50, 150),
        // new Displayable(350, 300, 50, 150, "/wall.jpg"));
        // //Wall
        // graphicEngine.addDisplayable(wall.getComponent(Displayable.class));
        // physicalEngine.addPhysicalObject(wall.getComponent(Physic.class));
        // TODO mettre dans le gameplay remove les displayables                                 


        initializeInvisibleWall();
        initializeEnnemis();
        // TODO FIN                                                 

        // gameObjects.add(wall);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void initializePlayer(){
        //Player
        player = new Player(new Physic(100, 100, 60, 64),
        new Displayable(100, 100, 64, 64, 6, "/player/pacman_run1.png", "/player/pacman_run2.png", "/player/pacman_run3.png", "/player/pacman_run4.png"));

        graphicEngine.addDisplayable(player.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(player.getComponent(Physic.class));
        player.getComponent(Physic.class).setSpeed(3);

        gameObjects.add(player);

    }
    private void initializeEnnemis(){
        for (int i = 0; i < 50; i++) {
            int nb = ((i/10)%5) ;
            List<String> png = new ArrayList<>(List.of("3","2","2","1","1"));;

            Crab crab = new Crab(new Physic(100 +50* ((i % 10)) ,50 +50 *((i/10))  , 45, 45),new Displayable(100 +50* ((i % 10)) ,50 +50 *( (i/10))  , 45, 45, "/alien_"+png.get(nb)+".png"),new Intelligent(new AIAlgoEnnemis()));
            gameObjects.add(crab);
            graphicEngine.addDisplayable(crab.getComponent(Displayable.class));
            physicalEngine.addPhysicalObject(crab.getComponent(Physic.class));
            aiEngine.addIAObjectIntelligent(crab.getComponent(Intelligent.class));
        }

    }

    private void initializeInvisibleWall(){
        InvisibleWall wallLeft = new InvisibleWall(new Physic(-50, 0, 50, graphicEngine.getScreenHeight()),new Displayable(-50, 0, 50, graphicEngine.getScreenHeight(), "/wall.jpg"));
       
        InvisibleWall wallRight = new InvisibleWall(new Physic(graphicEngine.getScreenWidth(), 0, 50, graphicEngine.getScreenHeight()), new Displayable(graphicEngine.getScreenWidth(), 0, 50, graphicEngine.getScreenHeight(), "/wall.jpg"));
        //Wall
        graphicEngine.addDisplayable(wallLeft.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(wallLeft.getComponent(Physic.class));

        //Wall
        graphicEngine.addDisplayable(wallRight.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(wallRight.getComponent(Physic.class));

        gameObjects.add(wallLeft);
        gameObjects.add(wallRight);

        InvisibleWall wallTop = new InvisibleWall(new Physic(0, -50, graphicEngine.getScreenWidth(), 50),
                                new Displayable(0, -50, graphicEngine.getScreenWidth(), 50, "/wall.jpg"));
       
        InvisibleWall wallBottom = new InvisibleWall(new Physic(0,  graphicEngine.getScreenHeight(), graphicEngine.getScreenWidth(), 50),
        new Displayable(0,  graphicEngine.getScreenHeight(), graphicEngine.getScreenWidth(), 50, "/wall.jpg"));
        
        //Wall
        graphicEngine.addDisplayable(wallTop.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(wallTop.getComponent(Physic.class));

        //Wall
        graphicEngine.addDisplayable(wallBottom.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(wallBottom.getComponent(Physic.class));

        gameObjects.add(wallTop);
        gameObjects.add(wallBottom);
    }

    @Override
    public void run(){
        while(gameThread != null){
            
            if(!GraphicEngine.refreshFrequences()) continue;

            for(GameObject go : gameObjects){
                Physic physic = go.getComponent(Physic.class);
                Displayable disp = go.getComponent(Displayable.class);
                graphicEngine.setPosition(disp, (int)physic.getX(), (int)physic.getY());
            }
            physicalEngine.update();
            graphicEngine.repaint();
            aiEngine.update();

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

}
