package engine.physicsAAA;

public class Collision {

    public Rectangle overlap;
    public Physic obj, obstacle;

    public Collision(Rectangle overlap, Physic obj, Physic obstacle) {
        this.overlap = overlap;
        this.obj = obj;
        this.obstacle = obstacle;
    }

    public Physic getObstacle(){
        return this.obstacle;
    }

    public Physic getObj(){
        return this.obj;
    }

    public Rectangle getOverlap(){
        return overlap;
    }

    public void setOverlap(Rectangle overlap){
        this.overlap = overlap;
    }
//TODO: mettre des get/set
}
