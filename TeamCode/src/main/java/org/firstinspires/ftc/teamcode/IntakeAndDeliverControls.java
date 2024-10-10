package org.firstinspires.ftc.teamcode;

public class IntakeAndDeliverControls {
    OperationMode operationMode;

    public IntakeAndDeliverControls(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    void intakeAndDeliverControls() {
        intakeControl();
        calculateHeight();
        linearSlide();
    }

    void intakeControl() {
        operationMode.intakeMotor.setPower(operationMode.gamepad2.left_stick_y);
    }

    void calculateHeight() {
        operationMode.telemetry.addData("Ticks", operationMode.linearSlide.getCurrentPosition(););
        operationMode.telemetry.update();
    }

    void linearSlide() {

    }
}


