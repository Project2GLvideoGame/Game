package game;

import game.event.EventsManager;
import game.graphic.GraphicEngine;
import game.physics.PhysicEngine;

public class App {

    public static void main(String[] args) throws Exception {
        EventsManager eventManager = new EventsManager();
        GraphicEngine graphicE = new GraphicEngine();
        PhysicEngine physicalE = new PhysicEngine(eventManager);
        
        Kernel kernel = new Kernel(graphicE, physicalE);
        kernel.startGameThread();
    }
}
