package game.ai;

import engine.event.CollisionEvent;
import engine.event.EnnemisCollisionEvent;
import engine.event.Event;
import engine.event.EventsManager;
import engine.physics.Collision;
import engine.physics.Physic;
import game.entity.InvisibleWall;
import game.entity.PlayerShoot;
import game.entity.enemies.Enemies;

public class AIAlgoEnnemis extends AI {
    

        public void apply(Event event , EventsManager eventsManager){
            if(event instanceof CollisionEvent ){
               
                if( ((CollisionEvent)event).getGameObject() instanceof Enemies){

                    
                    for(Collision coll : ((CollisionEvent)event).getCollision()) {
                    
                        if(coll.getObstacle().getGameObject() instanceof InvisibleWall){
                            eventsManager.submit(new EnnemisCollisionEvent( coll.getObj().getGameObject(),(((180 + coll.getObj().getGameObject().getComponent(Physic.class).getDirection()) % 360))));
                            // coll.getobj().getGameObject().getComponent(Physic.class).setDirection((180 + coll.getobj().getGameObject().getComponent(Physic.class).getDirection()) % 360);
                            // coll.getobj().getGameObject().getComponent(Physic.class).setY((coll.getobj().getGameObject().getComponent(Physic.class).getY() + 50 ));

                        }

                        if(coll.getObstacle().getGameObject() instanceof PlayerShoot && coll.getObj().getGameObject() instanceof Enemies){
                            ((Enemies)coll.getObj().getGameObject()).died();
                        }
                    }
                    

                }
                

                // collision avec mur invisible
                // collision avec missile player
                // collision avec missile ennemis
                // collision avec 
            }
        }
}