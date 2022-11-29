package game.ai;

import java.util.ArrayList;
import java.util.List;
import engine.Engine;
import engine.GameObject;
import engine.event.EventsManager;
import engine.physics.Physic;
import game.entity.PlayerShoot;
import game.entity.enemies.Crab;
import game.entity.enemies.Enemies;
import engine.event.CollisionEvent;
import engine.event.EnnemisCollisionEvent;

/**
 * IAEngine
 */
public class AIEngine extends Engine {

   List<Intelligent> intelligents = new ArrayList<Intelligent>();


   public AIEngine(EventsManager eventsManager) {
      super(eventsManager);
   }

   public void addIAObjectIntelligent(Intelligent intelligent) {
      intelligents.add(intelligent);
   }

   public void removeIAObjectIntelligent(Intelligent intelligent) {
      intelligents.remove(intelligent);
   }


   public void update() {


      List<CollisionEvent> collisionEvents = getEvents(CollisionEvent.class);
      if (collisionEvents == null) return;

      for (CollisionEvent collisionEvent : collisionEvents) {
         System.out.println(collisionEvent.getGameObject()+" "+collisionEvent.getCollisions().get(0).getObstacle().getGameObject() );         
         if(collisionEvent.getGameObject().getComponent(Intelligent.class)==null) return;
         collisionEvent.getGameObject().getComponent(Intelligent.class).getIA().apply(collisionEvent);
      }
      collisionEvents.clear();
      //System.out.println(collisionEvents.size());
   }







}
