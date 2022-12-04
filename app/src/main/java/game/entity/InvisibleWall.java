package game.entity;

import engine.Component;
import engine.GameObject;


    /**
     * Invisible wall for a game
     **/
    public class InvisibleWall extends GameObject{
    
    /**
     * @param in_components list of componnents for an invisible wall
     */
    public InvisibleWall(Component ...in_components){
        super(in_components);
    }
}
