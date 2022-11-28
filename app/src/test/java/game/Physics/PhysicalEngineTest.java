package game.Physics;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import engine.physics.Collision;
import engine.physics.Coordinate;
import engine.physics.Physic;
import engine.physics.PhysicEngine;
import engine.physics.Rectangle;

public class PhysicalEngineTest {

    static Physic westWall = new Physic(0, 0, 20, 100);
    static Physic eastWall = new Physic(80, 20, 20, 80);
    static Physic northWall = new Physic(20, 0, 80, 20);
    static Physic southWall = new Physic(20, 80, 60, 20);
    static Physic movingShrek = new Physic(50, 50, 10, 10);

    static PhysicEngine engine;

    @BeforeClass
    // public static void testSetup() {
    //     engine = new PhysicalEngine();
    //     engine.addPhysicalObject(westWall);
    //     engine.addPhysicalObject(eastWall);
    //     engine.addPhysicalObject(southWall);
    //     engine.addPhysicalObject(northWall);
    //     engine.addPhysicalObject(movingShrek);
    //     movingShrek.setSpeed( 0);
    // }

    @AfterClass
    public static void testCleanup() {
        // Do your cleanup here like close URL connection , releasing resources etc
    }


//     @Test
//     public void testSetCoordinates() {
//         movingShrek.setCoordinate(new Coordinate(0, 0));
//         assertTrue(movingShrek.getX() == 0);
//         assertTrue(movingShrek.getY() == 0);

//         movingShrek.setCoordinate( new Coordinate(-5, 5));
//         assertTrue(movingShrek.getX() == -5);
//         assertTrue(movingShrek.getY() == 5);

//         movingShrek.setCoordinate(new Coordinate(-1.33333, -235)  );
//         assertTrue(movingShrek.getX() == -1.33333);
//         assertTrue(movingShrek.getY() == -235);
//     }

// @Test(expected = AssertionError.class)
//     public void testNullHeight() {
//     engine.addPhysicalObject(new Physic(1, 2, 0, 4));
//     }

//     @Test(expected = AssertionError.class)
//     public void testNullWidth() {
//         engine.addPhysicalObject(new Physic(1, 2, 3, 0));
//     }

//     @Test
//     public void testAddPhysicalObject() {
//         assertTrue(engine.physicalObjects.size() ==5 ); 
//         Physic testPhysical = new Physic(1, 2, 3, 1);
//         engine.addPhysicalObject(testPhysical);
//         assertTrue(engine.physicalObjects.size() == 6);
//         assertTrue(engine.physicalObjects.get(5).equals(testPhysical));
//     }

//     @Test
//     public void testRemovePhysicalObject() {
//         Physic testPhysical = new Physic(1, 2, 3, 1);

//         engine.addPhysicalObject(testPhysical);
//         assertTrue(engine.physicalObjects.size() == 6);
//         engine.physicalObjects.remove(5);
//         assertTrue(engine.physicalObjects.size() == 5);
//         assertFalse(engine.physicalObjects.get(4).equals(testPhysical));

//     }

//     @Test
//     public void testTopLeftCornerCollision() {
//         movingShrek.setCoordinate(new Coordinate( 13, 13));
//         assertTrue(engine.allCollision(movingShrek).size() == 2);
//     }

//     @Test
//     public void testTopRightCornerCollision() {
//         movingShrek.setCoordinate(new Coordinate( 83, 13));
//         assertTrue(engine.allCollision(movingShrek).size() == 2);
//     }


//     @Test
//     public void testBottomLeftCornerCollision() {
//         movingShrek.setCoordinate(new Coordinate(13, 83));
//         assertTrue(engine.allCollision(movingShrek).size() == 2);
//     }


//     @Test
//     public void testOriginCollision() {
//         Collision eastWallCollision = new Collision(new Rectangle(0, 0, 10, 10), movingShrek, eastWall);
        
//         movingShrek.setCoordinate(new Coordinate(0, 0) );
//         // First Collision
//         assertTrue(engine.allCollision(movingShrek).size() == 1);
//         assertTrue(engine.allCollision(movingShrek).get(0).getOverlap().getX() == eastWallCollision.getOverlap().getX());
//         assertTrue(engine.allCollision(movingShrek).get(0).getOverlap().getY() == eastWallCollision.getOverlap().getY());
//         assertTrue(engine.allCollision(movingShrek).get(0).getOverlap().getWidth() == eastWallCollision.getOverlap()
//                 .getWidth());
//         assertTrue(engine.allCollision(movingShrek).get(0).getOverlap().getHeight() == eastWallCollision.getOverlap()
//                 .getHeight());
//     }

//     @Test
//     public void testBottomRightCornerCollisionDoubleCollision() {
//         Collision eastCollision = new Collision(new Rectangle(15, 15, 5, 10), movingShrek, eastWall);
//         Collision northCollision = new Collision(new Rectangle(20, 15, 5, 5), movingShrek, eastWall);
//         movingShrek.setCoordinate(new Coordinate(15, 15));
//         // First Collision
//         assertTrue(engine.allCollision(movingShrek).size() == 2);
//         assertTrue(engine.allCollision(movingShrek).get(0).getOverlap().getX() == eastCollision.getOverlap().getX());
//         assertTrue(engine.allCollision(movingShrek).get(0).getOverlap().getY() == eastCollision.getOverlap().getY());
//         assertTrue(engine.allCollision(movingShrek).get(0).getOverlap().getWidth() == eastCollision.getOverlap().getWidth());
//         assertTrue(engine.allCollision(movingShrek).get(0).getOverlap().getHeight() == eastCollision.getOverlap().getHeight());
//        // Second Collision 

//        assertTrue(engine.allCollision(movingShrek).size() == 2);
//        assertTrue(engine.allCollision(movingShrek).get(1).getOverlap().getX() == northCollision.getOverlap().getX());
//        assertTrue(engine.allCollision(movingShrek).get(1).getOverlap().getY() == northCollision.getOverlap().getY());
//        assertTrue(engine.allCollision(movingShrek).get(1).getOverlap().getWidth() == northCollision.getOverlap()
//                .getWidth());
//        assertTrue(engine.allCollision(movingShrek).get(1).getOverlap().getHeight() == northCollision.getOverlap()
//                .getHeight());
//     }
    
//   @Test
//     public void testSetDirection()  {
//         movingShrek.setDirection( 90);
//         assertTrue(movingShrek.getDirection() == 90);
//         movingShrek.setDirection( 0);
//         assertTrue(movingShrek.getDirection() == 0);
//     }

//     // TODO Regarder si le setSpeed negatif est normal
//     @Test
//     public void testSetSpeed() {
//         movingShrek.setSpeed( 5);
//         assertTrue(movingShrek.getSpeed() == 5);

//         movingShrek.setSpeed( 5.333);
//         assertTrue(movingShrek.getSpeed() == 5.333);


//         movingShrek.setSpeed( -5);
//         assertTrue(movingShrek.getSpeed() == -5);
//     }
//     // TODO modifier le set pour pouvoir peut etre avoir des angles plus grand ou des angles negatif

//     @Test(expected = AssertionError.class)
//     public void testSetNegativeDirection() {
//         movingShrek.setDirection( 400);
//         assertTrue(movingShrek.getDirection() == 40);
//         movingShrek.setDirection( -90);
//         assertTrue(movingShrek.getDirection() == 270);
//     }

  


// // TODO COMPUTE
// // WARNING  
//     @Test
//     public void testRightCorner() throws InterruptedException {
//         movingShrek.setCoordinate( new Coordinate(40, 20));
//         //assertTrue(engine.allCollision(movingShrek).size() == 0);
//         movingShrek.setDirection( 270);
//         movingShrek.setSpeed( 5);
//         long start = System.nanoTime();
//         while (movingShrek.getSpeed() != 0) {
//             engine.update(movingShrek);
//             System.out.println("Number collision " + engine.allCollision(movingShrek).size());
//             System.out.println("Position : " + movingShrek.getX() + " " + movingShrek.getY());
//             System.out.println("Speed : " + movingShrek.getSpeed());
//             Thread.sleep(10);
//             if (System.nanoTime() - start > Math.pow(10, 9)) {
//                 break;
//             }
//         }
//         System.out.println("Number collision " + engine.allCollision(movingShrek).size());
//         System.out.println("Position : " + movingShrek.getX() + " " + movingShrek.getY());
//         System.out.println("Speed : " + movingShrek.getSpeed());
//         engine.update(movingShrek);
//         // assertTrue(engine.allCollision(movingShrek).size() == 0);

//     }


//     // TODO
//     @Test 
//     public void testSetDestination(){
//         movingShrek.setCoordinate(new Coordinate(30, 30));
//         movingShrek.setSpeed(5);
//         engine.update(movingShrek);
//         while(movingShrek.getX()!= 50){
//             System.out.println(movingShrek.getX());
//         }
//     }

//     @Test
//     public void test() {
        
//     }
}
