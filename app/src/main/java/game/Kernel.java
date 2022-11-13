package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.Graphic.Displayable;
import game.Graphic.GraphicEngine;
import game.Input.KeyHandler;
import game.Physic.Physical;
import game.Physic.PhysicalEngine;

public class Kernel implements Runnable{

    KeyHandler KeyH = new KeyHandler();
    Thread gameThread;
    int FPS = 30;

    int playerX = 100, playerY = 100, playerSpeed = 4;

    private static List<GameObject> gameObjects = new ArrayList<>();

    private static GraphicEngine graphicEngine = new GraphicEngine();
    private static PhysicalEngine physicalEngine = new PhysicalEngine();
    
    private GameObject player;

    /* 
     static ImageView i_mur_sud = new ImageView("wall.jpg");
     static GameObject mur_sud = new GameObject(
         new Physical(20, 650, 700, 50),
        new Displayable(i_mur_sud, 20, 650, 700, 50)
        );

    static ImageView i_mur_nord = new ImageView("wall.jpg");
    static GameObject mur_nord = new GameObject(
        new Physical(20, 0, 700, 50),
        new Displayable(i_mur_nord, 20, 0, 700, 50)
        );

    static ImageView i_mur_est = new ImageView("wall.jpg");
    static GameObject mur_est = new GameObject(
        new Physical(650, 20, 50, 700),
        new Displayable(i_mur_est, 650, 20, 50, 700)
        );

    static ImageView i_mur_ouest = new ImageView("wall.jpg");
    static GameObject mur_ouest = new GameObject(
        new Physical(0, 20, 50, 700),
        new Displayable(i_mur_ouest, 0, 20, 50, 700)
        );

    private static class Update implements Runnable {
        long delay = 20;

        @Override
        public void run() {
            while(true) {
                for(GameObject go : gameObjects) {
                    physicalEngine.compute(go.getComponent(Physical.class));
                    if (timeline == null) {
                        // Timeline sert au Thread créé par JavaFX, c'est un ensemble de tâches à effectuer (ou KeyFrames).
                        // Chaque KeyFrame a un délai au bout duquel il est soumis au Thread de JavaFX.
                        timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> graphicEngine.relocate(go.getComponent(Displayable.class), go.getComponent(Physical.class).getX(), go.getComponent(Physical.class).getY())));
                        timeline.setCycleCount(Animation.INDEFINITE);
                    }
                    timeline.play();
                    //System.out.println(go.getComponent(Physical.class).getX() + " " + go.getComponent(Physical.class).getY());
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    */

    public Kernel() throws Exception{

        graphicEngine.init();
        graphicEngine.addKeyListener(KeyH);

        player = new GameObject(new Physical(350-64, 350-32, 60, 64),
                                new Displayable(ImageIO.read(getClass().getResource("/pacman_idle.png")),
                                350-32, 350-32, 64, 64));

        graphicEngine.addDisplayable(player.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(player.getComponent(Physical.class));
        gameObjects.add(player);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        //graphicEngine.init(stage);
        
        // physicalEngine.addPhysicalObject(player.getComponent(Physical.class));
        // graphicEngine.addChildren(player.getComponent(Displayable.class));

        /* 
        physicalEngine.addPhysicalObject(mur_est.getComponent(Physical.class));
        graphicEngine.addChildren(mur_est.getComponent(Displayable.class));
        
        physicalEngine.addPhysicalObject(mur_sud.getComponent(Physical.class));
        graphicEngine.addChildren(mur_sud.getComponent(Displayable.class));
        
        physicalEngine.addPhysicalObject(mur_nord.getComponent(Physical.class));
        graphicEngine.addChildren(mur_nord.getComponent(Displayable.class));

        physicalEngine.addPhysicalObject(mur_ouest.getComponent(Physical.class));
        graphicEngine.addChildren(mur_ouest.getComponent(Displayable.class));
        gameObjects.add(mur_est);
        gameObjects.add(mur_nord);
        gameObjects.add(mur_ouest);
        gameObjects.add(mur_sud);
        */

        // player.getComponent(Physical.class).setSpeed(4);
        // gameObjects.add(player);

        double drawInternal = 1_000_000_000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInternal;

            lastTime = currentTime;

            if(delta >= 1){
                graphicEngine.repaint();
                delta--;
            }
        }
    }


    public void movePlayer(int direction) {
        player.getComponent(Physical.class).setDirection(direction);
    }

    // public GameObject getPlayer(){return player;}
    // public GraphicEngine getGraphicEngine(){return graphicEngine;}
}
