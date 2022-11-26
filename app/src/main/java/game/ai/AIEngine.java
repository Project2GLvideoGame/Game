package game.ai;

import java.util.ArrayList;
import java.util.List;

import engine.Engine;
import engine.GameObject;
import engine.event.EventsManager;
import engine.physics.Physic;
import game.entity.enemies.Enemies;
import engine.event.CollisionEvent;
import engine.event.EnnemisCollisionEvent;


/**
 * IAEngine
 */

public class AIEngine extends Engine {
   
   List<Intelligent> intelligents = new ArrayList<Intelligent>();
   boolean collision = false;
   public AIEngine(EventsManager eventsManager) {
      super(eventsManager);

      eventsManager.subscribe(this ,EnnemisCollisionEvent.class);
   }

   public void addIAObjectIntelligent(Intelligent intelligent){
      intelligents.add(intelligent);
   }

   public void update(){
      
      List<CollisionEvent> collisionEvents = getEvents(CollisionEvent.class);

      if(collisionEvents != null){
         for (CollisionEvent collisionEvent : collisionEvents) {
           

            List<EnnemisCollisionEvent> ennemisCollisionEvents = getEvents(EnnemisCollisionEvent.class);
            if(ennemisCollisionEvents != null){
               for(EnnemisCollisionEvent ece : ennemisCollisionEvents){
               for (Intelligent intelligent : intelligents) {
                  GameObject ennemis = intelligent.getGameObject();
                  if(intelligent.getGameObject() instanceof Enemies){
                     ennemis.getComponent(Physic.class).setDirection(ece.getNewDirection());
                     ennemis.getComponent(Physic.class).setY(ennemis.getComponent(Physic.class).getY()+15);
                  }
               }
               collision = true;

            }
            ennemisCollisionEvents.clear();

            }
               if(!collision && collisionEvent.getGameObject().getComponent(Intelligent.class) != null){
               collisionEvent.getGameObject().getComponent(Intelligent.class).getIA().apply(collisionEvent,eventsManager);
            }

         }
         collisionEvents.clear();
         collision = false;
      }

   }
}