package engine.physics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PatternTest {


    @BeforeClass
    public static void testSetup() {
    }

    @AfterClass
    public static void testCleanup() {
    }


        @Test
        public void testA() {
        assertTrue(true);
        assertEquals(true, true);
        }


        @Test
        public void testB() {
        assertEquals(true, true);
        }

        @Test
        public void testC() {
        assertNotEquals(0,1);
        }

        @Test
        public void testD() {
            Object o = new Object();
            assertSame("assertSame_message", o, o);
        }

        @Test
        public void testE() {
            Object o = null;
            assertNull(o);
        }


}
