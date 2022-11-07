package game.Physic;

public class Collision {

    public Rectangle overlap;
    public Physical obj, obstacle;

    public Collision(Rectangle overlap, Physical obj, Physical obstacle) {
        this.overlap = overlap;
        this.obj = obj;
        this.obstacle = obstacle;
    }

//TODO: mettre des get/set
}
