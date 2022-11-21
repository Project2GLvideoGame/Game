package game.IA.PathIA;

import java.util.concurrent.ThreadLocalRandom;

import game.GameObject;
import game.Physics.Coordinate;
import game.Physics.Physic;

public class IARandom extends IA {

    public IARandom(GameObject go, Coordinate destination) {
        super(go, destination);
    }

    @Override
    void executeOrders() {
        // TODO : set speed ?
        // TODO une boucle qui change la direction a chaque collision
        go.getComponent(Physic.class).setDirection( ThreadLocalRandom.current().nextInt(0, 360));
    }
    
}
