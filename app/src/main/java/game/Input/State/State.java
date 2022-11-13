package game.Input.State;

import game.Input.KeyHandler;

public interface State {

    void up(KeyHandler input);
    void right(KeyHandler input);
    void down(KeyHandler input);
    void left(KeyHandler input);
    void pause(KeyHandler input);

    
}
