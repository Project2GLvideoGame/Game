package game;

import java.util.ArrayList;
import java.util.List;

import engine.GameObject;
import engine.Kernel;
import engine.graphic.Displayable;
import engine.input.InputEngine;
import engine.input.State;
import engine.physics.Physic;
import game.ai.AIAlgoEnnemis;
import game.ai.Intelligent;
import game.ai.InvisibleWall;
import game.entity.Player;
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

        kernel.startGameThread();

        launch();
    }

    public void launch() {
        // player = new Player(3, new Physic(100, 100, 60, 64),
        //         new Displayable(100, 100, 64, 64, 6, "/player/pacman_run1.png", "/player/pacman_run2.png", "/player/pacman_run3.png", "/player/pacman_run4.png"));

        // GameObject wall = new GameObject(new Physic(350, 300, 50, 150),
        //         new Displayable(350, 300, 50, 150, "/wall.jpg"));

        // kernel.addGameObject(player);
        // kernel.addGameObject(wall);

        initializeEnemis();
        initializeInvisibleWall();
    }

    public void changeState(State state) {
        inputEngine.changeState(state);
    }






    private void initializeEnemis(){
        List<String> pngs = new ArrayList<>(List.of("3","2","2","1","1"));
        for (int i = 0; i < 50; i++) {
            int nb = ((i/10)%5) ;
            String path = "/enemies/alien_"+pngs.get(nb)+".png";
            Crab crab = new Crab(
                new Physic(100 +50* ((i % 10)) ,50 +50 *((i/10)), 45, 45),
                new Displayable(100 +50* ((i % 10)) ,50 +50 *( (i/10)), 45, 45, path),
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

        InvisibleWall wallTop = new InvisibleWall(new Physic(0, -50, screenWidth, 50));

        InvisibleWall wallBottom = new InvisibleWall(new Physic(0,  screenHeight, screenWidth, 50));
        
        kernel.addGameObject(wallLeft);
        kernel.addGameObject(wallRight);
        kernel.addGameObject(wallTop);
        kernel.addGameObject(wallBottom);
    }

}

