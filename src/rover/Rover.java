package rover;

import commands.Commands;
import map.Coordinates;
import map.Directions;
import map.Map;

import java.util.ArrayList;

public class Rover {
    private Coordinates coordinates;
    private Directions direction;

    private Map map;

    public Rover(Map map, int x, int y, Directions direction) throws IllegalArgumentException{
        if (map.isObstacle(new Coordinates(x, y))) {
            throw new IllegalArgumentException("Rover starting position cannot be an obstacle");
        }
        this.map = map;
        this.coordinates = new Coordinates(x, y);
        this.direction = direction;
    }

    public void move(ArrayList<Commands> commands) {
        System.out.println("===MOVING===");
        for (Commands command:commands) {
            switch (command) {
                case FORWARD -> this.goStraight();
                case LEFT -> this.turnLeft();
                case RIGHT -> this.turnRight();
            }
        }
        this.display();
    }

    public void goStraight() {
        if (canGoStraight()) {
            switch (this.getDirection()) {
                case NORTH:
                    if (this.coordinates.getY() != 0) {
                        this.coordinates.decrementY();
                    } else {
                        this.coordinates.setY(this.map.getHeight() - 1);
                    }
                    break;
                case EAST:
                    if (this.coordinates.getX() != this.map.getWidth() - 1) {
                        this.coordinates.incrementX();
                    } else {
                        this.coordinates.setX(0);
                    }
                    break;
                case SOUTH:
                    if (this.coordinates.getY() != this.map.getHeight() - 1) {
                        this.coordinates.incrementY();
                    } else {
                        this.coordinates.setY(0);
                    }
                    break;
                case WEST:
                    if (this.coordinates.getX() != 0) {
                        this.coordinates.decrementX();
                    } else {
                        this.coordinates.setX(this.map.getWidth() - 1);
                    }
                    break;
            }
        }
    }

    public boolean canGoStraight() {
        switch (this.getDirection()) {
            case NORTH:
                if(this.coordinates.getY() != 0) {
                    return !this.map.isObstacle(new Coordinates(
                            this.coordinates.getX(),
                            this.coordinates.getY() - 1
                    ));
                } else {
                    return !this.map.isObstacle(new Coordinates(
                            this.coordinates.getX(),
                            this.map.getHeight() - 1
                    ));
                }
            case EAST:
                if(this.coordinates.getX() != this.map.getWidth() - 1) {
                    return !this.map.isObstacle(new Coordinates(
                            this.coordinates.getX() + 1,
                            this.coordinates.getY()
                    ));
                } else {
                    return !this.map.isObstacle(new Coordinates(
                            0,
                            this.coordinates.getY()
                    ));
                }
            case SOUTH:
                if(this.coordinates.getY() != this.map.getHeight() - 1) {
                    return !this.map.isObstacle(new Coordinates(
                            this.coordinates.getX(),
                            this.coordinates.getY() + 1
                    ));
                } else {
                    return !this.map.isObstacle(new Coordinates(
                            this.coordinates.getX(),
                            0
                    ));
                }
            case WEST:
                if(this.coordinates.getX() != 0) {
                    return !this.map.isObstacle(new Coordinates(
                            this.coordinates.getX() - 1,
                            this.coordinates.getY()
                    ));
                } else {
                    return !this.map.isObstacle(new Coordinates(
                            this.map.getWidth() - 1,
                            this.coordinates.getY()
                    ));
                }
            default:
                return false;
        }
    }

    public void turnRight() {
        switch (this.getDirection()) {
            case NORTH:
                this.setDirection(Directions.EAST);
                break;
            case EAST:
                this.setDirection(Directions.SOUTH);
                break;
            case SOUTH:
                this.setDirection(Directions.WEST);
                break;
            case WEST:
                this.setDirection(Directions.NORTH);
                break;
        }
    }

    public void turnLeft() {
        switch (this.direction) {
            case NORTH:
                this.setDirection(Directions.WEST);
                break;
            case EAST:
                this.setDirection(Directions.NORTH);
                break;
            case SOUTH:
                this.setDirection(Directions.EAST);
                break;
            case WEST:
                this.setDirection(Directions.SOUTH);
                break;
        }
    }

    public void display() {
        for (int i = 0; i < this.map.getHeight(); i++) {
            for (int j = 0; j < this.map.getHeight(); j++) {
                Coordinates currentCoordinates = new Coordinates(j, i);
                if (this.coordinates.equals(currentCoordinates)) {
                    this.displayRoverTile();
                } else if (this.map.isObstacle(currentCoordinates)) {
                    System.out.print("\uD83E\uDEA8");
                } else {
                    System.out.print("\uD83D\uDFEB");
                }
            }
            System.out.print("\n");
        }
    }

    private void displayRoverTile() {
        switch (this.getDirection()) {
            case NORTH -> System.out.print("⬆\uFE0F");
            case EAST -> System.out.print("➡\uFE0F");
            case SOUTH -> System.out.print("⬇\uFE0F");
            case WEST -> System.out.print("⬅\uFE0F");
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }
}

