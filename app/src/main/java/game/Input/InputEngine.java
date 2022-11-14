package game.Input;
import game.Kernel;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


public class InputEngine {
    
    public EventHandler<? super KeyEvent> eventHandlerPressed;
    public EventHandler<? super KeyEvent> eventHandlerReleased;

    private State currentState = new GameState();

    public void changeState(State state) {
        this.currentState = state;
    }

    public InputEngine(Kernel kernel) {
        eventHandlerPressed = (EventHandler<KeyEvent>) event -> {
            switch (event.getCode()) {
                case UP -> currentState.up(kernel);
                case RIGHT -> currentState.right(kernel);
                case DOWN -> currentState.down(kernel);
                case LEFT -> currentState.left(kernel);
                case P -> currentState.p(kernel);
                case ESCAPE -> currentState.escape(kernel);
                default -> System.out.println(event.getCode());
            }
        };

        eventHandlerReleased = (EventHandler<KeyEvent>) event -> {
            switch (event.getCode()) {
                case UP -> currentState.up(kernel);
                case RIGHT -> currentState.right(kernel);
                case DOWN -> currentState.down(kernel);
                case LEFT -> currentState.left(kernel);
                case P -> currentState.p(kernel);
                case ESCAPE -> currentState.escape(kernel);
                default -> System.out.println(event.getCode());
            }
        };
    }

/*
 * 
 * 
 private void eventSwitch( KeyEvent event){
         switch(event.getCode()){

                 case UP:
                 currentState.up(InputEngine.this);
                 System.out.println("pressed");
                     break;

                 case RIGHT:
                     currentState.right(InputEngine.this);
                     break;

                 case DOWN:
                 currentState.down(InputEngine.this);
                     break;

                 case LEFT:
                 currentState.left(InputEngine.this);
                     break;

                 case ESCAPE:
                 currentState.pause(InputEngine.this);
                 break;
                 
                 default:
                     break;

             }
 }
 */

}
