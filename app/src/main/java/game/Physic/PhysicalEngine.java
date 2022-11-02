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

    //TODO: recevoir des Coordinate a tester en plus, pour appeler isCollided()
    public List<Collision> allCollision(Physical physical) {
        List<Collision> collidedObjects = new ArrayList<>();
        for (Physical physicalObject : physicalObjects) {
            if (physicalObject!=physical && isCollided(physical, physicalObject)) {
                collidedObjects.add(
                    new Collision(physical.getBoxCollider().intersection(physicalObject.getBoxCollider()),
                    physical,
                    physicalObject
                    ));
            }
        }
        return collidedObjects;
    }

    //TODO: utiliser des Coordinate plutot que le obxColider pour obj1
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
    private static Coordinate polarToCartesianMath(double angleDeg, double radius){
        double angleInRadians = Math.toRadians(angleDeg);
        double x = radius * Math.cos(angleInRadians);
        double y = radius * Math.sin(angleInRadians);
        return new Coordinate(x, y);
    }

    /* Adaptation à notre origine au nord */
    private static Coordinate polarToCartesian(double direction, double radius){
        return polarToCartesianMath(direction+90, radius);
    }


    private double deltaYFromDeltaX(double deltaCorrectionX, double alpha){
        double alphaRad = Math.toRadians(alpha);
        double r = Math.abs(deltaCorrectionX/Math.cos(alphaRad));
        double delta_y = r*Math.sin(alphaRad);
        return delta_y;
    }

    private double deltaXFromDeltaY(double deltaCorrectionY, double alpha){
        double alphaRad = Math.toRadians(alpha);
        double r = Math.abs(deltaCorrectionY/Math.sin(alphaRad));
        double delta_x = r*Math.cos(alphaRad);
        return delta_x;
    }

    //TODO: regarde une seule colision
    private Coordinate positionAfterCollision(Physical physical, double initialX, double initialY, List<Collision> collisions){
        Coordinate beforeCollsionCoord = new Coordinate(initialX, initialY);
        if(collisions.isEmpty()) return beforeCollsionCoord;
        
        double deltaX = physical.getX()-initialX;
        int opX = oppositeSign(deltaX);
        double deltaY = physical.getY()-initialY;
        int opY = oppositeSign(deltaY);


        Collision collision = collisions.get(0);
        Rectangle overlapRect = collision.overlap;

        double correctionOnX = physical.getX()+opX*overlapRect.getWidth();
        Coordinate coordTryX = new Coordinate(correctionOnX, physical.getY());
        double correctionOnY = physical.getY()+opY*overlapRect.getHeight(); // - car javaFX axex y inversé
        Coordinate coordTryY = new Coordinate(physical.getX(), correctionOnY);
        
        //physical.setCoordinate(coordTryX);
        if(physical.getBoxCollider().isTouching(collision.obstacle.getBoxCollider()) ){
            double deltaCorrectionY = deltaYFromDeltaX(opX*overlapRect.getWidth(), physical.getDirection()+180+90); // Nord décalé
            System.out.println("111111");
            return new Coordinate(correctionOnX, physical.getY()-deltaCorrectionY);
        }
        
        //physical.setCoordinate(coordTryY);
        if( physical.getBoxCollider().isTouching(collision.obstacle.getBoxCollider()) ){
            double deltaCorrectionX = deltaXFromDeltaY(-opY*overlapRect.getHeight(), physical.getDirection()+180+90);
            System.out.println("22222222");
            return new Coordinate(physical.getX()+deltaCorrectionX, correctionOnY);
        }
        
        //physical.setCoordinate(new Coordinate(0, 0));
        System.out.println("33333");
        
        // System.out.println(physical.getY()+physical.getBoxCollider().getHeight());
        // physical.setCoordinate(coordTryY);
        // System.out.println(opY*overlapRect.getHeight());
        // System.out.println(physical.getY()+physical.getBoxCollider().getHeight());
        // System.out.println(collision.obstacle.getBoxCollider().getY());
        // System.out.println();
        
        System.out.println(physical.getY());
        physical.setCoordinate(coordTryY);
        System.out.println(opY*overlapRect.getY());
        System.out.println(physical.getY());
        System.out.println(collision.obstacle.getBoxCollider().getY()+collision.obstacle.getBoxCollider().getHeight());
        System.out.println();
        
        return new Coordinate(350, 350);
    }


    private int oppositeSign(double n){
        return (n>0)? -1:1;
    }


    public void compute(Physical physical) {
        long currentTime = System.nanoTime();
        long elapsedTime = (currentTime-previousTime)/10_000_000;
        
        double speed = physical.getSpeed();
        double x = physical.getX();
        double y = physical.getY();
        double distance  = speed*elapsedTime;
        Coordinate delta = polarToCartesian(physical.getDirection(), distance);

        //update coordonnées grace à la vitesse
    //    System.out.printf("[DEBUG] coordO %f  %f\n", physical.getX(), physical.getY());
    //    System.out.printf("[DEBUG] %f  %f\n", delta.x, delta.y);
        Coordinate noCollisionCoord = new Coordinate(x+delta.x, y-delta.y); //y inversé dans le graphique(JavaFX)
        physical.setCoordinate(noCollisionCoord); 
        
        List<Collision> collisions = allCollision(physical);
        if (! collisions.isEmpty()){
        //    System.out.printf(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Collision!!\n");
            physical.setCoordinate(noCollisionCoord);
            Coordinate withCollisionCoord = positionAfterCollision(physical, x, y, collisions);
            physical.setCoordinate(withCollisionCoord);
        }

        previousTime = currentTime;
    }




//TODO: update d'abord position ou acceleration ?
//TODO:  entre 2 compute() on peu traverser un mur
//TODO: coder acceleration avec des forces
//TODO fn(angle, distance)
//TODO fn(x, y)
//TODO coder destination
//TODO: rotation 90 pour la collision
// - au lieu de + sur l'axe Y






//#endregion
}
