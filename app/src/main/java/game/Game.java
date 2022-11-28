package game;

import java.util.ArrayList;
import java.util.List;

import engine.Kernel;
import engine.event.StateEvent;
import engine.graphic.Displayable;
import engine.input.State;
import engine.physics.Physic;
import game.ai.AIAlgoEnnemis;
import game.ai.Intelligent;
import game.entity.InvisibleWall;
import game.entity.Player;
import game.entity.PlayerShoot;
import game.entity.enemies.Crab;

public class Game {

    public Player player;

    public Game(){
        Kernel.start(this);
        initEntities();

        Kernel.getInstance().startGameThread();
    }

    public void update(){
        playerRules();
    }

    public void playerRules(){
        Displayable playerGraphic = player.getComponent(Displayable.class);
        if(player.isShooting()){
            Kernel.getInstance().addGameObject(new PlayerShoot(playerGraphic.getX()+playerGraphic.getWidth()/2, playerGraphic.getY()));
            player.disableShoot();
        }
    }


    public void initEntities() {
        int playerSize = 55;
        player = new Player(3, new Physic(Kernel.getInstance().getScreenWidth()/2+playerSize, Kernel.getInstance().getScreenHeight()-playerSize, playerSize, playerSize),
                new Displayable(Kernel.getInstance().getScreenWidth()/2+playerSize, Kernel.getInstance().getScreenHeight()-playerSize, playerSize, playerSize, 6, "/player/pacman_run1.png", "/player/pacman_run2.png", "/player/pacman_run3.png", "/player/pacman_run4.png"));

        Kernel.getInstance().addGameObject(player);

        initializeEnemies();
        initializeInvisibleWall();
    }

    
    private void initializeEnemies(){
        int enemiesSize = 55;
        int offset = 0;
        offset += enemiesSize;
        List<String> pngs = new ArrayList<>(List.of("3","2","2","1","1"));
        for (int i = 0; i < 50; i++) {
            int nb = ((i/10)%5);
            String path = "/enemies/alien_"+pngs.get(nb)+".png";
            Crab crab = new Crab(
                new Physic(10 + offset * ((i % 10)) ,10 + offset *((i/10)), enemiesSize, enemiesSize),
                new Displayable(10 + offset * ((i % 10)) ,10 + offset *( (i/10)), enemiesSize, enemiesSize, path),
                new Intelligent(new AIAlgoEnnemis())
                );
                
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
}
    
