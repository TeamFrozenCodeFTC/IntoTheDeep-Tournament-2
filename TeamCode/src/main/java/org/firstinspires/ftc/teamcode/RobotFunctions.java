package org.firstinspires.ftc.teamcode;

public class RobotFunctions {
    OperationMode operationMode;

    public RobotFunctions(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    int intakeBack() {
        double position = -operationMode.intakeExtender.getCurrentPosition();

        while (position < startLimit) {
            operationMode.intakeExtender.setPower(-1);
            operationMode.sweeperRotator.setPosition(.25);
        }
    }

}
