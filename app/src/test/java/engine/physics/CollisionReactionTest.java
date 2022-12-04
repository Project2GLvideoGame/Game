package engine.physics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import engine.physics.collisionReaction.DisappearReaction;
import engine.physics.collisionReaction.IgnoreReaction;
import engine.physics.collisionReaction.RollBackReaction;
import engine.physics.collisionReaction.RollBackSmartReaction;

public class CollisionReactionTest {

    DisappearReaction dr = new DisappearReaction();
    IgnoreReaction ir = new IgnoreReaction();
    RollBackReaction rr = new RollBackReaction();
    RollBackSmartReaction rsr = new RollBackSmartReaction();

    @BeforeClass
    public static void testSetup() {
    }

    @AfterClass
    public static void testCleanup() {
    }


    //-------------------------------------------------------------------------

    @Test
    public void testIgnoreReaction() {
        Physic p = new Physic(200, 200, 10, 10, ir);
        p.getReaction().setPositionAfterCollision(p, new Coordinate(100,100), new Coordinate(200, 200), null);
        Coordinate actual =  p.getCoordinate();
        
        Coordinate expected = new Coordinate(200, 200);
        
        assertTrue(expected.getX()==actual.getX() && expected.getY()==actual.getY());
    }

    
    @Test
    public void testRollBackReaction() {
        Physic p = new Physic(200, 200, 10, 10, rr);
        p.getReaction().setPositionAfterCollision(p, new Coordinate(100,100), new Coordinate(200, 200), null);
        Coordinate actual =  p.getCoordinate();
        
        Coordinate expected = new Coordinate(100, 100);
        
        assertTrue(expected.getX()==actual.getX() && expected.getY()==actual.getY());
    }

}
