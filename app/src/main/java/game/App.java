package game;

import game.Event.EventsManager;
import game.Graphic.GraphicEngine;
import game.Physics.PhysicEngine;

public class App {

    public static void main(String[] args) throws Exception {
        EventsManager eventManager = new EventsManager();
        GraphicEngine graphicE = new GraphicEngine();
        PhysicEngine physicalE = new PhysicEngine(eventManager);
        
        Kernel kernel = new Kernel(graphicE, physicalE);
        kernel.startGameThread();
    }
}
