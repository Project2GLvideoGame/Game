package game.Physics;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import game.Physics.Physic;

public class PhysicalTest {
    Physic physicalToTest = new Physic(0, 0, 0, 0);


    @Test(expected = AssertionError.class)
    public void testNullHeight() {
       new Physic(1, 2, 0, 4);
    }

    @Test(expected = AssertionError.class)
    public void testNullWidth() {
        new Physic(1, 2, 3, 0);
    }


    
}
