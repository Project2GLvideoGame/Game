package engine.ai;

import engine.Component;

public class Intelligent extends Component {
    
    AI ia ;
    
    public Intelligent(AI ia) {
        this.ia = ia;
    }

    public AI getIA(){
        return ia;
    }

}
