package game.Physics;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    protected static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    protected static int oppositeSign(double n){
        return (n>0)? -1:1;
    }

    protected static double deltaYFromDeltaX(double deltaCorrectionX, double alpha){
        double alphaRad = Math.toRadians(alpha);
        double r = Math.abs(deltaCorrectionX/Math.cos(alphaRad));
        double delta_y = r*Math.sin(alphaRad);
        return delta_y;
    }

    protected static double deltaXFromDeltaY(double deltaCorrectionY, double alpha){
        double alphaRad = Math.toRadians(alpha);
        double r = Math.abs(deltaCorrectionY/Math.sin(alphaRad));
        double delta_x = r*Math.cos(alphaRad);
        return delta_x;
    }

    /* Déf mathématique*/
    protected static Coordinate polarToCartesianMath(double angleDeg, double radius){
        double angleInRadians = Math.toRadians(angleDeg);
        double x = radius * Math.cos(angleInRadians);
        double y = radius * Math.sin(angleInRadians);
        return new Coordinate(x, y);
    }

    /* Adaptation à notre origine au nord */
    protected static Coordinate polarToCartesian(double direction, double radius){
        return polarToCartesianMath(direction+90, radius);
    }


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
    
    protected static double distance(Coordinate a, Coordinate b){
        return Math.hypot(b.getX()-a.getX(), b.getY()-a.getY());
    }


    static public double normalizeAngle(double angle){
        return (angle%360>0)? angle:angle+360;
    }

    
}
