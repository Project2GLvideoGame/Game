package game.TEST;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); //window set to fit to prefered size

        window.setLocationRelativeTo(null); //init the window to center on the screen
        window.setVisible(true);

        gamePanel.startGameThread();
    }
    
}
