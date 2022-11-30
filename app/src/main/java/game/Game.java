package game;

import game.entity.PlayerShoot;
import java.util.ArrayList;
import java.util.List;
import engine.Engine;
import engine.Kernel;
import engine.event.CollisionEvent;
import engine.event.DestroyEvent;
import engine.event.EventsManager;
import engine.event.StateEvent;
import engine.graphic.Displayable;
import engine.input.State;
import engine.physics.Physic;
import engine.physics.collisionReaction.IgnoreReaction;
import game.ai.AI;
import game.ai.AIEnnemis;
import game.ai.Intelligent;
import game.entity.EnemyShoot;
import game.entity.InvisibleWall;
import game.entity.Player;
import game.entity.enemies.Crab;

public class Game extends Engine{

    public Player player;
    public boolean PlayerShootAlive = false;


    public Game(EventsManager eventsManager){
        super(eventsManager);
    }

    public void update(){
        handleDestoyEvents();
        handleCollisionEvents();
    }

    private void handleDestoyEvents(){
        List<DestroyEvent> destroyEvents = getEvents(DestroyEvent.class);
        if(destroyEvents==null || destroyEvents.size()==0) return;
        for (DestroyEvent destroyEvent : destroyEvents)
            if(destroyEvent.getGameObject() instanceof PlayerShoot);
                PlayerShootAlive = false;
        destroyEvents.clear();
    }

    private void handleCollisionEvents(){
        List<CollisionEvent> collisionEvents = getEvents(CollisionEvent.class);
        if(collisionEvents==null || collisionEvents.size()==0) return;
        for (CollisionEvent collisionEvent : collisionEvents) {
            if(collisionEvent.getGameObject() instanceof Player && collisionEvent.getCollisions().get(0).getObstacle().getGameObject() instanceof EnemyShoot){
                player.takeDamage();
                if(player.isDead()){
                    Kernel.getInstance().removeGameObject(player);
                    //TODO
                    //TODOOOO
                }
            }
        }

        collisionEvents.clear();
    }

    // public void shootRules(){
    //     destroyShoot();
        
    //     List<CollisionEvent> colls = getEvents(CollisionEvent.class);
    //     if(colls == null || colls.size() == 0) return;
        
    //     System.out.println(colls.get(0));
        
    //     for(CollisionEvent col : colls){
    //         if(col.getGameObject() instanceof PlayerShoot)//est que l'autre n'est pas un EnemieShoot
    //         submit(new PlayerShootEvent(col.getGameObject()));
    //     }
    //     colls.clear();
    // }

    // private void destroyShoot(){
    //     List<PlayerShootEvent> shootsE = getEvents(PlayerShootEvent.class);
    //     if(shootsE == null || shootsE.size() == 0) return;
        
    //     System.out.println(shootsE.get(0));
        
    //     for(PlayerShootEvent shootE : shootsE){
    //         Kernel.getInstance().removeGameObject(shootE.getGameObject());
    //     }
    //     shootsE.clear();
    // }

    


    public void init() {
        int playerSize = 55;
        player = new Player(5, new Physic(Kernel.getInstance().getScreenWidth()/2+playerSize, Kernel.getInstance().getScreenHeight()-playerSize, playerSize, playerSize),
                new Displayable(Kernel.getInstance().getScreenWidth()/2+playerSize, Kernel.getInstance().getScreenHeight()-playerSize, playerSize, playerSize, 6, "/player/pacman_run1.png", "/player/pacman_run2.png", "/player/pacman_run3.png", "/player/pacman_run4.png"));

        Kernel.getInstance().addGameObject(player);

        initializeEnemies();
        initializeInvisibleWall();
    }

    
    private void initializeEnemies(){
        int enemiesSize = 55;
        int offset = enemiesSize+10;
        int maxParLigne = 10;
        int offsetInitial = 10;
        List<Crab> crabs = new ArrayList<>();
        AI aiEnnemis = new AIEnnemis(crabs);
        List<String> pngs = new ArrayList<>(List.of("3","2","2","1","1"));

        for (int rang = 0; rang < 50; rang++) {
            int nb = ((rang/maxParLigne)%5);
            String path = "/enemies/alien_"+pngs.get(nb)+".png";
            int rangEffectif = rang % maxParLigne;
            int column = rang/maxParLigne;

            Crab crab = new Crab(rang,
                new Physic     (offsetInitial+ offset*rangEffectif, offsetInitial + offset*column, enemiesSize-10, enemiesSize-10, new IgnoreReaction()),
                new Displayable(offsetInitial+ offset*rangEffectif, offsetInitial + offset*column, enemiesSize, enemiesSize, path),
                new Intelligent(aiEnnemis)
                );
            crabs.add(crab);

            Kernel.getInstance().addGameObject(crab);
        }
    }
        
    private void initializeInvisibleWall(){
        int screenWidth = Kernel.getInstance().getScreenWidth();
        int screenHeight = Kernel.getInstance().getScreenHeight();
        
        InvisibleWall wallLeft = new InvisibleWall(
        new Physic(-50, 0, 50, screenHeight));

        InvisibleWall wallRight = new InvisibleWall(
        new Physic(screenWidth, 0, 50, screenHeight));
        
        InvisibleWall wallTop = new InvisibleWall(new Physic(0, -100, screenWidth, 50));
        
        InvisibleWall wallBottom = new InvisibleWall(new Physic(0,  screenHeight, screenWidth, 50));

        Kernel.getInstance().addGameObject(wallLeft);
        Kernel.getInstance().addGameObject(wallRight);
        Kernel.getInstance().addGameObject(wallTop);
        Kernel.getInstance().addGameObject(wallBottom);
    }
    
    public void changeState(State state) {
        Kernel.getInstance().eventsManager.submit(new StateEvent(state));
    }

    public void PlayerShoot(){
        if(PlayerShootAlive) return;
        PlayerShootAlive = true;
        Displayable playerGraphic = player.getComponent(Displayable.class);
        PlayerShoot ps = new PlayerShoot(playerGraphic.getX(), playerGraphic.getY()-20);
        Kernel.getInstance().addGameObject(ps);
    }


}
    
