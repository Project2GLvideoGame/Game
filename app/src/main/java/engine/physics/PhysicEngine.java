package engine.physics;

import static engine.physics.Utils.*;
import java.util.ArrayList;
import java.util.List;
import engine.Engine;
import engine.event.CollisionEvent;
import engine.event.EventsManager;
import engine.event.MoveEvent;
import game.entity.Player;
import game.entity.enemies.Enemies;


public class PhysicEngine extends Engine{

    PreviousWorld previousWorld =null;
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


    public List<Collision> allCollision(Physic physical, List<Physic> physicalObjectsCopy) {

        List<Collision> collidedObjects = new ArrayList<>();
        for (int i = 0; i < physicalObjectsCopy.size(); i++) {
            Physic tempPhysical = physicalObjectsCopy.get(i);
            
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




    public void placerCorrectementToutLeMonde(List<Physic> physicalObjectsCopy , long elapsedTime) {
        List<PositionAfterCollisionData> doiventEtreReplaces = new ArrayList<>();
        
        for (int i = 0; i < physicalObjectsCopy.size(); i++) {
            Physic physical = physicalObjectsCopy.get(i);
            Coordinate naiveCoord = new Coordinate(physical.getX(), physical.getY());
            Coordinate beforeCollCoord  = previousWorld.getPreviousPhysical(i).getPreviousCoordinate();


            List<Collision> collisions = allCollision(physical, physicalObjectsCopy);
            if (! collisions.isEmpty()){
                doiventEtreReplaces.add(new PositionAfterCollisionData(physical, beforeCollCoord, naiveCoord, collisions));

                CollisionEvent collisionEvent = new CollisionEvent(physical.getGameObject(), collisions, beforeCollCoord);
                submit(collisionEvent);
            }

            MoveEvent moveEvent = new MoveEvent(physical.getGameObject(), beforeCollCoord, physical.getCoordinate());
            submit(moveEvent);
        }

        for (PositionAfterCollisionData data : doiventEtreReplaces) {        
            setPositionAfterCollision(data.getPhysic(), data.getBeforeCollCoord(), data.getNaiveCoord(), data.getCollisions());
        }

    }



    
    private void placerNaivementToutLeMonde(List<Physic> physicalObjectsCopy , long elapsedTime){
        
        for (int i = 0; i < physicalObjectsCopy.size(); i++) {
            Physic physical = physicalObjectsCopy.get(i);

            
            double speed = physical.getSpeed();
            Coordinate beforeCollCoord = new Coordinate(physical.getX(), physical.getY());
            double distance  = speed*elapsedTime;
            
            if(physical.getUseDestination()){
                physical.setDirection(normalizeAngle(computeDirectionForJavaFx(beforeCollCoord, physical.getDestination())));
                distance = Math.min(distance, distance(beforeCollCoord, physical.getDestination()));
            }
            
            Coordinate deltaCoord = polarToCartesian(physical.getDirection(), distance);
            
            Coordinate naiveCoord = new Coordinate(round(beforeCollCoord.x+deltaCoord.x,6), round(beforeCollCoord.y-deltaCoord.y,6)); //y inversé dans le graphique(JavaFX)
            physical.setCoordinate(naiveCoord);
            
            previousWorld.addpreviousPhysical( previousWorld.new PreviousPhysical(physical, beforeCollCoord));
        }
    }




    public void update(){
        //System.out.println("len="+physicalObjects.size());
        //if (System.nanoTime()-previousTime<10_000_000) return;
        
        previousWorld = new PreviousWorld();
        List<Physic> physicalObjectsCopy = new ArrayList<>(physicalObjects);
        
        long currentTime = System.nanoTime();
        long elapsedTime = (currentTime-previousTime)/10_000_000;

        placerNaivementToutLeMonde(physicalObjectsCopy, elapsedTime);
        placerCorrectementToutLeMonde(physicalObjectsCopy, elapsedTime);
        
        previousTime = System.nanoTime();
    }




}
