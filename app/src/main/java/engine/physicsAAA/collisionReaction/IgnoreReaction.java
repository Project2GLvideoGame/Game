package engine.physicsAAA.collisionReaction;

import static engine.physicsAAA.Utils.*;

import java.util.List;

import engine.physicsAAA.Collision;
import engine.physicsAAA.Coordinate;
import engine.physicsAAA.Physic;


public class IgnoreReaction implements CollisionReaction {

    @Override
    public void setPositionAfterCollision(Physic physical, Coordinate beforeCollsionCoord, Coordinate CollisonCoord, List<Collision> collisions) {
        return;
    }





}
