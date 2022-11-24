package engine.physics;

public class Collision {

    public Rectangle overlap;
    public Physic obj, obstacle;

    public Collision(Rectangle overlap, Physic obj, Physic obstacle) {
        this.overlap = overlap;
        this.obj = obj;
        this.obstacle = obstacle;
    }


    public Rectangle getOverlap(){
        return overlap;
    }

    public void setOverlap(Rectangle overlap){
        this.overlap = overlap;
    }
    public Physic getobj(){
        return obj;
    }
    public Physic getObstacle(){
        return obstacle;
    }
}
