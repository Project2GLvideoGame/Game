package game.kernel;

import static org.junit.Assert.*;

import engine.GameObject;
import engine.Kernel;
import engine.graphic.Displayable;
import engine.physics.Physic;
import org.junit.Test;

public class KernelTest {

    GameObject gameObject1 = new GameObject(
            new Displayable(0, 0, 5, 5, 20, "/Background/Background1.png", "/Background/Background2.png", "/Background/Background3.png", "/Background/Background4.png")
    );
    GameObject gameObject2 = new GameObject(
            new Displayable(0, 0, 5, 5, 20, "/Background/Background1.png", "/Background/Background2.png", "/Background/Background3.png", "/Background/Background4.png"),
            new Physic(0, 0, 3, 3)
    );
    @Test
    public void testAddGameObject1() {
        Kernel kernel = new Kernel();
        kernel.addGameObject(gameObject1);
        assertEquals(1, kernel.graphicEngine.getScene().displayables.size());
    }

    @Test
    public void testAddGameObject2() {
        Kernel kernel = new Kernel();
        kernel.addGameObject(gameObject2);
        assertEquals(1, kernel.graphicEngine.getScene().displayables.size());
        assertEquals(1, kernel.physicEngine.physicObjects.size());
    }

    @Test
    public void testRemoveGameObject1() {
        Kernel kernel = new Kernel();
        GameObject gameObject3 = new GameObject(
                new Physic(0, 0, 3, 3)
        );
        kernel.addGameObject(gameObject1);
        kernel.removeGameObject(gameObject3);
        assertEquals(1, kernel.graphicEngine.getScene().displayables.size());
    }

    @Test
    public void testRemoveGameObject2() {
        Kernel kernel = new Kernel();
        kernel.addGameObject(gameObject2);
        assertEquals(1, kernel.graphicEngine.getScene().displayables.size());
        assertEquals(1, kernel.physicEngine.physicObjects.size());
        kernel.removeGameObject(gameObject2);
        assertEquals(0, kernel.graphicEngine.getScene().displayables.size());
        assertEquals(0, kernel.physicEngine.physicObjects.size());
    }
}