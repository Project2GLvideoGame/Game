package game.IA.PathIA;

import game.GameObject;
import game.Physics.Coordinate;
import game.Physics.Physic;

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
