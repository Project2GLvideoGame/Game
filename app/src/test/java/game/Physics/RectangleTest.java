package game.Physics;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import engine.physics.Rectangle;

public class RectangleTest {
    
    @Test
    public void testConstructorAndGetters(){
        Rectangle rectangle = new Rectangle(0, 50, 100, 30);

        assertEquals(0, rectangle.getCooridnate().getX(), 0);
        assertEquals(50, rectangle.getCooridnate().getY(), 0);

        assertEquals(100, rectangle.getWidth(), 0);
        assertEquals(30, rectangle.getHeight(), 0);
    }

    @Test
    public void testIntersects(){
        Rectangle rectangle1 = new Rectangle(0, 0, 100, 100);
        Rectangle rectangle2 = new Rectangle(80, 0, 100, 100);

        assertEquals(true, rectangle1.intersects(rectangle2));

        rectangle2.setX(100);

        assertEquals(false, rectangle1.intersects(rectangle2));
    }

    @Test
    public void testIntersection(){
        Rectangle rectangle1 = new Rectangle(0, 0, 100, 100);
        Rectangle rectangle2 = new Rectangle(80, 0, 100, 100);

        Rectangle rectangleIntersect = rectangle1.intersection(rectangle2);

        assertEquals(80, rectangleIntersect.getX(), 0);
        assertEquals(0, rectangleIntersect.getY(), 0);

        assertEquals(20, rectangleIntersect.getWidth(), 0);
        assertEquals(100, rectangleIntersect.getHeight(), 0);
    }

    @Test
    public void testIsTouching(){
        Rectangle rectangle1 = new Rectangle(0, 0, 100, 100);
        Rectangle rectangle2 = new Rectangle(100, 50, 100, 100);

        assertEquals(true, rectangle1.isTouching(rectangle2));

        rectangle2.setX(99);

        assertEquals(false, rectangle1.isTouching(rectangle2));

        rectangle2.setX(101);

        assertEquals(false, rectangle1.isTouching(rectangle2));

    }

}
