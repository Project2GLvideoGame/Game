package engine.graphic;

import engine.Engine;
import engine.event.EventsManager;
import engine.event.MoveEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicEngine extends Engine<Displayable> {

    //Screen settings
    final static int originalTileSize = 16; //16x16 tiles
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 22;
    final int maxScreenRow = 13;
    
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    
    private static int FPS = 60;
    

    private List<Displayable> displayables = new ArrayList<>();
    private Scene scene;
    

    public GraphicEngine(EventsManager eventsManager){
        super(eventsManager);
        this.scene = new Scene();
        initWindow();
    }

    public Scene getScene() {
        return scene;
    }

    private void initWindow(){
        JFrame window = new JFrame();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Game");
        
        window.add(scene);
        
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

    public void removeDisplayable(Displayable displayable){
        displayables.remove(displayable);
    }


    public int getScreenWidth(){
        return screenWidth;
    }

    public int getScreenHeight(){
        return screenHeight;
    }


    public void update(){
        List<MoveEvent> moveEvents = getEvents(MoveEvent.class);
        if(moveEvents != null){
            for (int i = 0; i < moveEvents.size(); i++) {
                if(moveEvents.get(i).getGameObject().getComponent(Displayable.class)==null) continue;
                setPosition(moveEvents.get(i).getGameObject().getComponent(Displayable.class), (int)moveEvents.get(i).getDestination().getX(), (int)moveEvents.get(i).getDestination().getY());
                moveEvents.remove(i);
            }
        }

        this.scene.validate();
        this.scene.repaint();
    }



    public class Scene extends JPanel {

        public Scene() {
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
            this.setFocusable(true);
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            for (int i = 0; i < displayables.size(); i++) {
                if(displayables.get(i).getVisibility())
                    displayables.get(i).draw(g2);
            }
            g2.dispose();
        }
    }







}
