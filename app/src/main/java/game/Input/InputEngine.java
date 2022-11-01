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

    //TODO: Change GraphiqueEngine start
   /*
   public void start(Stage stage) throws Exception {
       InputEngine inputEngine = new InputEngine();
       Scene scene = new Scene(createContent(), width, height);
       scene.setOnKeyPressed(inputEngine.eventHandlerPressed);
       scene.setOnKeyReleased(inputEngine.eventHandlerPressed);
       stage.setScene(scene);
       stage.show();
   }
    */ 

    

    
    public InputEngine(){

        eventHandlerPressed = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                eventSwitch(event);
            }
    };

        eventHandlerReleased =  new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                eventSwitch(event);
            } 
        };
    }

    private void eventSwitch( KeyEvent event){
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

}
