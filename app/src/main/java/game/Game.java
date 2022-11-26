package game;

import java.util.ArrayList;
import java.util.List;

import engine.Kernel;
import engine.graphic.Displayable;
import engine.input.InputEngine;
import engine.input.State;
import engine.physics.Physic;
import game.ai.AIAlgoEnnemis;
import game.ai.Intelligent;
import game.ai.InvisibleWall;
import game.entity.Player;
import game.entity.PlayerShoot;
import game.entity.enemies.Crab;

public class Game {

    private final Kernel kernel;
    private final InputEngine inputEngine;
    public Player player;

    public Game(){
        kernel = new Kernel();
        inputEngine = new InputEngine();

        inputEngine.setGame(this);
        kernel.addKeyListener(inputEngine);

        initEntities();
        kernel.addGameObject(new PlayerShoot(400, 600));

        kernel.startGameThread();

    }

    public void initEntities() {
        int playerSize = 55;
        player = new Player(3, new Physic(kernel.getScreenWidth()/2+playerSize, kernel.getScreenHeight()-playerSize, playerSize, playerSize),
                new Displayable(kernel.getScreenWidth()/2+playerSize, kernel.getScreenHeight()-playerSize, playerSize, playerSize, 6, "/player/pacman_run1.png", "/player/pacman_run2.png", "/player/pacman_run3.png", "/player/pacman_run4.png"));

        kernel.addGameObject(player);

        initializeEnemis();
        initializeInvisibleWall();
    }

    
    private void initializeEnemis(){
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
                
            kernel.addGameObject(crab);
        }
    }
        
    private void initializeInvisibleWall(){
        int screenWidth = kernel.getScreenWidth();
        int screenHeight = kernel.getScreenHeight();
        
        InvisibleWall wallLeft = new InvisibleWall(
        new Physic(-50, 0, 50, screenHeight));

        InvisibleWall wallRight = new InvisibleWall(
        new Physic(screenWidth, 0, 50, screenHeight));
        
        InvisibleWall wallTop = new InvisibleWall(new Physic(0, -100, screenWidth, 50));
        
        InvisibleWall wallBottom = new InvisibleWall(new Physic(0,  screenHeight, screenWidth, 50));
        
        kernel.addGameObject(wallLeft);
        kernel.addGameObject(wallRight);
        kernel.addGameObject(wallTop);
        kernel.addGameObject(wallBottom);
    }
    
    public void changeState(State state) {
        inputEngine.changeState(state);
    }
}
    
