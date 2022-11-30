package game.ai;

import engine.physics.Physic;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import engine.Kernel;
import engine.event.CollisionEvent;
import engine.event.Event;
import engine.physics.Collision;
import game.entity.EnemyShoot;
import game.entity.InvisibleWall;
import game.entity.Player;
import game.entity.PlayerShoot;
import game.entity.enemies.Crab;
import game.entity.enemies.Enemies;
import java.lang.Math;
import java.util.Random.*;

public class AIEnnemis extends AI {

    final List<Crab> crabs;
    final double mid = Kernel.getInstance().getScreenWidth() / 2;
    final long DELAYBETWEENSHOOT = 2000000000; //ns
    final int MAXSALVOSHOOT = 7;
    boolean lastCollisonLeft = true;
    long lastShootSalvoTime = System.nanoTime();



    public AIEnnemis(List<Crab> crabs) {
        this.crabs = crabs;
        // System.out.println(mid);
    }

    @Override
    public void apply(Event event) {
        if (event instanceof CollisionEvent)
            GererDeplacement(event);
    }

    private void GererDeplacement(Event event) {
        CollisionEvent collisionEvent = (CollisionEvent) event;
        if (!(collisionEvent.getGameObject() instanceof Enemies))
            return;

        for (Collision collision : collisionEvent.getCollisions()) {

            // si ennemi touche un bord de l'ecran
            if (collision.getObstacle().getGameObject() instanceof InvisibleWall) {

                if (collision.getObj().getX() > mid && lastCollisonLeft == false)
                    return;
                if (collision.getObj().getX() < mid && lastCollisonLeft == true)
                    return;

                lastCollisonLeft = !lastCollisonLeft;
                // System.out.println(lastCollisonLeft);

                double currentDirection = collision.getObj().getGameObject().getComponent(Physic.class)
                        .getDirection();
                // System.out.println(" "+currentDirection);

                for (Crab crab : crabs) {
                    crab.getComponent(Physic.class).setDirection((currentDirection + 180) % 360);
                    crab.getComponent(Physic.class).setY(crab.getComponent(Physic.class).getY() + 15);
                }
                break;
            }

            // si ennemi touche un misible du player
            else if (collision.getObstacle().getGameObject() instanceof PlayerShoot) {
                //System.out.println("remove");
                this.crabs.remove(collision.getObj().getGameObject());
                Kernel.getInstance().removeGameObject(collision.getObj().getGameObject());
            }

            else if (collision.getObstacle().getGameObject() instanceof Player) {
                // TODO
            } else {
                //System.out.println("else ai crab");
            }

        }
    }

    @Override
    void apply(Intelligent intelligent, long currentTime, long previousTime) {
        GererMissiles(intelligent, currentTime, previousTime);
    }

    private void GererMissiles(Intelligent intelligent, long currentTime, long previousTime) {
        if(currentTime-lastShootSalvoTime<DELAYBETWEENSHOOT) return;
        lastShootSalvoTime = currentTime;
        
        int possibleNbShootPerSalvo = Math.min(crabs.size(), MAXSALVOSHOOT);
        Random rd = new Random();
        int nbShootPerSalvo = rd.nextInt(possibleNbShootPerSalvo);
        
        List<Integer> crabShooterIDs = new ArrayList<>(nbShootPerSalvo);
        
        for (int i=0; i<nbShootPerSalvo; i++) {
            crabShooterIDs.add( crabs.get(rd.nextInt(crabs.size())).getID() );
        }
        
        for (Crab crab : crabs) {
            //System.out.println(crabShooterIDs+" "+crab.getID());
            if(!crabShooterIDs.contains(crab.getID())) continue;
            //System.out.println("on va tirer");
            int y = (int)crab.getComponent(Physic.class).getY()+25;
            int x = (int)crab.getComponent(Physic.class).getX();
            EnemyShoot enemyShoot = new EnemyShoot(x, y);
            Kernel.getInstance().addGameObject(enemyShoot);
        }
    
    }

}
