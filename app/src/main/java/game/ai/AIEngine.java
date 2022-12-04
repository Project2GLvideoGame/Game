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

/**
 * IAEngine
 */
public class AIEngine extends Engine {

   List<Intelligent> intelligents = new ArrayList<Intelligent>();
   long previousTime;
   final long DeltaBetweenCall = 200_000_000; //in ns

   public AIEngine(EventsManager eventsManager) {
      super(eventsManager);
      previousTime = System.nanoTime();
   }

   public void addIAObjectIntelligent(Intelligent intelligent) {
      intelligents.add(intelligent);
   }

   public void removeIAObjectIntelligent(Intelligent intelligent) {
      intelligents.remove(intelligent);
   }


   public void update() {
      ManageCollisionEvents();   
      ManageTiming();
   }


   private void ManageCollisionEvents(){
      List<CollisionEvent> collisionEvents = getEvents(CollisionEvent.class);
      if (collisionEvents == null|| collisionEvents.isEmpty()) return;
      for (CollisionEvent collisionEvent : collisionEvents) {
         if(collisionEvent.getGameObject().getComponent(Intelligent.class)==null) continue;
         collisionEvent.getGameObject().getComponent(Intelligent.class).getIA().apply(collisionEvent);
      }
      collisionEvents.clear();
   }


   private void ManageTiming(){
      final long currentTime = System.nanoTime();
      long elapsedTime = currentTime-previousTime;
      //System.out.println("elapsed moteur ia: "+elapsedTime);
      if( elapsedTime > DeltaBetweenCall){
         previousTime = currentTime;
         //System.out.println("DeltaBetweenCall ok ");
         for (Intelligent intelligent : intelligents) {
            intelligent.getIA().apply(intelligent, currentTime, previousTime);
         }
      }
   }




}
