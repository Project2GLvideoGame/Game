package game.Physics;

import org.junit.Test;

import engine.physics.Physic;

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
