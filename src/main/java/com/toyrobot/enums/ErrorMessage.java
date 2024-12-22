package com.toyrobot.enums;

public enum ErrorMessage {
    CommandIsNotFound("Command is not found, please try again."),
    InvalidPlaceArguments("Command PLACE is invalid arguments, please try again."),
    InvalidMove("Moving the robot to fall from the table is not allowed!"),
    IgnoredPlaceCommandWhenRobotIsAlreadyPlaced("The command is discarded, the robot has been placed on the table."),
    IgnoredPlaceCommandWhenInputIsInvalid("The command is discarded, input is invalid."),
    IgnoreCommandWhenRobotIsNotOnTable("The command is discarded, the robot has not been placed on the table.");

    public final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
