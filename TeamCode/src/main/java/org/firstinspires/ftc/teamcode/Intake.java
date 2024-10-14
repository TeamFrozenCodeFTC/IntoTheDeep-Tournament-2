package org.firstinspires.ftc.teamcode;

public class Intake {
    Robot operationMode;

    public Intake(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    static final double SWEEPER_ROTATOR_MIN_POSITION = .25;
    static final double SWEEPER_ROTATOR_MAX_POSITION = .92;

    static final int MIN_TICKS = 100;
    static final int MAX_TICKS = 3300;

    void moveExtenderBack() {
        new Thread(() -> {
            while (operationMode.intakeExtender.getCurrentPosition() < MIN_TICKS) {
                operationMode.intakeExtender.setPower(-1);
            }
            operationMode.intakeExtender.setPower(0);
        }).start();
    }

    void sweeperArmOut() {
        operationMode.sweeperRotator.setPosition(SWEEPER_ROTATOR_MAX_POSITION);
    }

    void sweeperArmIn() {
        operationMode.sweeperRotator.setPosition(SWEEPER_ROTATOR_MIN_POSITION);
    }

    void spinSweeperIn() {
        operationMode.sweeper.setPower(0.5);
    }

    void spinSweeperOut() {
        operationMode.sweeper.setPower(-1);
    }
}

class IntakeControls {
    OperationMode operationMode;
    Intake intake;

    public IntakeControls(OperationMode operationMode) {
        this.operationMode = operationMode;
        this.intake = new Intake(operationMode);
    }

    void control() {
        intakeExtenderControls();
        sweeperArmControls();
        sweeperControls();
    }

    void intakeExtenderControls() {
        double power = -operationMode.gamepad2.left_stick_y;
        double position = operationMode.intakeExtender.getCurrentPosition();

        if (position > Intake.MAX_TICKS && power > 0) {
            power = 0;
        }
        if (position < Intake.MIN_TICKS && power < 0) {
            power = 0;
        }

        operationMode.intakeExtender.setPower(power);
    }

    void sweeperArmControls() {
        if (operationMode.gamepad2.right_stick_y < 0) {
            intake.sweeperArmOut();
        }
        if (operationMode.gamepad2.right_stick_y > 0) {
            intake.sweeperArmIn();
        }
    }

    void sweeperControls() {
        if (operationMode.gamepad2.square) {
            intake.spinSweeperOut();
        }
        if (operationMode.gamepad2.circle) {
            intake.spinSweeperIn();
        }
    }
}
