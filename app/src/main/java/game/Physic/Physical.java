package game.Physic;


import game.Component;
import javafx.scene.paint.Color;

public class Physical extends Component {

    private Rectangle rectangle;
    private double speed;
    private double direction; // 0=Nord, sens trigo <==> N=0, O=90, S=180, E=270
    private Coordinate destination;
    private boolean useDestination;
    private double acceleration;

    public Physical(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.speed=0;
        this.acceleration = 0;
        destination = new Coordinate(0,0);
        useDestination = false;
        direction = 0;
    }

    public double getX() {
        return rectangle.getX();
    }

    public double getY() {
        return rectangle.getY();
    }

    public Coordinate getCoordinate() {
        return new Coordinate(rectangle.getX(), rectangle.getY());
    }

    public void setX(double x) {
        rectangle.setX(x);
    }

    public void setY(double y) {
        rectangle.setY(y);
    }
    
    public void setCoordinate(double x, double y) {
        rectangle.setX(x);
        rectangle.setY(y);
    }

    public void setCoordinate(Coordinate coord) {
        rectangle.setX(coord.getX());
        rectangle.setY(coord.getY());
    }

    public double getSpeed() {
        return this.speed;
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

    
    public double getDirection(){
        return this.direction;
    }
    
    public void setDirection(double direction){
        this.direction = (direction>=360)? 0:((direction<0)? 0:direction);
    }
    
    public Coordinate getDestination(){
        return this.destination;
    }
    
    public void setDestinationCoord(Coordinate destination){
        this.destination = destination;
    }


    // public double getAcceleration() {
    //     return this.acceleration;
    // }
    
    // public void setAcceleration(double acceleration) {
    //     this.acceleration = acceleration;
    // }



    public javafx.scene.shape.Rectangle printBoxCollider(){
        javafx.scene.shape.Rectangle rec = new javafx.scene.shape.Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        rec.setFill(Color.GREEN);
        rec.setOpacity(0.5);
        return rec;
    }




}

//TODO: les params de Displayable et Physical sont pas dans le mem ordre