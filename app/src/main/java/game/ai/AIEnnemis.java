package game.ai;

import engine.physics.Physic;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import engine.Kernel;
import engine.event.CollisionEvent;
import engine.event.Event;
import engine.graphic.Displayable;
import engine.physics.Collision;
import game.entity.EnemyShoot;
import game.entity.InvisibleWall;
import game.entity.Player;
import game.entity.PlayerShoot;
import game.entity.enemies.Crab;
import game.entity.enemies.Enemies;
import java.lang.Math;

    /**
     * AI for basic ennemis
     **/
public class AIEnnemis extends AI {

    final Random rd = new Random();
    final List<Crab> crabs;
    final double MID = Kernel.getInstance().getScreenWidth() / 2;
    final long DELAYBETWEENSHOOT = 2000000000; //ns
    final int MAXSALVOSHOOT = 8;
    boolean lastCollisonLeft = true;
    long lastShootSalvoTime = System.nanoTime();


    /**
     * Create AI for basic ennemis then can communicate beetween all of them 
     * @param crabs list of all basics ennemis
     */
    public AIEnnemis(List<Crab> crabs) {
        this.crabs = crabs;
    }


    @Override
    public void apply(Event event) {
        if (event instanceof CollisionEvent)
            GererDeplacement(event);
    }


    @Override
    void apply(Intelligent intelligent, long currentTime, long previousTime) {
        GererMissiles(intelligent, currentTime, previousTime);
    }



    private void GererDeplacement(Event event) {
        CollisionEvent collisionEvent = (CollisionEvent) event;
        if (!(collisionEvent.getGameObject() instanceof Enemies))
            return;

        for (Collision collision : collisionEvent.getCollisions()) {

            // si ennemi touche un bord de l'ecran
            if (collision.getObstacle().getGameObject() instanceof InvisibleWall) {

                if (collision.getObj().getX() > MID && lastCollisonLeft == false)
                    return;
                if (collision.getObj().getX() < MID && lastCollisonLeft == true)
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
                System.out.println("Collid plyershoot");
                this.crabs.remove(collision.getObj().getGameObject());
                Kernel.getInstance().addToScore(((Enemies)collision.getObj().getGameObject()).getPoint());
                Kernel.getInstance().removeGameObject(collision.getObj().getGameObject());
            }

            else if (collision.getObstacle().getGameObject() instanceof Player) {
                Player player = (Player)(collision.getObstacle().getGameObject());
                player.kill();
            } else {
                //System.out.println("ELSEEEEEEEE ai crab");
            }

        }
    }



    private void GererMissiles(Intelligent intelligent, long currentTime, long previousTime) {
        if(currentTime-lastShootSalvoTime<DELAYBETWEENSHOOT) return;
        lastShootSalvoTime = currentTime;
        
        int possibleNbShootPerSalvo = Math.min(crabs.size(), MAXSALVOSHOOT);
        int nbShootPerSalvo = rd.nextInt(possibleNbShootPerSalvo);
        
        List<Integer> crabShooterIDs = new ArrayList<>(nbShootPerSalvo);
        
        for (int i=0; i<nbShootPerSalvo; i++) {
            crabShooterIDs.add( crabs.get(rd.nextInt(crabs.size())).getID() );
        }
        
        for (Crab crab : crabs) {
            //System.out.println(crabShooterIDs+" "+crab.getID());
            if(!crabShooterIDs.contains(crab.getID())) continue;
            //System.out.println("on va tirer");
            int yShoot = (int)crab.getComponent(Physic.class).getY()+25;
            int xShoot = (int)crab.getComponent(Physic.class).getX();
            double speedShoot = (crab.getID()<10)? 8:(crab.getID()<30)? 5:2;
            EnemyShoot enemyShoot = new EnemyShoot(xShoot, yShoot, speedShoot);
            if(speedShoot == 2)
                enemyShoot.getComponent(Displayable.class).setAssets(5,"/enemies/Alien_1/alien1Shoot_1.png","/enemies/Alien_1/alien1Shoot_2.png","/enemies/Alien_1/alien1Shoot_3.png","/enemies/Alien_1/alien1Shoot_4.png","/enemies/Alien_1/alien1Shoot_5.png");
            if(speedShoot == 5)
                enemyShoot.getComponent(Displayable.class).setAssets(5,"/enemies/Alien_2/alien2Shoot_1.png","/enemies/Alien_2/alien2Shoot_2.png","/enemies/Alien_2/alien2Shoot_3.png","/enemies/Alien_2/alien2Shoot_4.png");
            if(speedShoot == 8)
                enemyShoot.getComponent(Displayable.class).setAssets(5,"/enemies/Alien_3/alien3Shoot_1.png","/enemies/Alien_3/alien3Shoot_2.png","/enemies/Alien_3/alien3Shoot_3.png","/enemies/Alien_3/alien3Shoot_4.png");
            Kernel.getInstance().addGameObject(enemyShoot);
        }
    
    }






}
