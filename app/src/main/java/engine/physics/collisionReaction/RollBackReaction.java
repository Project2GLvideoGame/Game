package engine.physics.collisionReaction;

import java.util.List;
import engine.physics.Collision;
import engine.physics.Coordinate;
import engine.physics.Physic;
import static engine.physics.Utils.*;


public class RollBackReaction implements CollisionReaction {

    @Override
    public void setPositionAfterCollision(Physic physical, Coordinate beforeCollsionCoord, Coordinate CollisonCoord, List<Collision> collisions) {
        physical.setCoordinate(beforeCollsionCoord);
        return;
    }





}
