package engine.ia.pathIA;

import engine.GameObject;
import engine.physics.Coordinate;
import engine.physics.Physic;

public class IASimple extends IA{

    public IASimple(GameObject go, Coordinate destination) {
        super(go, destination);
    }

    public IASimple(GameObject go,   GameObject goDestination) {
        super(go, goDestination);

    }

    @Override
    void executeOrders() {
        // TODO : set speed ?
        go.getComponent(Physic.class).setDestinationCoord(coordinate);
    }
    
}
