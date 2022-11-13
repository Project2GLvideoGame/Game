package game.IA;

import game.Component;
import game.GameObject;
import game.Physic.Coordinate;
import game.Physic.Physical;

public abstract class IA extends Component{
    GameObject go;
    Coordinate coordinate;

    public IA(GameObject go, Coordinate destination) {
        this.go = go;
        this.coordinate = destination;
    }

    public IA(GameObject go,   GameObject goDestination) {
        this.coordinate = go.getComponent(Physical.class).getCoordinate();
        this.go = go;
    }
    
    abstract void executeOrders();
    
}
