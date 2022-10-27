package game;

public class Physical {

    private double x;
    private double y;
    private double speed = 0;
    private Rectangle rectangle;

    public Physical(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.rectangle = new Rectangle(width, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
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
