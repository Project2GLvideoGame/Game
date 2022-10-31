package game;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.math.*;

public class PhysicalEngine {


    List<Physical> physicalObjects = new ArrayList<>();

    public PhysicalEngine() {
        super();
    }

    public void addPhysicalObject(Physical physical) {
        physicalObjects.add(physical);
    }

    public List<Physical> isCollision(Physical physical) {
        List<Physical> collidedObjects = new ArrayList<>();
        for (Physical physicalObject : physicalObjects) {
            if (!physicalObject.equals(physical) && isCollided(physical, physicalObject)) {
                collidedObjects.add(physicalObject);
            }
        }
        return collidedObjects;
    }

    private boolean isCollided(Physical obj1, Physical obj2) {
        return obj2.getBoxCollider().intersects(obj1.getBoxCollider());
    }


    //#region LP
    

    private double computeDirection(Point source, Point destination){
        // calculate the angle theta from the deltaY and deltaX values
        // (atan2 returns radians values from [-PI,PI])
        // 0 currently points EAST.  
        // NOTE: By preserving Y and X param order to atan2,  we are expecting 
        // a CLOCKWISE angle direction.  
        double theta = Math.atan2(destination.y - source.y, destination.x - source.x);

        // rotate the theta angle clockwise by 90 degrees 
        // (this makes 0 point NORTH)
        // NOTE: adding to an angle rotates it clockwise.  
        // subtracting would rotate it counter-clockwise
        theta += Math.PI/2.0;

        // convert from radians to degrees
        // this will give you an angle from [0->270],[-180,0]
        double angle = Math.toDegrees(theta);

        // convert to positive range [0-360)
        // since we want to prevent negative angles, adjust them now.
        // we can assume that atan2 will not return a negative value
        // greater than one partial rotation
    return (angle<0)? angle+360:angle;
    }

    public void setDestination(Physical physical, Point destination) {
        physical.setDestinationCoord(destination);
        double direction = computeDirection(physical.getCoordinate(), destination);
        physical.setDirection(direction);
    }

    public void setDirection(Physical physical, double direction) {
        physical.setDirection(direction);
    }


    /* Téléportation */
    public void setCoordinate(Physical physical, int x, int y) {
        physical.setCoordinate(x, y);
    }


    public void setSpeed(Physical physical, double absSpeed) {
        physical.setSpeed(absSpeed);
    }

    public void setAcceleration(Physical physical, double absAcceleration) {
        physical.setAcceleration(absAcceleration);
    }


    /* Déf mathématique*/
    private static Point polarToCartesian(double angleDeg, double radius){
        //System.out.printf("[DEBUG] angleDeg: %f  radius: %f\n", angleDeg, radius);
        double angleInRadians = Math.toRadians(angleDeg);
        double x = radius * Math.cos(angleInRadians);
        double y = radius * Math.sin(angleInRadians);
        
        return new Point( (int)x, (int)y );
    }

    /* Adaptation à notre originine au nord */
    private static Point computeDelta(double direction, double radius){
        return polarToCartesian(direction+90, radius);
    }


    public void update(Physical physical) {        
        //TODO: utiliser la destination Point destination = physical.getDestination();
        //TODO: update d'abord position ou acceleration ?
        
        double speed = physical.getSpeed();
        int x = physical.getX();
        int y = physical.getY();
        double distance = speed;
        Point delta = computeDelta(physical.getDirection(), distance);

        //update coordonnées grace à la vitesse
        physical.setCoordinate(x+delta.x, y+delta.y);
        System.out.printf("[DEBUG] %d  %d\n", delta.x, delta.y);


        //update vitesse grace a l'acceleration
        physical.setSpeed(speed+physical.getAcceleration());

        //check colision : si collision on annule le déplacement
        if (!isCollision(physical).isEmpty()){
            physical.setCoordinate(x, y);
            System.out.printf("collision!!\n");
        }

    }












    //#endregion
}
