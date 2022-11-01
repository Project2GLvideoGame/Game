package game.Physic;


import java.util.ArrayList;
import java.util.List;
import java.math.*;

public class PhysicalEngine {


    public List<Physical> physicalObjects = new ArrayList<>();
    long previousTime;


    public PhysicalEngine() {
        previousTime = System.nanoTime(); //TODO pas exact
    }

    public void addPhysicalObject(Physical physical) {
        physicalObjects.add(physical);
    }

    public List<Physical> isCollision(Physical physical) {
        List<Physical> collidedObjects = new ArrayList<>();
        for (Physical physicalObject : physicalObjects) {
            if (physicalObject!=physical && isCollided(physical, physicalObject)) {
                collidedObjects.add(physicalObject);
            }
        }
        return collidedObjects;
    }

    private boolean isCollided(Physical obj1, Physical obj2) {
        return obj2.getBoxCollider().intersects(obj1.getBoxCollider());
    }


//#region LP
    

    private double computeDirection(Coordinate source, Coordinate destination){
        // calculate the angle theta from the deltaY and deltaX values
        // (atan2 returns radians values from [-PI,PI])
        // 0 currently Coordinates EAST.  
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

    public void setDestination(Physical physical, Coordinate destination) {
        physical.setDestinationCoord(destination);
        double direction = computeDirection(physical.getCoordinate(), destination);
        physical.setDirection(direction);
    }

    public void setDirection(Physical physical, double direction) {
        physical.setDirection(direction);
    }


    /* Téléportation */
    public void setCoordinate(Physical physical, double x, double y) {
        physical.setCoordinate(x, y);
    }


    public void setSpeed(Physical physical, double absSpeed) {
        physical.setSpeed(absSpeed);
    }

    // public void setAcceleration(Physical physical, double absAcceleration) {
    //     physical.setAcceleration(absAcceleration);
    // }


    /* Déf mathématique*/
    private static Coordinate polarToCartesian(double angleDeg, double radius){
        //System.out.printf("[DEBUG] angleDeg: %f  radius: %f\n", angleDeg, radius);
        double angleInRadians = Math.toRadians(angleDeg);
        double x = radius * Math.cos(angleInRadians);
        double y = radius * Math.sin(angleInRadians);
        
        return new Coordinate(x, y);
    }

    /* Adaptation à notre origine au nord */
    private static Coordinate computeDelta(double direction, double radius){
        return polarToCartesian(direction+90, radius);
    }


    public void compute(Physical physical) {
        long currentTime = System.nanoTime();
        long elapsedTime = (currentTime-previousTime)/10_000_000;
        //TODO: utiliser la destination Point destination = physical.getDestination();
        //TODO: update d'abord position ou acceleration ?
        
        double speed = physical.getSpeed();
        //System.out.printf("[DEBUG] speed %f\n", speed);
        double x = physical.getX();
        double y = physical.getY();
        double distance = speed*elapsedTime;
        Coordinate delta = computeDelta(physical.getDirection(), distance);

        //update coordonnées grace à la vitesse
        System.out.printf("[DEBUG] coordB %f  %f\n", physical.getX(), physical.getY());
        System.out.printf("[DEBUG] %f  %f\n", x+delta.x, y-delta.y);
        physical.setCoordinate(x+delta.x, y-delta.y); //TODO javaFX c'est nul
        System.out.println("[DEBUG] coordA "+physical.getX()+" "+physical.getY());


        //update vitesse grace a l'acceleration
        //physical.setSpeed(speed+physical.getAcceleration()*elapsedTime);

        //check colision : si collision on annule le déplacement
        if (!isCollision(physical).isEmpty()){
            physical.setCoordinate(x, y);
            System.out.printf("collision!!\n");
        }
        previousTime = currentTime;
    }




//TODO: coordonées en double pour la physique
//TODO:  entre 2 compute() on peu traverser un mur
//TODO: retirer/recoder l'acceleration (car la elle est tout le temps || a la vitesse )







//#endregion
}
