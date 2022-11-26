package engine.physicsAAA.collisionReaction;

import static engine.physicsAAA.Utils.*;

import java.util.List;

import engine.physicsAAA.Collision;
import engine.physicsAAA.Coordinate;
import engine.physicsAAA.Physic;
import engine.physicsAAA.Rectangle;

//TODO Bug pour les petits obstacles
public class RollBackSmartReaction implements CollisionReaction {

    @Override
    public void setPositionAfterCollision(Physic physical, Coordinate beforeCollsionCoord, Coordinate CollisonCoord, List<Collision> collisions) {
        if(collisions.isEmpty()) physical.setCoordinate(beforeCollsionCoord);
        
        Collision collision = collisions.get(0);
        Rectangle overlapRect = collision.overlap;

        double deltaX = CollisonCoord.getX()-beforeCollsionCoord.getX();
        int opX = oppositeSign(deltaX);
        double correctionOnX = opX*overlapRect.getWidth();
        double correctionOnY = deltaYFromDeltaX(opX*overlapRect.getWidth(), physical.getDirection()+180+90); // 90:Nord décalé 180:demi tour
        physical.setCoordinate(new Coordinate(round(CollisonCoord.getX()+correctionOnX,6), round(CollisonCoord.getY()+correctionOnY,6) ));
        if(physical.getBoxCollider().isTouching(collision.obstacle.getBoxCollider())){
            // System.out.println("coorX: "+correctionOnX+"  corrY: "+correctionOnY);
            return;
        }
        
        double deltaY = CollisonCoord.getY()-beforeCollsionCoord.getY();
        int opY = oppositeSign(deltaY);
        correctionOnY = opY*overlapRect.getHeight();
        correctionOnX = deltaXFromDeltaY(-opY*overlapRect.getHeight(), physical.getDirection()+180+90);
        physical.setCoordinate(new Coordinate( Math.round(CollisonCoord.getX()+correctionOnX), Math.round(CollisonCoord.getY()+correctionOnY)));
        if( physical.getBoxCollider().isTouching(collision.obstacle.getBoxCollider()) ){
            // System.out.println("coorX: "+correctionOnX+"  corrY: "+correctionOnY);
            return;
        }
    
        // System.out.println("33333");
        physical.setCoordinate(beforeCollsionCoord);
        return;    
    }





}
