package engine.physics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PhysicUtilsTest {


    @BeforeClass
    public static void testSetup() {
    }

    @AfterClass
    public static void testCleanup() {
    }


    //-------------------------------------------------------------------------

    @Test
    public void testRound() {
        double actual = Utils.round(30.33333333333333, 5);
        double expected = 30.33333;
        assertTrue(expected == actual);
    }

    @Test
    public void testRound2() {
        double actual = Utils.round(30.33333333333333, 5);
        double expected = 30.333333;
        assertTrue(expected != actual);
    }


    @Test
    public void testOppositeSign() {
        double actual = Utils.oppositeSign(342);
        double expected = -1;
        assertTrue(expected == actual);
    }

    @Test
    public void testOppositeSign2() {
        double actual = Utils.oppositeSign(-342);
        double expected = 1;
        assertTrue(expected == actual);
    }


    @Test
    public void testDeltaYFromDeltaX() {
        double actual = Utils.deltaXFromDeltaY(1, 0);
        double expected = 0;
        System.out.println(actual);
        assertTrue(expected == actual);
    }

    
    @Test
    public void testDeltaYFromDeltaX2() {
        double actual = Utils.deltaXFromDeltaY(1, 45);
        double expected = 1;
        System.out.println(actual);
        assertTrue(Utils.round(expected, 5) == Utils.round(actual, 0));
    }
    

    @Test
    public void testdeltaXFromDeltaY() {
        double actual = Utils.deltaXFromDeltaY(1, 0);
        double expected = 0;
        System.out.println(actual);
        assertTrue(expected == actual);
    }

    
    @Test
    public void testdeltaXFromDeltaY2() {
        double actual = Utils.deltaXFromDeltaY(1, 45);
        double expected = 1;
        assertTrue(Utils.round(expected, 5) == Utils.round(actual, 0));
    }


    @Test
    public void testnormalizeAngle() {
        double actual = Utils.normalizeAngle(730);
        double expected = 10;
        assertTrue(expected==actual);
    }

    @Test
    public void testnormalizeAngle2() {
        double actual = Utils.normalizeAngle(710);
        double expected = 350;
        assertTrue(expected==actual);
    }

    @Test
    public void testnormalizeAngle3() {
        double actual = Utils.normalizeAngle(-10);
        double expected = 350;
        assertTrue(expected==actual);
    }
    

    @Test
    public void testnormalizeAngle4() {
        double actual = Utils.normalizeAngle(-370);
        double expected = 350;
        assertTrue(expected==actual);
    }
    

    

}
