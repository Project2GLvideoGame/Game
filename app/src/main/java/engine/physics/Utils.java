package engine.physics;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    /**
     * arondir un double a une precison donnée
     * @param value le double
     * @param places la precision 
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * retourne -1 ou 1 selon le signe de n
     * @param n
     * @return
     */
    public static int oppositeSign(double n){
        return (n>0)? -1:1;
    }

    /**
     * déduire le deltaY connaisant deltaX et l'angle
     * @param deltaCorrectionX
     * @param alpha
     * @return
     */
    public static double deltaYFromDeltaX(double deltaCorrectionX, double alpha){
        double alphaRad = Math.toRadians(alpha);
        double r = Math.abs(deltaCorrectionX/Math.cos(alphaRad));
        double delta_y = r*Math.sin(alphaRad);
        return delta_y;
    }

    /**
     * déduire le deltaX connaisant deltaY et l'angle
     * @param deltaCorrectionY
     * @param alpha
     * @return
     */
    public static double deltaXFromDeltaY(double deltaCorrectionY, double alpha){
        double alphaRad = Math.toRadians(alpha);
        double r = Math.abs(deltaCorrectionY/Math.sin(alphaRad));
        double delta_x = r*Math.cos(alphaRad);
        return delta_x;
    }


    /**
     *  Déf mathématique
     * @param angleDeg
     * @param radius
     * @return
     */
    protected static Coordinate polarToCartesianMath(double angleDeg, double radius){
        double angleInRadians = Math.toRadians(angleDeg);
        double x = radius * Math.cos(angleInRadians);
        double y = radius * Math.sin(angleInRadians);
        return new Coordinate(x, y);
    }

    /**
     *  Adaptation à notre origine au nord
     */
    protected static Coordinate polarToCartesian(double direction, double radius){
        return polarToCartesianMath(direction+90, radius);
    }

    /**
     * déduire une direction (0...360) en connaisant 2 coordonnés
     * @param source
     * @param destination
     * @return
     */
    protected static double computeDirectionMath(Coordinate source, Coordinate destination){
        // calculate the angle theta from the deltaY and deltaX values
        // (atan2 returns radians values from [-PI,PI])
        // 0 currently Coordinates EAST.  
        // NOTE: By preserving Y and X param order to atan2,  we are expecting 
        // a CLOCKWISE angle direction.  
        double theta = Math.atan2(destination.y - source.y, destination.x - source.x);

        // convert from radians to degrees
        // this will give you an angle from [0->270],[-180,0]
        double angle = Math.toDegrees(theta);

        // convert to positive range [0-360)
        // since we want to prevent negative angles, adjust them now.
        // we can assume that atan2 will not return a negative value
        // greater than one partial rotation
        return (angle<0)? angle+360:angle;
    }

    /**
     * déduire une direction (0...360) en connaisant 2 coordonnés
     * direction adapté a notre 0 au nord
     * @param source
     * @param destination
     * @return
     */
    protected static double computeDirectionForJavaFx(Coordinate source, Coordinate destination){
        // calculate the angle theta from the deltaY and deltaX values
        // (atan2 returns radians values from [-PI,PI])
        // 0 currently Coordinates EAST.  
        // NOTE: By preserving Y and X param order to atan2,  we are expecting 
        // a CLOCKWISE angle direction.  
        
        double theta = Math.atan2(-(destination.y-source.y), destination.x-source.x);

        // rotate the theta angle clockwise by 90 degrees 
        // (this makes 0 point NORTH)
        // NOTE: adding to an angle rotates it clockwise.  
        // subtracting would rotate it counter-clockwise
        
        theta -= Math.PI/2.0;

        // convert from radians to degrees
        // this will give you an angle from [0->270],[-180,0]
        
        double angle = Math.toDegrees(theta);

        // convert to positive range [0-360)
        // since we want to prevent negative angles, adjust them now.
        // we can assume that atan2 will not return a negative value
        // greater than one partial rotation
        return (angle<0)? angle+360:angle;
    }
    
    /**
     * calcul la distance2 entre 2 points
     * @param a
     * @param b
     * @return
     */
    protected static double distance(Coordinate a, Coordinate b){
        return Math.hypot(b.getX()-a.getX(), b.getY()-a.getY());
    }


    //TODO return (angle%360>0)? angle%360:angle+360;
    /**
     * convertir tout angle en une valeur entre 0 et 360
     * @param angle
     * @return
     */
    static public double normalizeAngle(double angle){
        return (angle%360>0)? angle:angle+360;
    }

    
}
