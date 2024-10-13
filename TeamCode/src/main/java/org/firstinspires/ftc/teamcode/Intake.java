package org.firstinspires.ftc.teamcode;

public class Intake {
    OperationMode operationMode;

    public Intake(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    static final double SWEEPER_ROTATOR_MIN_POSITION = .25;
    static final double SWEEPER_ROTATOR_MAX_POSITION = .92;

    int MIN_TICKS = 100;
    int MAX_TICKS = 3300;

    void intakeExtenderBack() {
        // Moves the intake all the way back
        double position = -operationMode.intakeExtender.getCurrentPosition();

        new Thread(() -> {
            while (position < MIN_TICKS) {
                operationMode.intakeExtender.setPower(-1);
            }
            operationMode.intakeExtender.setPower(0);
        }).start();
    }

    void intakeExtenderControl() {
        double power = -operationMode.gamepad2.left_stick_y;
        double position = operationMode.intakeExtender.getCurrentPosition();

        if (position > MAX_TICKS && power > 0) {
            power = 0;
        }
        if (position < MIN_TICKS && power < 0) {
            power = 0;
        }

        operationMode.intakeExtender.setPower(power);
    }

    void sweeperArmOut() {
        operationMode.sweeperRotator.setPosition(SWEEPER_ROTATOR_MAX_POSITION);
    }

    void sweeperArmIn() {
        operationMode.sweeperRotator.setPosition(SWEEPER_ROTATOR_MIN_POSITION);
    }

    void sweeperArmControls() {
        if (operationMode.gamepad2.right_stick_y < 0) {
            sweeperArmOut();
        }
        if (operationMode.gamepad2.right_stick_y > 0) {
            sweeperArmIn();
        }
    }

    void spinSweeperIn() {
        operationMode.sweeper.setPower(0.5);
    }

    void spinSweeperOut() {
        operationMode.sweeper.setPower(-1);
    }

    void sweeperControls() {
        if (operationMode.gamepad2.square) {
            spinSweeperOut();
        }
        if (operationMode.gamepad2.circle) {
            spinSweeperIn();
        }
    }
}

class IntakeControls {

}
