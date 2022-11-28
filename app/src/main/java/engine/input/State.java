package engine.input;

import game.Game;

public interface State {

    void upPressed(Game game);
    void upReleased(Game game);
    void downPressed(Game game);
    void downReleased(Game game);
    void rightPressed(Game game);
    void rightReleased(Game game);
    void leftPressed(Game game);
    void leftReleased(Game game);
    void escapePressed(Game game);
    void escapeReleased(Game game);
    void pPressed(Game game);
    void pReleased(Game game);

}
