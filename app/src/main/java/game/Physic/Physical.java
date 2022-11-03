package game.Physic;

import java.awt.Point;
import java.awt.Rectangle;

import game.Component;

public class Physical extends Component{

    private double speed;
    private double acceleration;
    private Rectangle rectangle;
    private Point destination;
    //private boolean useDestination;
    private double direction; // 0=Nord, sens trigo <==> N=0, O=90, S=180, E=270

    public Physical(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.speed=0;
        this.acceleration = 0;
        destination = new Point(0,0);
        //useDestination = false;
        direction = 0;
    }

    public int getX() {
        return (int) rectangle.getLocation().getX();
    }

    public int getY() {
        return (int) rectangle.getLocation().getY();
    }
    public Point getCoordinate() {
        return rectangle.getLocation();
    }

    public void setX(int x) {
        rectangle.setLocation(x, getY());
    }

    public void setY(int y) {
        rectangle.setLocation(getX(), y);
    }
    
    public void setCoordinate(int x, int y) {
        rectangle.setLocation(x, y);
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public double getAcceleration() {
        return this.acceleration;
    }
    
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public Rectangle getBoxCollider() {
        return rectangle;
    }

    public void setBoxCollider(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setDestinationCoord(Point destination){
        this.destination = destination;
    }

    public Point getDestination(){
        return this.destination;
    }

    public void setDirection(double direction){
        this.direction = (direction>=360)? 0:((direction<0)? 0:direction);
    }

    public double getDirection(){
        return this.direction;
    }






}
