package engine.physics;


public class Rectangle {

    volatile double x, y, width, height;

    public Rectangle(double x, double y, double width, double height) {
        assert height>0 : "!height>0";
        assert width>0 : "!width>0";
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        assert width>0 : "!width>0";
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        assert height>0 : "!height>0";
        this.height = height;
    }

    public Coordinate getCooridnate(){
        return new Coordinate(this.x, this.y);
    }
    public void setCooridnate(Coordinate coord){
        this.x = coord.getX();
        this.y = coord.getY();
    }

    /**
     * Retourne vrai si les 2 rectangles sont en collsion,
     * c'est a dire qu'ils se superposent
     * @param other l'autre rectangle de la collision
     * @return
     */
    public boolean intersects(Rectangle other){
        double x1 = this.x;
        double y1 = this.y;
        double x2 = x1+this.width;
        double y2 = y1+this.height;

        double x3 = other.x;
        double y3 = other.y;
        double x4 = x3+other.width;
        double y4 = y3+other.height;

        double x5 = Math.max(x1, x3);
        double y5 = Math.max(y1, y3);
        double x6 = Math.min(x2, x4);
        double y6 = Math.min(y2, y4);

        return (x5<x6) && (y5<y6);
    }

    /**
     * Permet d'btenir le rectangle de superposition entre 2 rect en collision
     * @param other l'autre rectangle de la collision
     * @return
     */
    public Rectangle intersection(Rectangle other){
        double x1 = this.x;
        double y1 = this.y;
        double x2 = x1+this.width;
        double y2 = y1+this.height;

        double x3 = other.x;
        double y3 = other.y;
        double x4 = x3+other.width;
        double y4 = y3+other.height;

        double x5 = Math.max(x1, x3);
        double y5 = Math.max(y1, y3);
        double x6 = Math.min(x2, x4);
        double y6 = Math.min(y2, y4);
        return new Rectangle(x5, y5, x6-x5, y6-y5);
    }

    /**
     * return true if rectangles are adjacent and not overlaping considering normalized rectangles
     * @param other
     * @return
     */
    public boolean isTouching(Rectangle other){
        return (
            isEquals(this.x, other.getX()+other.getWidth()) ||
            isEquals(this.x+this.width, other.getX()) ||
            isEquals(this.y, other.getY()+other.getHeight()) ||
            isEquals(this.y+ this.height, other.getY())
        );
    }

    private boolean isEquals(double d1, double d2){
        return d1==d2;
    }
}
