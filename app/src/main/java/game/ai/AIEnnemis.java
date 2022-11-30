package game.ai;

import engine.physics.Physic;
import engine.event.EnnemisCollisionEvent;
import engine.event.EventsManager;

import java.util.List;

import engine.Kernel;
import engine.event.CollisionEvent;
import engine.event.Event;
import engine.physics.Collision;
import game.entity.InvisibleWall;
import game.entity.Player;
import game.entity.PlayerShoot;
import game.entity.enemies.Crab;
import game.entity.enemies.Enemies;

public class AIEnnemis extends AI {
    
    int c = 0;
    final List<Crab> crabs ;
    final double mid = Kernel.getInstance().getScreenWidth()/2;
    boolean lastCollisonLeft = true;

    public AIEnnemis(List<Crab> crabs ) {
        this.crabs = crabs;
        //System.out.println(mid);
    }

    @Override
    public void apply(Event event) {
        //if (!(event instanceof CollisionEvent)) return;
        CollisionEvent collisionEvent = (CollisionEvent) event;
        if (!(collisionEvent.getGameObject() instanceof Enemies)) return;
        
        for (Collision collision : collisionEvent.getCollisions()) {
            // System.out.println("Coll type, Obj = " + collision.getObj().getGameObject());
            // System.out.println("Obstacle = " + collision.getObstacle().getGameObject());
            
            //si ennemi touche un bord de l'ecran
            if (collision.getObstacle().getGameObject() instanceof InvisibleWall) {
                //System.out.println(collision.getObstacle().getGameObject().getClass());
                //System.out.println(collision.getObj().getGameObject().getClass()+" "+c);
                //System.out.println(collision.getObstacle().getGameObject().getClass()+" "+c);
                //System.out.println(collision.getOverlap().getX()+" "+collision.getOverlap().getX()+" "+c);
                //c++;

                
                if(collision.getObj().getX()>mid && lastCollisonLeft==false) return;
                if(collision.getObj().getX()<mid && lastCollisonLeft==true) return;
                
                lastCollisonLeft = !lastCollisonLeft;
                //System.out.println(lastCollisonLeft);
                
                double currentDirection = collision.getObj().getGameObject().getComponent(Physic.class).getDirection();
                //System.out.println(" "+currentDirection);
                
                for (Crab crab : crabs) {
                    crab.getComponent(Physic.class).setDirection((currentDirection+180)%360);
                    crab.getComponent(Physic.class).setY(crab.getComponent(Physic.class).getY()+15);
                }
                break;
            }
            //si ennemi touche un missile du player
            else if (collision.getObstacle().getGameObject() instanceof PlayerShoot) {
                //System.out.println("remove");
                Kernel.getInstance().removeGameObject(collision.getObj().getGameObject());
                Kernel.getInstance().removeGameObject(collision.getObstacle().getGameObject());
            }
            else if (collision.getObstacle().getGameObject() instanceof Player) {
                Player player = (Player)collision.getObstacle().getGameObject();
                player.takeDamage();
            }


        }
    }



}
