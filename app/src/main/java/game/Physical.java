package game;

import java.awt.Rectangle;

public class Physical {

    private double speed = 0;
    private Rectangle rectangle;

    public Physical(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public int getX() {
        return (int) rectangle.getLocation().getX();
    }

    public int getY() {
        return (int) rectangle.getLocation().getY();
    }

    public void setX(int x) {
        rectangle.setLocation(x, getY());
    }

    public void setY(int y) {
        rectangle.setLocation(getX(), y);

    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Rectangle getBoxCollider() {
        return rectangle;
    }

    public void setBoxCollider(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

}
