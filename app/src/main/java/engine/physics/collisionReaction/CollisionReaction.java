package engine.physics.collisionReaction;

import java.util.List;

import engine.physics.Collision;
import engine.physics.Coordinate;
import engine.physics.Physic;


public interface CollisionReaction {

    public void setPositionAfterCollision(Physic physical, Coordinate beforeCollsionCoord, Coordinate CollisonCoord, List<Collision> collisions);
    
}
