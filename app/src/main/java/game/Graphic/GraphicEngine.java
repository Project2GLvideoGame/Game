package game.Graphic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import game.GameObject;

public class GraphicEngine extends JPanel{

    final int originalTileSize = 16; //16x16 tiles
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    private List<Displayable> displayables = new ArrayList<>();
    

    //Initialise les gameObject Initialement présent dans la scène 
    private void createContent() {
        // GameObject g = new GameObject(new Displayable(new ImageView("pacman_run.gif"), width/2 - 32, height/2 - 32, 64, 64));
        
        // mainGroup.getChildren().add(g.getComponent(Displayable.class).getAsset());
        // displayables.add(g.getComponent(Displayable.class));
    }

    public void init() throws Exception {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        createContent();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        Displayable displayable = player.getComponent(Displayable.class);

        g2.drawImage(displayable.getAsset(), displayable.getX(), displayable.getY(), null);

        g2.dispose();
    }

    private void actionEvent(ActionEvent event){
        scale(displayables.get(0), displayables.get(0).getAsset().getScaleX() + 0.2d);
    }

    public void setPosition(Displayable displayable, int x, int y) {
        displayable.setX(x);
        displayable.setY(y);
    }
    public void relocate(Displayable displayable, double x, double y){
        displayable.getAsset().relocate(x, y);
    }

    public void rotate(Displayable displayable, double angle) {
        displayable.getAsset().setRotate(angle);
    }

    public void scale(Displayable displayable, double percentage) {
        displayable.getAsset().setScaleX(percentage);
        displayable.getAsset().setScaleY(percentage);
    }

    public void setVisibility(Displayable displayable, boolean value) {
        displayable.getAsset().setVisible(value);
    }

    public void addDisplayable(Displayable displayable){
        displayables.add(displayable);
    }

}
