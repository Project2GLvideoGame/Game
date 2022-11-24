package game;

import java.util.ArrayList;
import java.util.List;

import engine.GameObject;
import engine.Kernel;
import engine.graphic.Displayable;
import engine.input.InputEngine;
import engine.input.State;
import engine.physics.Physic;
import game.ai.Intelligent;
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
        player = new Player(3, new Physic(100, 100, 60, 64),
                new Displayable(100, 100, 64, 64, 6, "/player/pacman_run1.png", "/player/pacman_run2.png", "/player/pacman_run3.png", "/player/pacman_run4.png"));

        GameObject wall = new GameObject(new Physic(350, 300, 50, 150),
                new Displayable(350, 300, 50, 150, "/wall.jpg"));


        kernel.addGameObject(player);
        kernel.addGameObject(wall);
    }

    public void changeState(State state) {
        inputEngine.changeState(state);
    }






    private void initializeEnnemis(){
        for (int i = 0; i < 50; i++) {
            int nb = ((i/10)%5) ;
            List<String> png = new ArrayList<>(List.of("3","2","2","1","1"));;

            Crab crab = new Crab(new Physic(100 +50* ((i % 10)) ,50 +50 *((i/10))  , 45, 45),new Displayable(100 +50* ((i % 10)) ,50 +50 *( (i/10))  , 45, 45, "/alien_"+png.get(nb)+".png"),new Intelligent(new AIAlgoEnnemis()));
            gameObjects.add(crab);
            graphicEngine.addDisplayable(crab.getComponent(Displayable.class));
            physicalEngine.addPhysicalObject(crab.getComponent(Physic.class));
            aiEngine.addIAObjectIntelligent(crab.getComponent(Intelligent.class));
        }

}

