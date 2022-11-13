package game.Graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicEngine extends JPanel{

    //Screen settings
    final int originalTileSize = 16; //16x16 tiles
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    
    private static int FPS = 25;
    

    private List<Displayable> displayables = new ArrayList<>();
    

    public GraphicEngine() throws Exception {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        initWindow();
    }
    
    private void initWindow(){

        JFrame window = new JFrame();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Game");
        
        window.add(this);
        
        window.pack(); //window set to fit to prefered size
        
        window.setLocationRelativeTo(null); //init the window to center on the screen
        window.setVisible(true);
    }

    private static double drawInternal = 1_000_000_000/FPS;
    private static double delta = 0;
    private static long lastTime = System.nanoTime();
    private static long currentTime;

    public static boolean refreshFrequences(){

        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / drawInternal;
        lastTime = currentTime;

        if(delta >= 1){
            delta--;
            return true;
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        for(Displayable disp : displayables){
            if(disp.getVisibility())
                g2.drawImage(disp.getAsset(), disp.getX(), disp.getY(), null);
        }
        
        g2.dispose();
    }

    public void setPosition(Displayable displayable, int x, int y) {
        displayable.setX(x);
        displayable.setY(y);
    }

    private void rotate(Displayable displayable, double angle) {
        //displayable.getAsset().setRotate(angle);
    }

    public void scale(Displayable displayable, double percentage) {
        displayable.setHeight((int)(displayable.getHeight()*percentage));
        displayable.setWidth((int)(displayable.getWidth()*percentage));
    }

    public void setVisibility(Displayable displayable, boolean value) {
        displayable.setVisibility(value);
    }

    public void addDisplayable(Displayable displayable){
        displayables.add(displayable);
    }

}
