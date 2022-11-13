package game;

import game.Graphic.GraphicEngine;
import game.Physic.PhysicalEngine;

public class App {

    public static void main(String[] args) throws Exception {
        GraphicEngine graphicE = new GraphicEngine();
        PhysicalEngine physicalE = new PhysicalEngine();
        
        Kernel kernel = new Kernel(graphicE, physicalE);
        kernel.startGameThread();
    }
}
