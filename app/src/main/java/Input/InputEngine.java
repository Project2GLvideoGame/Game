package Input;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import Input.State.GameState;
import Input.State.State;

public class InputEngine {
    
    EventHandler<? super KeyEvent> eventHandler;
    private State currentState = new GameState();

    
    public InputEngine(){
        eventHandler = new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                
                switch(event.getCode()){

                    case UP:
                    currentState.up(InputEngine.this);
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

                    case P:
                    currentState.pause(InputEngine.this);
                    break;
                    
                    default:
                        break;

                }
                
            }
    };

    
    }
}
