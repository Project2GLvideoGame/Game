package game;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game.Graphic.Displayable;
import game.Graphic.GraphicEngine;
import game.Input.KeyHandler;
import game.Physic.Physical;
import game.Physic.PhysicalEngine;

public class Kernel implements Runnable{

    private KeyHandler KeyH = new KeyHandler();
    private Thread gameThread;

    private static List<GameObject> gameObjects = new ArrayList<>();

    private static GraphicEngine graphicEngine;
    private static PhysicalEngine physicalEngine;
    
    private GameObject player;


    public Kernel(GraphicEngine graphicEngine, PhysicalEngine physicalEngine) throws Exception{

        Kernel.graphicEngine = graphicEngine;
        Kernel.physicalEngine = physicalEngine;

        KeyH.setKernel(this);
        graphicEngine.addKeyListener(KeyH);

        //Gameplay work
        player = new GameObject(new Physical(100, 100, 60, 64),
                                new Displayable(ImageIO.read(getClass().getResource("/pacman_idle.png")),
                                100, 100, 64, 64));
        GameObject wall = new GameObject(new Physical(350, 300, 50, 150),
                                new Displayable(ImageIO.read(getClass().getResource("/wall.jpg")),
                                350, 300, 50, 150));

        //Player
        graphicEngine.addDisplayable(player.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(player.getComponent(Physical.class));
        player.getComponent(Physical.class).setSpeed(3);

        //Wall
        graphicEngine.addDisplayable(wall.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(wall.getComponent(Physical.class));

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
                Physical physic = go.getComponent(Physical.class);
                Displayable disp = go.getComponent(Displayable.class);
                graphicEngine.setPosition(disp, (int)physic.getX(), (int)physic.getY());
                System.out.println(physic.getX() + " " + physic.getY());
            }

            physicalEngine.computeAll();
            graphicEngine.repaint();
                
        }
    }


    public void movePlayer(int direction) {
        player.getComponent(Physical.class).setDirection(direction);
    }

    // public GameObject getPlayer(){return player;}
    // public GraphicEngine getGraphicEngine(){return graphicEngine;}
}
