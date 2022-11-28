package engine;

import game.Game;

public class App {

    public static void main(String[] args) throws Exception {
        Kernel.start(new Game());
    }
}
