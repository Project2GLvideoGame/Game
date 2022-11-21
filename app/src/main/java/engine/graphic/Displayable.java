package engine.graphic;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Component;

import java.awt.Graphics2D;

public class Displayable extends Component {

    private Image asset;
    private int x, y;
    private int width, height;
    private boolean visibility = true;

    private AnimationFrames animationFrames = null;

    public Displayable(int x, int y, int width, int height, String spritePaths) {

        BufferedImage tempAsset;
        try {
            tempAsset = ImageIO.read(getClass().getResource(spritePaths));
            this.asset = tempAsset.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public Displayable(int x, int y, int width, int height, int framerate, String... spritePaths) {

        this.animationFrames = new AnimationFrames(framerate, width, height, spritePaths);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getAsset() {
        return asset;
    }

    public boolean getVisibility(){
        return this.visibility;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setWidth(int width) {
        this.asset = asset.getScaledInstance(width, this.height, Image.SCALE_DEFAULT);
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.asset = asset.getScaledInstance(this.width, height, Image.SCALE_DEFAULT);
        this.height = height;
    }
    
    public void setAsset(Image img) {
        this.asset = img;
    }

    public void setVisibility(boolean visibility){
        this.visibility = visibility;
    }

    public void draw(Graphics2D g2){
        if(this.animationFrames != null){
            this.asset = this.animationFrames.getCurrentSprite();
            this.animationFrames.updateSprite();
        }

        g2.drawImage(this.asset, this.x, this.y, null);
    }
}