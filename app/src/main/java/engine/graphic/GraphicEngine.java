package engine.graphic;

import engine.Engine;
import engine.Kernel;
import engine.event.EventsManager;
import engine.event.MoveEvent;
import engine.event.StateEvent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicEngine extends Engine {

    //Screen settings
    final static int originalTileSize = 16; //16x16 tiles
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 22;
    final int maxScreenRow = 13;
    
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    
    JFrame window = new JFrame();

    private Scene scene;
    
    /**
     * Initialise une nouvelle scène et la dimensionne
     * @param eventsManager
     * Recevra la liste des évènements auquels il est abonné
     */
    public GraphicEngine(EventsManager eventsManager){
        super(eventsManager);
        this.scene = new Scene();
        initWindow();
    }

    public Scene getScene() {
        return scene;
    }

    private void initWindow(){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");
        
        window.add(scene);
        
        window.pack(); //window set to fit to prefered size
        
        window.setLocationRelativeTo(null); //init the window to center on the screen
        window.setVisible(true);
    }

    public void setPosition(Displayable displayable, int x, int y) {
        displayable.setX(x);
        displayable.setY(y);
    }

    public void scale(Displayable displayable, double percentage) {
        displayable.setHeight((int)(displayable.getHeight()*percentage));
        displayable.setWidth((int)(displayable.getWidth()*percentage));
    }

    public void setVisibility(Displayable displayable, boolean value) {
        displayable.setVisibility(value);
    }

    public void addDisplayable(Displayable displayable){
        this.scene.displayables.add(displayable);
    }

    public void removeDisplayable(Displayable displayable){
        this.scene.displayables.remove(displayable);
    }

    public int getScreenWidth(){
        return screenWidth;
    }

    public int getScreenHeight(){
        return screenHeight;
    }

    /**
     * Vérifie les évènements concernant MoveEvent ou StateEvent
     * et les traites, puis met a jour la scène.
    */
    public void update(){
        handleStateEvents();
        handleMoveEvents();
        this.scene.validate();
        this.scene.repaint();
    }

    private void handleMoveEvents() {
        List<MoveEvent> moveEvents = getEvents(MoveEvent.class);
        if (moveEvents == null) return;
        for (int i=0; i<moveEvents.size(); i++) {
            if(moveEvents.get(i)==null){
                System.out.println("||||||||||||||||||||||||||||||||||||||||||||||"+i);
                continue;
            }
            if (moveEvents.get(i).getGameObject().getComponent(Displayable.class) == null) continue;
            setPosition(moveEvents.get(i).getGameObject().getComponent(Displayable.class),  (int) moveEvents.get(i).getDestination().getX(), (int) moveEvents.get(i).getDestination().getY());
        }
        moveEvents.clear();
    }

    private void handleStateEvents(){
        List<StateEvent> stateEvents = getEvents(StateEvent.class);
        if(stateEvents == null) return;
        for (StateEvent stateEvent : stateEvents) {
            System.out.println("recoir state event");
            createScene(stateEvent.getDisplayables());
        }
        stateEvents.clear();
    }

    private void createScene(Displayable... displayables){
        scene.displayables.addAll(Arrays.asList(displayables));
        Kernel.getInstance().gameOver = true;
        System.out.println("scene size=== "+displayables.length);
        System.out.println(displayables[1].getX());
    }

    /**
     * Une Scène est composé d'une liste de Displayable, elle
     * étend JPanel et donc peut être mis a jour en appelant
     * la fonction repaint().
    */
    public class Scene extends JPanel {

        public List<Displayable> displayables = new ArrayList<>();
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
