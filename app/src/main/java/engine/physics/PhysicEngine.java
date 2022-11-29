package engine.physics;

import static engine.physics.Utils.*;

import java.util.ArrayList;
import java.util.List;
import engine.Engine;
import engine.event.CollisionEvent;
import engine.event.EventsManager;
import engine.event.MoveEvent;
import game.ai.Intelligent;
import game.entity.enemies.Crab;
import game.entity.enemies.Enemies;

public class PhysicEngine extends Engine{

    public List<Physic> physicalObjects = new ArrayList<>();
    long previousTime;

    public PhysicEngine(EventsManager eventsManager) {
        super(eventsManager);
        previousTime = System.nanoTime(); //TODO pas exact
    }



    public void addPhysicalObject(Physic physical) {
        physicalObjects.add(physical);
    }


    public void removePhysicalObject(Physic physical) {
        physicalObjects.remove(physical);
    }


    public List<Collision> allCollision(Physic physical) {
        List<Collision> collidedObjects = new ArrayList<>();
        for (int i = 0; i < physicalObjects.size(); i++) {
            Physic tempPhysical = physicalObjects.get(i);
            
            if (tempPhysical!=physical && isCollided(physical, tempPhysical)) {
                collidedObjects.add(
                    new Collision(physical.getBoxCollider().intersection(tempPhysical.getBoxCollider()),
                    physical,
                    tempPhysical
                    ));
            }
        } 
        
        return collidedObjects;
    }



    private boolean isCollided(Physic obj1, Physic obj2) {
        return obj2.getBoxCollider().intersects(obj1.getBoxCollider());
    }


    public void setPositionAfterCollision(Physic physical, Coordinate beforeCollsionCoord, Coordinate CollisonCoord, List<Collision> collisions){
        physical.getReaction().setPositionAfterCollision(physical, beforeCollsionCoord, CollisonCoord, collisions);
    }


    public void update(Physic physical) {
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
            //physical.setCoordinate(naiveCoord);
            setPositionAfterCollision(physical, lastCoord, naiveCoord, collisions);
            CollisionEvent collisionEvent = new CollisionEvent(physical.getGameObject(),collisions,lastCoord);
            if(collisionEvent.getGameObject() instanceof Enemies)
                submit(collisionEvent);
            else{
                //submit(collisionEvent);
            }
            //physical.setSpeed(0);
            // System.out.printf("[DEBUG] coordO %f  %f\n", physical.getX(), physical.getY());
            // System.out.printf("[DEBUG] coordO %f  %f\n", physical.getX()+physical.getBoxCollider().getWidth(), physical.getY()+physical.getBoxCollider().getHeight());

        }
        MoveEvent moveEvent = new MoveEvent(physical.getGameObject(), lastCoord, physical.getCoordinate());
        submit(moveEvent);
    }


    public void update(){
        //System.out.println("len="+physicalObjects.size());
        //if (System.nanoTime()-previousTime<10_000_000) return;
        for (int i = 0; i < physicalObjects.size(); i++) {
            Physic physical = physicalObjects.get(i);
            update(physical);
        }
        previousTime = System.nanoTime();
    }


}
