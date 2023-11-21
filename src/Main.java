import static commands.Commands.*;
import static map.Directions.*;
import commands.Commands;
import map.Map;
import rover.Rover;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        Map map = new Map("\uD83E\uDEA823\n456\n789");
        Rover rover = new Rover(map, 1, 1, NORTH);
        rover.display();

        ArrayList<Commands> commands = new ArrayList<Commands>();
        commands.add(FORWARD);
        commands.add(RIGHT);
        commands.add(FORWARD);

        rover.move(commands);
    }
}