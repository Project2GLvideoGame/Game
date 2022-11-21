package engine.ia.pathIA;

import java.util.concurrent.ThreadLocalRandom;

import engine.GameObject;
import engine.physics.Coordinate;
import engine.physics.Physic;

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
