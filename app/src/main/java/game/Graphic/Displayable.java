package game.Graphic;

import java.awt.image.BufferedImage;

import game.Component;

public class Displayable extends Component{

    private final BufferedImage asset;
    private int x, y;
    private int width, height;

    public Displayable(BufferedImage asset, int x, int y, int width, int height) {
        this.asset = asset;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getAsset() {
        return asset;
    }
}
