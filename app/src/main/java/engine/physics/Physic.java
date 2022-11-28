package engine.physics;

import static engine.physics.Utils.*;

import engine.Component;
import engine.physics.collisionReaction.*;

public class Physic extends Component {

    private Rectangle boxCollider;
    private double speed;
    private double direction; // 0=Nord, sens trigo <==> N=0, O=90, S=180, E=270
    private Coordinate destination;
    private boolean useDestination;
    CollisionReaction reaction;

    public Physic(double x, double y, double width, double height) {
        this.boxCollider = new Rectangle(x, y, width, height);
        this.speed=0;
        destination = new Coordinate(0,0);
        useDestination = false;
        direction = 0;
        reaction = new RollBackReaction();
    }

    public Physic(double x, double y, double width, double height, CollisionReaction reaction) {
        this.boxCollider = new Rectangle(x, y, width, height);
        this.speed=0;
        destination = new Coordinate(0,0);
        useDestination = false;
        direction = 0;
        this.reaction = reaction;
    }

    public double getX() {
        return boxCollider.getX();
    }

    public double getY() {
        return boxCollider.getY();
    }

    public Coordinate getCoordinate() {
        return boxCollider.getCooridnate();
    }

    public void setX(double x) {
        boxCollider.setX(x);
    }

    public void setY(double y) {
        boxCollider.setY(y);
    }
    
    public void setCoordinate(Coordinate coord) {
        boxCollider.setCooridnate(coord);
    }



    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    

    public Rectangle getBoxCollider() {
        return boxCollider;
    }

    
    public double getDirection(){
        return this.direction;
    }

    
    
    public void setDirection(double direction){
        this.direction = (direction>=360)? 0:((direction<0)? 0:direction);
    }
    public void setDirection(Coordinate coordinate){
        setDirection(normalizeAngle(computeDirectionForJavaFx(getCoordinate(), coordinate)));
    }

    
    public Coordinate getDestination(){
        return this.destination;
    }
    
    public void setDestinationCoord(Coordinate destination){
        this.destination = destination;
    }

    public boolean getUseDestination(){
        return this.useDestination;
    }

    public void setUseDestination(boolean value){
        this.useDestination = value;
    }

    public CollisionReaction getReaction(){
        return this.reaction;
    }




}
