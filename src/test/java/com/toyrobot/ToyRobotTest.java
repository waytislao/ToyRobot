package com.toyrobot;

import com.toyrobot.enums.ErrorMessage;
import com.toyrobot.enums.RobotFacingDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;

class ToyRobotTest {
    ToyRobot toyRobot;

    private void assertNullRobotInfos() {
        Assertions.assertNull(toyRobot.getX());
        Assertions.assertNull(toyRobot.getY());
        Assertions.assertNull(toyRobot.getCurrentRobotFacing());
    }

    @BeforeEach
    public void setUp() {
        toyRobot = new ToyRobot();
    }

    //region place function

    @Test
    public void ShouldGetCorrectRobotInfos_WhenPlaceRobotOnTable() {
        toyRobot.place(1, 2, RobotFacingDirection.WEST);

        Assertions.assertEquals(1, toyRobot.getX());
        Assertions.assertEquals(2, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.WEST, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldDiscardPlaceCommandAndAllRobotInfoAreNotChanged_WhenRobotIsAlreadyPlaced() {
        toyRobot.place(1, 2, RobotFacingDirection.WEST);
        toyRobot.place(3, 4, RobotFacingDirection.NORTH);

        Assertions.assertEquals(1, toyRobot.getX());
        Assertions.assertEquals(2, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.WEST, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldDiscardPlaceCommand_WhenXIsOutOfRange() {
        toyRobot.place(5, 1, RobotFacingDirection.WEST);

        assertNullRobotInfos();

        toyRobot.place(-1, 1, RobotFacingDirection.WEST);

        assertNullRobotInfos();
    }

    @Test
    public void ShouldDiscardPlaceCommand_WhenYIsOutOfRange() {
        toyRobot.place(1, 5, RobotFacingDirection.WEST);

        assertNullRobotInfos();

        toyRobot.place(1, -1, RobotFacingDirection.WEST);

        assertNullRobotInfos();
    }

    @Test
    public void ShouldDiscardPlaceCommand_WhenDirectionIsNull() {
        toyRobot.place(1, 1, null);

        assertNullRobotInfos();
    }
    //endregion

    //region move function
    @Test
    public void ShouldMoveCorrectly_WhenRobotFacingNorth() {
        toyRobot.place(0, 0, RobotFacingDirection.NORTH);
        toyRobot.move();

        Assertions.assertEquals(0, toyRobot.getX());
        Assertions.assertEquals(1, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.NORTH, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldNotMove_WhenRobotFacingNorthAndIsOnTheEdge() {
        toyRobot.place(0, 4, RobotFacingDirection.NORTH);
        toyRobot.move();

        Assertions.assertEquals(0, toyRobot.getX());
        Assertions.assertEquals(4, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.NORTH, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldMoveCorrectly_WhenRobotFacingEast() {
        toyRobot.place(0, 0, RobotFacingDirection.EAST);
        toyRobot.move();

        Assertions.assertEquals(1, toyRobot.getX());
        Assertions.assertEquals(0, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.EAST, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldNotMove_WhenRobotFacingEastAndIsOnTheEdge() {
        toyRobot.place(4, 0, RobotFacingDirection.EAST);
        toyRobot.move();

        Assertions.assertEquals(4, toyRobot.getX());
        Assertions.assertEquals(0, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.EAST, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldMoveCorrectly_WhenRobotFacingSouth() {
        toyRobot.place(0, 4, RobotFacingDirection.SOUTH);
        toyRobot.move();

        Assertions.assertEquals(0, toyRobot.getX());
        Assertions.assertEquals(3, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.SOUTH, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldNotMove_WhenRobotFacingSouthAndIsOnTheEdge() {
        toyRobot.place(3, 0, RobotFacingDirection.SOUTH);
        toyRobot.move();

        Assertions.assertEquals(3, toyRobot.getX());
        Assertions.assertEquals(0, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.SOUTH, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldMoveCorrectly_WhenRobotFacingWest() {
        toyRobot.place(4, 0, RobotFacingDirection.WEST);
        toyRobot.move();

        Assertions.assertEquals(3, toyRobot.getX());
        Assertions.assertEquals(0, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.WEST, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldNotMove_WhenRobotFacingWestAndIsOnTheEdge() {
        toyRobot.place(0, 3, RobotFacingDirection.WEST);
        toyRobot.move();

        Assertions.assertEquals(0, toyRobot.getX());
        Assertions.assertEquals(3, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.WEST, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldDiscardMoveCommandAndAllRobotInfoAreNull_WhenRobotHasNotBeenPlaced() {
        toyRobot.move();

        assertNullRobotInfos();
    }
    //endregion

    //region left function
    @Test
    public void ShouldTurnLeftToWest_WhenRobotFacingNorth() {
        toyRobot.place(0, 0, RobotFacingDirection.NORTH);
        toyRobot.turnLeft();

        Assertions.assertEquals(0, toyRobot.getX());
        Assertions.assertEquals(0, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.WEST, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldTurnLeftToSouth_WhenRobotFacingWest() {
        toyRobot.place(0, 0, RobotFacingDirection.WEST);
        toyRobot.turnLeft();

        Assertions.assertEquals(0, toyRobot.getX());
        Assertions.assertEquals(0, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.SOUTH, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldDiscardLeftCommandAndAllRobotInfoAreNull_WhenRobotHasNotBeenPlaced() {
        toyRobot.turnLeft();

        assertNullRobotInfos();
    }
    //endregion

    //region right function
    @Test
    public void ShouldTurnRightToEast_WhenRobotFacingNorth() {
        toyRobot.place(0, 0, RobotFacingDirection.NORTH);
        toyRobot.turnRight();

        Assertions.assertEquals(0, toyRobot.getX());
        Assertions.assertEquals(0, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.EAST, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldTurnRightToNorth_WhenRobotFacingWest() {
        toyRobot.place(0, 0, RobotFacingDirection.WEST);
        toyRobot.turnRight();

        Assertions.assertEquals(0, toyRobot.getX());
        Assertions.assertEquals(0, toyRobot.getY());
        Assertions.assertEquals(RobotFacingDirection.NORTH, toyRobot.getCurrentRobotFacing());
    }

    @Test
    public void ShouldDiscardRightCommandAndAllRobotInfoAreNull_WhenRobotHasNotBeenPlaced() {
        toyRobot.turnRight();

        assertNullRobotInfos();
    }
    //endregion

    //region report function
    @Test
    public void ShouldReportCorrectlyAndSuccessfully_WhenRobotIsPlacedOnTable() {
        toyRobot.place(2, 4, RobotFacingDirection.WEST);

        String result = toyRobot.report();

        Assertions.assertEquals("Output: 2,4,WEST", result);
    }

    @Test
    public void ShouldReportError_WhenRobotIsNotPlacedOnTable() {
        String result = toyRobot.report();

        Assertions.assertEquals(ErrorMessage.IgnoreCommandWhenRobotIsNotOnTable.message, result);
    }
    //endregion

    //region runConsole function
    @Test
    public void ShouldParseCommandSuccessfullyAndExecuteCommandFunctionCorrectlyWhenSomeCommandIsInLowercase() throws Exception {
        PrintStream outBackup = System.out;
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        withTextFromSystemIn("PLACE 0,0,NORTH", "   RIGHT", "MOVE", "left", "REPort", "X")
                .execute(() -> {
                    toyRobot.runConsole();
                    String[] consoleOutputs = out.toString().split("\n");
                    Assertions.assertEquals("Output: 1,0,NORTH", consoleOutputs[consoleOutputs.length - 1]);
                });

        System.setOut(outBackup);
    }

    @Test
    public void ShouldContinueReadInput_WhenInputWrongCommand() throws Exception {
        PrintStream outBackup = System.out;
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        withTextFromSystemIn("TOP", "X")
                .execute(() -> {
                    toyRobot.runConsole();
                    String[] consoleOutputs = out.toString().split("\n");
                    Assertions.assertEquals(ErrorMessage.CommandIsNotFound.message, consoleOutputs[1]);
                });

        System.setOut(outBackup);
    }

    @Test
    public void ShouldContinueReadInput_WhenInputPlaceCommandAndWrongArgument() throws Exception {
        PrintStream outBackup = System.out;
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        String[] testcaseArray = {
                "PLACE",
                "PLACE 0,0,NORTH END",
                "PLACE A,0,NORTH",
                "PLACE 0,B,NORTH",
                "PLACE 0,0,TOP",
                "PLACE A,B,TOP"};

        for (String testcase : testcaseArray) {
            withTextFromSystemIn(testcase, "X")
                    .execute(() -> {
                        toyRobot.runConsole();
                        String[] consoleOutputs = out.toString().split("\n");
                        Assertions.assertEquals(ErrorMessage.InvalidPlaceArguments.message, consoleOutputs[1]);
                    });
        }

        System.setOut(outBackup);
    }
    //endregion
}