package game;

import engine.event.*;
import game.entity.PlayerShoot;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import engine.Engine;
import engine.GameObject;
import engine.Kernel;
import engine.graphic.Displayable;
import engine.input.State;
import engine.physics.Physic;
import engine.physics.collisionReaction.IgnoreReaction;
import engine.sound.Soundable;
import engine.sound.Track;
import game.ai.AI;
import game.ai.AIEnnemis;
import game.ai.Intelligent;
import game.entity.BottomWall;
import game.entity.EnemyShoot;
import game.entity.InvisibleWall;
import game.entity.Player;
import game.entity.enemies.Crab;
import game.entity.enemies.Enemies;
import game.state.GameOverState;
import game.state.GameState;

public class Game extends Engine{

    public Player player;
    public boolean PlayerShootAlive = false;


    public Game(EventsManager eventsManager){
        super(eventsManager);
    }


    public void init() {
        GameObject backGround = new GameObject(
            new Displayable(0, 0, Kernel.getInstance().getScreenWidth(), Kernel.getInstance().getScreenHeight(), 20, "/Background/Background1.png", "/Background/Background2.png", "/Background/Background3.png", "/Background/Background4.png")
            );

        int playerSize = 75;
        player = new Player(5, new Physic(Kernel.getInstance().getScreenWidth()/2+playerSize, Kernel.getInstance().getScreenHeight()-playerSize, playerSize, playerSize),
                new Displayable(Kernel.getInstance().getScreenWidth()/2+playerSize, Kernel.getInstance().getScreenHeight()-playerSize, playerSize, playerSize, 6, "/player/PlayerSpaceShip1.png", "/player/PlayerSpaceShip2.png", "/player/PlayerSpaceShip3.png", "/player/PlayerSpaceShip4.png")
                ,new Soundable(new Track(StateEvent.class.getName(), "/audio/music.wav"),new Track("gameOver", "/audio/gameOver.wav"))

        );

        Kernel.getInstance().addGameObject(backGround);
        Kernel.getInstance().addGameObject(player);

        initializeEnemies();
        initializeInvisibleWall();
        initializeUI();
        initializeSound();
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
            String path = "/enemies/Alien_"+pngs.get(nb);
            int rangEffectif = rang % maxParLigne;
            int column = rang/maxParLigne;

            Crab crab = new Crab(rang,
                new Physic     (offsetInitial+ offset*rangEffectif, offsetInitial + offset*column, enemiesSize-10, enemiesSize-10, new IgnoreReaction()),
                new Displayable(offsetInitial+ offset*rangEffectif, offsetInitial + offset*column, enemiesSize, enemiesSize, 8, path+"/alien_1.png", path+"/alien_2.png", path+"/alien_3.png", path+"/alien_4.png"),
                new Soundable(new Track("enemyDead","/audio/enemyDead.wav" )),
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
        
        InvisibleWall wallBottom = new BottomWall(new Physic(0,  screenHeight, screenWidth, 50));

        Kernel.getInstance().addGameObject(wallLeft);
        Kernel.getInstance().addGameObject(wallRight);
        Kernel.getInstance().addGameObject(wallTop);
        Kernel.getInstance().addGameObject(wallBottom);
    }

    public void initializeUI(){
        Kernel.getInstance().addGameObject(player.getLifePointGameObject());
    }

    public void initializeSound(){
        player.getComponent(Soundable.class).playSoundtrack(StateEvent.class.getName());

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
        if(collisionEvents==null || collisionEvents.isEmpty()) return;

        for (CollisionEvent collisionEvent : collisionEvents) {
            GameObject gameObject = collisionEvent.getCollisions().get(0).getObstacle().getGameObject();
            if(collisionEvent.getGameObject() instanceof Player && gameObject instanceof EnemyShoot)
                handlePlayerShootColl(collisionEvent);
            else if(collisionEvent.getGameObject() instanceof Player && gameObject instanceof Enemies)
                endGame();
            else if(collisionEvent.getGameObject() instanceof Enemies && gameObject instanceof BottomWall)
                endGame();
            else if (collisionEvent.getGameObject() instanceof PlayerShoot  && gameObject instanceof Enemies) {
                submit(new SoundEvent(gameObject,"enemyDead"));
            }
            else if (collisionEvent.getGameObject() instanceof Player  && gameObject instanceof PlayerShoot) {

            }
        }

        collisionEvents.clear();
    }
    

    public void onlyOnePlayerShootAtTime(){
        if(PlayerShootAlive) return;
        PlayerShootAlive = true;
        Displayable playerGraphic = player.getComponent(Displayable.class);
        PlayerShoot ps = new PlayerShoot(playerGraphic.getX(), playerGraphic.getY()-20);
        submit(new ShootEvent(ps));
        //submit(new SoundEvent(ps,"shoot"));
        Kernel.getInstance().addGameObject(ps);
    }


    private void handlePlayerShootColl(CollisionEvent collisionEvent){
        Kernel.getInstance().removeGameObject(collisionEvent.getCollisions().get(0).getObstacle().getGameObject());
        player.takeDamage();
        if(! player.isDead()) return;
        submit(new SoundEvent(player,"gameOver"));
        endGame();
    }



    private void endGame(){
        //System.out.println("KILLLLLLLLLLL PLAYER");
        player.getComponent(Soundable.class).stopAllMusic();
        Kernel.getInstance().removeGameObject(player);
        StateEvent stateEvent = new StateEvent(new GameOverState(), new Displayable(0, 0, Kernel.getInstance().getScreenWidth(), Kernel.getInstance().getScreenHeight(), "/gameOver.png") );
        submit(stateEvent);
        //Kernel.getInstance().graphicEngine.update();
        //Kernel.getInstance().gameOver = true;
        //Kernel.getInstance().addGameObject(new GameObject());
    }

    



}
    
