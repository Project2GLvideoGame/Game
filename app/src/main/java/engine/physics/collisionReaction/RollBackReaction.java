package engine.physics.collisionReaction;

import static engine.physics.Utils.*;

import java.util.List;

import engine.physics.Collision;
import engine.physics.Coordinate;
import engine.physics.Physic;

/**
 * Apres une collision on replace l'objet à sa derniere position
 * avant la collision,
 * Permet de s'arreter contre un mur par exemple
 */
public class RollBackReaction implements CollisionReaction {

    @Override
    public void setPositionAfterCollision(Physic physical, Coordinate beforeCollsionCoord, Coordinate CollisonCoord, List<Collision> collisions) {
        physical.setCoordinate(beforeCollsionCoord);
        return;
    }





}
