package game.Input.State;

import game.Input.InputEngine;

public interface State {

    void up(InputEngine input);
    void right(InputEngine input);
    void down(InputEngine input);
    void left(InputEngine input);
    void pause(InputEngine input);

    
}
