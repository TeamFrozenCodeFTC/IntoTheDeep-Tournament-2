package org.firstinspires.ftc.teamcode;

public class Intake {
    Robot operationMode;

    public Intake(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    static final double SWEEPER_ARM_MIN_POSITION = .25;
    static final double SWEEPER_ARM_MAX_POSITION = .92;

    static final int MAX_TICKS = 3500;
    static final int TICKS_MARGIN = 100;

    static final int MIN_TICKS = MAX_TICKS - TICKS_MARGIN;

    void moveExtenderBack() {
        operationMode.linearSlide.setPower(1);
        operationMode.linearSlide.setTargetPosition(MIN_TICKS);
//        new Thread(() -> {
//            while (operationMode.intakeExtender.getCurrentPosition() < MIN_TICKS) {
//                operationMode.intakeExtender.setPower(-1);
//            }
//            operationMode.intakeExtender.setPower(0);
//        }).start();
    }

    // TODO cruz?
    void moveExtenderOut(double inches) {

    }

    void sweeperArmOut() {
        operationMode.sweeperRotator.setPosition(SWEEPER_ARM_MAX_POSITION);
    }

    void sweeperArmIn() {
        operationMode.sweeperRotator.setPosition(SWEEPER_ARM_MIN_POSITION);
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
