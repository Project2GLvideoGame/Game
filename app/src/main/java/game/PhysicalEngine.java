package game;

import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;

public class PhysicalEngine {

    List<Physical> physicalObjects;

    public PhysicalEngine() {
        super();
    }

    public void addPhysicalObject(Physical physical) {
        physicalObjects.add(physical);
    }

    public List<Physical> isCollision(Physical physical) {
        List<Physical> collidedObjects = new ArrayList<>();

        return collidedObjects;

    }

    public void setPosition(Physical physical, Point2D.Double abs) {
        //checkCollision

    }

    public void translate(Physical physical, Point2D.Double destination) {
        //checkCollision
    }

    public void setSpeed(Physical physical, int absSpeed) {

    }

    public void setAcceleration(Physical physical, int gap) {
    }

}
