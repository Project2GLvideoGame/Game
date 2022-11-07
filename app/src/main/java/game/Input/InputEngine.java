package game.Input;
import game.Input.State.GameState;
import game.Input.State.State;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class InputEngine {
    
    public EventHandler<? super KeyEvent> eventHandlerPressed;
    public EventHandler<? super KeyEvent> eventHandlerReleased;

    private State currentState = new GameState();

    public void changeState(State state){
        this.currentState = state;
    }
    
    public InputEngine(){

        eventHandlerPressed = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()){

                    case UP:
                        currentState.up(InputEngine.this);
                        System.out.println("UP pressed");
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
        };

        eventHandlerReleased =  new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()){

                    case UP:
                    currentState.up(InputEngine.this);
                    System.out.println("UP released");
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

                }            } 
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
