package com.toyrobot.enums;

import java.util.Arrays;
import java.util.List;

public enum RobotFacingDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static List<RobotFacingDirection> getClockwiseSortedList() {
        return Arrays.asList(
                RobotFacingDirection.NORTH,
                RobotFacingDirection.EAST,
                RobotFacingDirection.SOUTH,
                RobotFacingDirection.WEST);
    }
}
