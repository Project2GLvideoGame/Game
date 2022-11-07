package game;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import game.Graphic.*;
import game.Physic.*;
import game.Input.*;

public class Kernel extends Application {

    private static Timeline timeline;
    private static List<GameObject> gameObjects = new ArrayList<>();
    private static GraphicEngine graphicEngine = new GraphicEngine();
    private static PhysicalEngine physicalEngine = new PhysicalEngine();
    private InputEngine inputEngine = new InputEngine(this);
    private GameObject player = new GameObject(new Physical(350-64, 350-32, 60, 64), new Displayable(new ImageView("pacman_run.gif"), 350-32, 350-32, 64, 64));

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

    public void update() {
        Thread thread = new Thread(new Update());
        thread.start();
    }

    @Override
    public void start(Stage stage) throws Exception {
        graphicEngine.init(stage);

        Scene scene = graphicEngine.getScene();

        scene.addEventFilter(KeyEvent.KEY_PRESSED ,inputEngine.eventHandlerPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED ,inputEngine.eventHandlerReleased);
        
        physicalEngine.addPhysicalObject(player.getComponent(Physical.class));
        graphicEngine.addChildren(player.getComponent(Displayable.class));

        physicalEngine.addPhysicalObject(mur_est.getComponent(Physical.class));
        graphicEngine.addChildren(mur_est.getComponent(Displayable.class));

        physicalEngine.addPhysicalObject(mur_sud.getComponent(Physical.class));
        graphicEngine.addChildren(mur_sud.getComponent(Displayable.class));

        physicalEngine.addPhysicalObject(mur_nord.getComponent(Physical.class));
        graphicEngine.addChildren(mur_nord.getComponent(Displayable.class));

        physicalEngine.addPhysicalObject(mur_ouest.getComponent(Physical.class));
        graphicEngine.addChildren(mur_ouest.getComponent(Displayable.class));

        player.getComponent(Physical.class).setSpeed(2);
        
        gameObjects.add(player);
        gameObjects.add(mur_est);
        gameObjects.add(mur_nord);
        gameObjects.add(mur_ouest);
        gameObjects.add(mur_sud);
        
        update();
    }

    public void movePlayer(int direction) {
        player.getComponent(Physical.class).setDirection(direction);
    }

    public GameObject getPlayer(){return player;}
    public GraphicEngine getGraphicEngine(){return graphicEngine;}
}
