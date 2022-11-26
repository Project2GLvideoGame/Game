package engine.physicsAAA.collisionReaction;

import java.util.List;

import engine.physicsAAA.Collision;
import engine.physicsAAA.Coordinate;
import engine.physicsAAA.Physic;


public interface CollisionReaction {

    public void setPositionAfterCollision(Physic physical, Coordinate beforeCollsionCoord, Coordinate CollisonCoord, List<Collision> collisions);
    
}
