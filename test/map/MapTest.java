package map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapTest {
    @Test
    public void testMapSize() {
        Map map = new Map("123\n4\uD83E\uDEA86\n789");
        Assertions.assertEquals(3, map.width);
        Assertions.assertEquals(3, map.height);
    }

    @Test
    public void testMapParsing() {
        Map map = new Map("123\n4\uD83E\uDEA86\n789");

        Assertions.assertTrue(map.isObstacle(new Coordinates(1, 1)));
        Assertions.assertFalse(map.isObstacle(new Coordinates(0, 0)));
    }
}