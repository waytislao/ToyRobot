package com.toyrobot;

import com.toyrobot.enums.ErrorMessage;
import com.toyrobot.enums.RobotCommand;
import com.toyrobot.enums.RobotFacingDirection;

import java.util.List;
import java.util.Scanner;

public class ToyRobot {
    private final List<RobotFacingDirection> clockwiseSortedDirections = RobotFacingDirection.getClockwiseSortedList();
    private final int maxX = 4;
    private final int maxY = 4;

    private RobotFacingDirection currentRobotFacing = null;
    private Integer x = null;
    private Integer y = null;

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public RobotFacingDirection getCurrentRobotFacing() {
        return currentRobotFacing;
    }

    void runConsole() {
        System.out.println("Enter commands of the following form : \"PLACE X,Y,F\", \"MOVE\", \"LEFT\", \"RIGHT\", \"REPORT\" (enter x to exit)");

        while (true) {
            RobotCommand command;
            String[] userInput;
            try {
                userInput = new Scanner(System.in).nextLine().trim().toUpperCase().split(" ");
                command = RobotCommand.valueOf(userInput[0]);

                if (command.equals(RobotCommand.X)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMessage.CommandIsNotFound.message);
                continue;
            }

            switch (command) {
                case PLACE:
                    if (userInput.length != 2) {
                        System.out.println(ErrorMessage.InvalidPlaceArguments.message);
                        break;
                    }
                    int newX;
                    int newY;
                    RobotFacingDirection newRobotFacingDirection;
                    try {
                        String[] userArguments = userInput[1].split(",");
                        newX = Integer.parseInt(userArguments[0]);
                        newY = Integer.parseInt(userArguments[1]);
                        newRobotFacingDirection = RobotFacingDirection.valueOf(userArguments[2].trim().toUpperCase());
                    } catch (Exception exception) {
                        System.out.println(ErrorMessage.InvalidPlaceArguments.message);
                        break;
                    }
                    place(newX, newY, newRobotFacingDirection);
                    break;
                case MOVE:
                    move();
                    break;
                case LEFT:
                    turnLeft();
                    break;
                case RIGHT:
                    turnRight();
                    break;
                case REPORT:
                    System.out.println(report());
                    break;
            }
        }
    }

    boolean hasRobotBeenPlaced() {
        return x != null && y != null && currentRobotFacing != null;
    }

    public void place(int newX, int newY, RobotFacingDirection newRobotFacingDirection) {
        boolean isCoordinateOutOfRange = (newX > maxX || newX < 0) || (newY > maxY || newY < 0);
        if (isCoordinateOutOfRange || newRobotFacingDirection == null) {
            System.out.println(ErrorMessage.IgnoredPlaceCommandWhenInputIsInvalid.message);
            return;
        }
        if (hasRobotBeenPlaced()) {
            System.out.println(ErrorMessage.IgnoredPlaceCommandWhenRobotIsAlreadyPlaced.message);
            return;
        }
        x = newX;
        y = newY;
        currentRobotFacing = newRobotFacingDirection;
    }

    public void move() {
        if (!hasRobotBeenPlaced()) {
            System.out.println(ErrorMessage.IgnoreCommandWhenRobotIsNotOnTable.message);
            return;
        }
        int oldX = x;
        int oldY = y;
        switch (currentRobotFacing) {
            case EAST:
                if (x + 1 <= maxX) {
                    x += 1;
                }
                break;
            case WEST:
                if (x - 1 >= 0) {
                    x -= 1;
                }
                break;
            case NORTH:
                if (y + 1 <= maxY) {
                    y += 1;
                }
                break;
            case SOUTH:
                if (y - 1 >= 0) {
                    y -= 1;
                }
                break;
        }

        if (x == oldX && y == oldY) {
            System.out.println(ErrorMessage.InvalidMove.message);
        }
    }

    public void turnLeft() {
        if (!hasRobotBeenPlaced()) {
            System.out.println(ErrorMessage.IgnoreCommandWhenRobotIsNotOnTable.message);
            return;
        }
        currentRobotFacing = clockwiseSortedDirections.get(
                Math.floorMod(
                        clockwiseSortedDirections.indexOf(currentRobotFacing) - 1,
                        clockwiseSortedDirections.size()
                ));
    }

    public void turnRight() {
        if (!hasRobotBeenPlaced()) {
            System.out.println(ErrorMessage.IgnoreCommandWhenRobotIsNotOnTable.message);
            return;
        }
        currentRobotFacing = clockwiseSortedDirections.get(
                Math.floorMod(
                        clockwiseSortedDirections.indexOf(currentRobotFacing) + 1,
                        clockwiseSortedDirections.size()
                ));
    }

    public String report() {
        if (!hasRobotBeenPlaced()) {
            return ErrorMessage.IgnoreCommandWhenRobotIsNotOnTable.message;
        }
        return String.format("Output: %d,%d,%s", x, y, currentRobotFacing);
    }
}
