package engine.physics.collisionReaction;

import java.util.List;

import engine.physics.Collision;
import engine.physics.Coordinate;
import engine.physics.Physic;


public interface CollisionReaction {

    /**
     * Defini le calcul de la postion apres une collision
     * @param physical
     * @param beforeCollsionCoord
     * @param CollisonCoord lieu de la collision
     * @param collisions listes des obstacles de l'objet
     */
    public void setPositionAfterCollision(Physic physical, Coordinate beforeCollsionCoord, Coordinate CollisonCoord, List<Collision> collisions);
    
}
