package game;

import java.io.IOException;

import javax.swing.JFrame;

import game.Graphic.GraphicEngine;

public class App {

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        GraphicEngine graphicE = new GraphicEngine();
        window.add(graphicE);

        window.pack(); //window set to fit to prefered size

        window.setLocationRelativeTo(null); //init the window to center on the screen
        window.setVisible(true);

        graphicE.startGameThread();
    }
}
