package game.Physic;

import java.util.ArrayList;
import java.util.List;
import game.Engine;
import game.Kernel;
import game.Events.EventsManager;
import game.Events.MoveEvent;

import static game.Physic.Utils.*;

public class PhysicalEngine extends Engine{

    public List<Physical> physicalObjects = new ArrayList<>();
    long previousTime;

    public PhysicalEngine(EventsManager eventsManager) {
        super(eventsManager);
        previousTime = System.nanoTime(); //TODO pas exact
    }



    public void addPhysicalObject(Physical physical) {
        physicalObjects.add(physical);
    }


    public void removePhysicalObject(Physical physical) {
        physicalObjects.remove(physical);
    }


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



    private boolean isCollided(Physical obj1, Physical obj2) {
        return obj2.getBoxCollider().intersects(obj1.getBoxCollider());
    }


    //TODO: regarde une seule colision
    public void setPositionAfterCollision(Physical physical, Coordinate beforeCollsionCoord, Coordinate CollisonCoord, List<Collision> collisions){
        if(collisions.isEmpty()) physical.setCoordinate(beforeCollsionCoord);
        
        Collision collision = collisions.get(0);
        Rectangle overlapRect = collision.overlap;

        double deltaX = CollisonCoord.getX()-beforeCollsionCoord.getX();
        int opX = oppositeSign(deltaX);
        double correctionOnX = opX*overlapRect.getWidth();
        double correctionOnY = deltaYFromDeltaX(opX*overlapRect.getWidth(), physical.getDirection()+180+90); // 90:Nord décalé 180:demi tour
        physical.setCoordinate(new Coordinate(round(CollisonCoord.getX()+correctionOnX,6), round(CollisonCoord.getY()+correctionOnY,6) ));
        if(physical.getBoxCollider().isTouching(collision.obstacle.getBoxCollider())){
            // System.out.println("coorX: "+correctionOnX+"  corrY: "+correctionOnY);
            return;
        }
        
        double deltaY = CollisonCoord.getY()-beforeCollsionCoord.getY();
        int opY = oppositeSign(deltaY);
        correctionOnY = opY*overlapRect.getHeight();
        correctionOnX = deltaXFromDeltaY(-opY*overlapRect.getHeight(), physical.getDirection()+180+90);
        physical.setCoordinate(new Coordinate( Math.round(CollisonCoord.getX()+correctionOnX), Math.round(CollisonCoord.getY()+correctionOnY)));
        if( physical.getBoxCollider().isTouching(collision.obstacle.getBoxCollider()) ){
            // System.out.println("coorX: "+correctionOnX+"  corrY: "+correctionOnY);
            return;
        }
    
        // System.out.println("33333");
        physical.setCoordinate(beforeCollsionCoord);
        return;
    }


    public void update(Physical physical) {
        long currentTime = System.nanoTime();
        long elapsedTime = (currentTime-previousTime)/10_000_000;
        
        double speed = physical.getSpeed();
        Coordinate lastCoord = new Coordinate(physical.getX(), physical.getY());
        double distance  = speed*elapsedTime;

        if(physical.getUseDestination()){
            physical.setDirection(normalizeAngle(computeDirectionForJavaFx(lastCoord, physical.getDestination())));
            distance = Math.min(distance, distance(lastCoord, physical.getDestination()));
        }

        Coordinate deltaCoord = polarToCartesian(physical.getDirection(), distance);

        Coordinate naiveCoord = new Coordinate(round(lastCoord.x+deltaCoord.x,6), round(lastCoord.y-deltaCoord.y,6)); //y inversé dans le graphique(JavaFX)
        physical.setCoordinate(naiveCoord);
        
        List<Collision> collisions = allCollision(physical);
        if (! collisions.isEmpty()){
            //System.out.printf(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Collision!!\n");
            //System.out.printf("[DEBUG] coordO %f  %f\n", physical.getX(), physical.getY());
            //System.out.printf("[DEBUG] coordO %f  %f\n", physical.getX()+physical.getBoxCollider().getWidth(), physical.getY()+physical.getBoxCollider().getHeight());
            //System.out.println("overlapW: "+collisions.get(0).overlap.getWidth()+"  overlapH: "+collisions.get(0).overlap.getHeight());
            physical.setCoordinate(naiveCoord);
            setPositionAfterCollision(physical, lastCoord, naiveCoord, collisions);
            //physical.setSpeed(0);
            // System.out.printf("[DEBUG] coordO %f  %f\n", physical.getX(), physical.getY());
            // System.out.printf("[DEBUG] coordO %f  %f\n", physical.getX()+physical.getBoxCollider().getWidth(), physical.getY()+physical.getBoxCollider().getHeight());

        }
        MoveEvent moveEvent = new MoveEvent(physical.getGameObject(), lastCoord, physical.getCoordinate());
        submit(moveEvent);
    }


    public void update(){
        for (Physical physical : physicalObjects) {
            update(physical);
        }
        previousTime = System.nanoTime();
    }



//TODO: update d'abord position ou acceleration ?
//TODO: coder acceleration avec des forces
//TODO:  entre 2 compute() on peu traverser un mur
//#endregion
}
