package engine.input;

import java.awt.event.*;
import java.util.List;

import engine.Engine;
import engine.event.EventsManager;
import engine.event.StateEvent;
import game.Game;
import game.state.GameState;

/**
 * Le moteur d'entrée permet de détecter les événements liés aux appuis de touches par le joueur. Pour
 * cela il implémente KeyListener et utilise aussi le patron de conception State.
 */
public class InputEngine extends Engine implements KeyListener {

    private State currentState = new GameState();
    private Game game;

    /**
     * Permet de créer un moteur d'entrée.
     * @param game Une instance de jeu, correspondant au Gameplay
     * @param eventsManager Une instance d'EventManager permettant au moteur d'entrée
     *                      de suivre les événements liés aux changements d'états
     */
    public InputEngine(Game game, EventsManager eventsManager) {
        super(eventsManager);
        this.game = game;
    }

    /**
     * Permet au moteur de se mettre à jour, comme tous les autres moteurs.
     * Le moteur d'entrée se rafraîchit simplement en changeant son état courant
     * s'il détecte via le EventManager, que de nouveaux StateEvents ont été soumis.
     */
    public void update() {
        List<StateEvent> stateEvents = getEvents(StateEvent.class);
        if(stateEvents != null) {
            for (StateEvent stateEvent : stateEvents) {
                changeState(stateEvent.getNewState());
            }
            stateEvents.clear();
        }
    }

    public State getCurrentState() {
        return currentState;
    }

    /**
     * Permet au moteur d'entrée de changer son état courant.
     * @param state Le prochain état souhaité
     */
    public void changeState(State state) {
        this.currentState = state;
    }

    /**
     * Permet de changer l'instance Gameplay du moteur d'entrée.
     * @param game La nouvelle instance
     */
    public void setGame(Game game){
        this.game = game;
    }

    /**
     * Permet de récupérer l'instance du Gameplay.
     * @return L'instance Gameplay du moteur d'entrée
     */
    public Game getGame(){return game;}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Est appelée lorsqu'une touche est pressée. La touche est passée en paramètre de la fonction et déclenchera
     * la méthode associée à cette touche de l'état courant à l'aide d'un switch sur l'ensemble des touches que nous
     * proposons.
     * @param e L'événement (appui sur une touche) associé, sous forme de KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                currentState.upPressed(game);
                break;
            case KeyEvent.VK_RIGHT:
                currentState.rightPressed(game);
                break;
            case KeyEvent.VK_DOWN:
                currentState.downPressed(game);
                break;
            case KeyEvent.VK_LEFT:
                currentState.leftPressed(game);
                break;
            case KeyEvent.VK_P:
                currentState.pPressed(game);
                break;
            case KeyEvent.VK_ESCAPE:
                currentState.escapePressed(game);
                break;
            default:
                break;
        }

    }

    /**
     * Est appelée lorsqu'une touche est relâchée. La touche est passée en paramètre de la fonction et déclenchera
     * la méthode associée à cette touche de l'état courant à l'aide d'un switch sur l'ensemble des touches que nous
     * proposons.
     * @param e L'événement (touche relâchée) associé, sous forme de KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                currentState.upReleased(game);
                break;
            case KeyEvent.VK_RIGHT:
                currentState.rightReleased(game);
                break;
            case KeyEvent.VK_DOWN:
                currentState.downReleased(game);
                break;
            case KeyEvent.VK_LEFT:
                currentState.leftReleased(game);
                break;
            case KeyEvent.VK_P:
                currentState.pReleased(game);
                break;
            case KeyEvent.VK_ESCAPE:
                currentState.escapeReleased(game);
                break;
            default:
                break;
        }
    }

}
