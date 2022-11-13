package game.IA;

import game.GameObject;
import game.Physic.Coordinate;
import game.Physic.Physical;

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
        go.getComponent(Physical.class).setDestinationCoord(coordinate);
    }
    
}
