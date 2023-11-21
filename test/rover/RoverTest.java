package rover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import map.Coordinates;
import map.Map;
import static map.Directions.*;

public class RoverTest {
    @Test
    public void testConstructor() {
        Map map = new Map("123\n4\uD83E\uDEA86\n789");
        try {
            Rover rover = new Rover(map, 1, 1, NORTH);
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }
    @Test
    public void testTurnRight() {
        Map map = new Map("123\n4\uD83E\uDEA86\n789");
        Rover rover = new Rover(map, 0, 0, NORTH);
        rover.turnRight();
        Assertions.assertEquals(EAST, rover.getDirection());
        rover.turnRight();
        Assertions.assertEquals(SOUTH, rover.getDirection());
        rover.turnRight();
        Assertions.assertEquals(WEST, rover.getDirection());
        rover.turnRight();
        Assertions.assertEquals(NORTH, rover.getDirection());
    }

    @Test
    public void testTurnLeft() {
        Map map = new Map("123\n4\uD83E\uDEA86\n789");
        Rover rover = new Rover(map, 0, 0, NORTH);
        rover.turnLeft();
        Assertions.assertEquals(WEST, rover.getDirection());
        rover.turnLeft();
        Assertions.assertEquals(SOUTH, rover.getDirection());
        rover.turnLeft();
        Assertions.assertEquals(EAST, rover.getDirection());
        rover.turnLeft();
        Assertions.assertEquals(NORTH, rover.getDirection());
    }

    @Test
    public void testMoveOnObstacle() {
        Map map = new Map("\uD83E\uDEA823\n456\n789");
        Rover rover = new Rover(map, 1, 1, NORTH);
        rover.goStraight();
        Assertions.assertEquals(new Coordinates(1, 0), rover.getCoordinates());
        rover.turnLeft();
        rover.goStraight();
        Assertions.assertEquals(new Coordinates(1, 0), rover.getCoordinates());
    }


    @Test
    public void testGoStraight() {
        Map map = new Map("\uD83E\uDEA823\n456\n789");
        Rover rover = new Rover(map, 1, 1, NORTH);
        rover.goStraight();
        Assertions.assertEquals(new Coordinates(1, 0), rover.getCoordinates());
        rover.goStraight();
        Assertions.assertEquals(new Coordinates(1, 2), rover.getCoordinates());
        rover.goStraight();
        rover.turnLeft();
        rover.goStraight();
        Assertions.assertEquals(new Coordinates(0, 1), rover.getCoordinates());
        rover.goStraight();
        Assertions.assertEquals(new Coordinates(2, 1), rover.getCoordinates());
        rover.goStraight();
        rover.turnLeft();
        rover.goStraight();
        Assertions.assertEquals(new Coordinates(1, 2), rover.getCoordinates());
        rover.goStraight();
        Assertions.assertEquals(new Coordinates(1, 0), rover.getCoordinates());
        rover.goStraight();
        rover.turnLeft();
        rover.goStraight();
        Assertions.assertEquals(new Coordinates(2, 1), rover.getCoordinates());
        rover.goStraight();
        Assertions.assertEquals(new Coordinates(0, 1), rover.getCoordinates());
    }

    @Test
    public void testDisplay() {
        Map map = new Map("\uD83E\uDEA823\n456\n789");
        Rover rover = new Rover(map, 1, 1, NORTH);
        rover.display();
    }
}
