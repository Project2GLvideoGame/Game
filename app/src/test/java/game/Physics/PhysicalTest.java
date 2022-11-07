package game.Physics;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import game.Physic.Physical;

public class PhysicalTest {
    Physical physicalToTest = new Physical(0, 0, 0, 0);


    @Test(expected = AssertionError.class)
    public void testNullHeight() {
       new Physical(1, 2, 0, 4);
    }

    @Test(expected = AssertionError.class)
    public void testNullWidth() {
        new Physical(1, 2, 3, 0);
    }

    @Test
    public void testsetBoxCollider() {
        physicalToTest.setBoxCollider(null);
    }
    
}
