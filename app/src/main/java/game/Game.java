package game;

import game.entity.PlayerShoot;
import java.util.ArrayList;
import java.util.List;

import engine.GameObject;
import engine.Kernel;
import engine.event.StateEvent;
import engine.graphic.Displayable;
import engine.input.State;
import engine.physics.Physic;
import engine.physics.collisionReaction.IgnoreReaction;
import game.ai.AI;
import game.ai.AIEnnemis;
import game.ai.Intelligent;
import game.entity.InvisibleWall;
import game.entity.Player;
import game.entity.enemies.Crab;

public class Game {

    public Player player;

    public Game(){
        
        Kernel.start(this);
        initEntities();

        //Kernel.getInstance().startGameThread();
    }

    public void update(){
        //playerRules();
    }

    
    //private void PleyrShootRules(){}
    //private void playerRules(){}


    public void initEntities() {
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
            Crab crab = new Crab(
                new Physic     (offsetInitial+ offset*rangEffectif, offsetInitial + offset*column, enemiesSize, enemiesSize, new IgnoreReaction()),
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
}
    
