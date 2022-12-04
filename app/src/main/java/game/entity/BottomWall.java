package game.entity;

import engine.Component;
    /**
     * BottomWall Object
     **/
public class BottomWall extends InvisibleWall{
    
    /**
     * Bottom wall for a game
     * @param in_components List of componnent defining a bottomWall
     */
    public BottomWall(Component ...in_components){
        super(in_components);
    }
}
