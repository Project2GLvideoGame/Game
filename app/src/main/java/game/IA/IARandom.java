package game.IA;

import java.util.concurrent.ThreadLocalRandom;

import game.GameObject;
import game.Physic.Coordinate;
import game.Physic.Physical;

public class IARandom extends IA {

    public IARandom(GameObject go, Coordinate destination) {
        super(go, destination);
    }

    @Override
    void executeOrders() {
        // TODO : set speed ?
        // TODO une boucle qui change la direction a chaque collision
        go.getComponent(Physical.class).setDirection( ThreadLocalRandom.current().nextInt(0, 360));
    }
    
}
