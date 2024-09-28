package org.firstinspires.ftc.teamcode;

public class IntakeControls {
    OperationMode operationMode;

    public IntakeControls(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    void intakeControl() {
        boolean a = operationMode.gamepad2.a;

        operationMode.intakeServo.setPosition(a ? 1 : 0);
    }
}


