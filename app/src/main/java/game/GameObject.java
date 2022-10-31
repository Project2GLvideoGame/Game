package game;

import game.Graphic.Displayable;
import game.Physic.Physical;

public class GameObject {
        
    private Displayable displayable;
    private Physical physical;

    public GameObject(Displayable displayable,Physical physical) {
        this.displayable = displayable;
        this.physical = physical;
    }

    public Displayable getDisplayable() {
        return displayable;
    }

    public Physical getPhysical(){
        return physical;

    } 


}
