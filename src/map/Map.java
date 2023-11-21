package map;

import java.util.ArrayList;

public class Map {
    private ArrayList<Coordinates> obstacles = new ArrayList<Coordinates>();

    int width;
    int height;
    private final static String OBSTACLE = "\uD83E\uDEA8";

    public Map(String stringObstacles) {
        Coordinates obstacleCoordinates = new Coordinates(0, 0);
        stringObstacles.codePoints().forEach(codePoint -> {
            String currentSymbol = new String(Character.toChars(codePoint));
            if (currentSymbol.equals("\n")) {
                obstacleCoordinates.incrementY();
                obstacleCoordinates.setX(0);
            } else {
                if (currentSymbol.equals(OBSTACLE)) {
                    this.obstacles.add(new Coordinates(obstacleCoordinates.getX(), obstacleCoordinates.getY()));
                }
                obstacleCoordinates.incrementX();
            }

            this.width = obstacleCoordinates.getX();
            this.height = obstacleCoordinates.getY() + 1;
        });
    }

    public boolean isObstacle(Coordinates coordinates) {
        ArrayList<Coordinates> obstacles = this.getObstacles();
        return obstacles.contains(coordinates);
    }

    public ArrayList<Coordinates> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Coordinates> obstacles) {
        this.obstacles = obstacles;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        String res = "Map{\n" +
                "\tobstacles{\n";
        for (Coordinates coords : this.obstacles) {
            res += "\t\t(" + coords.getX() + ", " + coords.getY() + ")\n";
        }
        res += "\t}\n" +
                "}\n";
        return res;
    }
}
