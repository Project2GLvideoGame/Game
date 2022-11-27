package engine.input;

import game.Game;

public interface State {

    void up(Game game);
    void right(Game game);
    void down(Game game);
    void left(Game game);
    void escape(Game game);
    void p(Game game);
}
