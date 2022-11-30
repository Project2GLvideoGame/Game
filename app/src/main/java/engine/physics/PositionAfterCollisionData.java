package engine.physics;

import java.util.List;

public class PositionAfterCollisionData {

    Physic physic;
    Coordinate beforeCollCoord;
    Coordinate naiveCoord;
    List<Collision> collisions;

    public PositionAfterCollisionData(Physic physic, Coordinate beforeCollCoord, Coordinate naiveCoord, List<Collision> collisions) {
        this.physic = physic;
        this.beforeCollCoord = beforeCollCoord;
        this.naiveCoord = naiveCoord;
        this.collisions = collisions;
    }

    public Physic getPhysic(){
        return this.physic;
    }

    public Coordinate getBeforeCollCoord() {
        return beforeCollCoord;
    }

    public Coordinate getNaiveCoord() {
        return naiveCoord;
    }

    public List<Collision> getCollisions() {
        return collisions;
    }

    
}
