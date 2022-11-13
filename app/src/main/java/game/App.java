package game;

import javax.swing.JFrame;

import game.Graphic.GraphicEngine;
import game.Physic.PhysicalEngine;

public class App {

    public static void main(String[] args) throws Exception {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Game");

        GraphicEngine graphicE = new GraphicEngine();
        PhysicalEngine physicalE = new PhysicalEngine();

        window.add(graphicE);
        
        window.pack(); //window set to fit to prefered size
        
        window.setLocationRelativeTo(null); //init the window to center on the screen
        window.setVisible(true);
        
        Kernel kernel = new Kernel(graphicE, physicalE);
        kernel.startGameThread();
    }
}
