package game;

import java.util.ArrayList;
import java.util.List;

import game.Graphic.Displayable;
import game.Graphic.GraphicEngine;
import game.Input.InputEngine;
import game.Physic.Physical;
import game.Physic.PhysicalEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Kernel extends Application implements Runnable{

    List<GameObject> gameObjects = new ArrayList<>();
    GameObject player = new GameObject(new Physical(0, 350-32, 64, 64), new Displayable(new ImageView("pacman_run.gif"), 350-32, 350-32, 64, 64));
    GraphicEngine graphicEngine = new GraphicEngine();
    PhysicalEngine physicalEngine = new PhysicalEngine();

    @Override
    public void start(Stage stage) throws Exception {
        graphicEngine.init(stage);
        
        InputEngine inputEngine = new InputEngine();
        Scene scene = graphicEngine.getScene();

        scene.setOnKeyPressed(inputEngine.eventHandlerPressed);
        scene.setOnKeyReleased(inputEngine.eventHandlerReleased);
        Thread thread = new Thread(this);

        physicalEngine.addPhysicalObject(player.getComponent(Physical.class));
        graphicEngine.addChildren(player.getComponent(Displayable.class));
        player.getComponent(Physical.class).setSpeed(1);
        player.getComponent(Physical.class).setDirection(270);

        gameObjects.add(player);

        thread.start();
    }
    
    @Override
    public void run() {
        while(true){
            
            for(GameObject go : gameObjects){
                physicalEngine.compute(go.getComponent(Physical.class));
                graphicEngine.relocate(go.getComponent(Displayable.class), go.getComponent(Physical.class).getX(), go.getComponent(Physical.class).getY());
                System.out.println(go.getComponent(Physical.class).getX() + " " + go.getComponent(Physical.class).getY());
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        
    }

}
