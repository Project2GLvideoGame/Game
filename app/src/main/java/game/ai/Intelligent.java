package game.ai;

import engine.Component;

/**
 * Intelligent Component : an Object with an AI
 **/
public class Intelligent extends Component {
    
    private AI ai ;
    
    /**
     * Generate an intellignet Object
     * @param ai Brain of an this Object
     */
    public Intelligent(AI ai) {
        this.ai = ai;
    }

    /**
     * @return The AI of the Intelligent Object
     */
    public AI getIA(){
        return ai;
    }

}
