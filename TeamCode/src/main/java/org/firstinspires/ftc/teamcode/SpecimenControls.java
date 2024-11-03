package org.firstinspires.ftc.teamcode;

// Uses gamepad2
public class SpecimenControls {
    OperationMode operationMode;

    public SpecimenControls(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    void control() {
        linearSlide();
        dumper();

        intakeExtender();
        intakeArm();
        intakeSweeper();
    }

    void linearSlide() {
        if (operationMode.gamepad2.right_bumper) {
            operationMode.linearSlide.extend();
        }
        else if (operationMode.gamepad2.left_bumper) {
            operationMode.linearSlide.retract();
        }
    }

    void dumper() {
        if (operationMode.gamepad2.dpad_up) {
            operationMode.linearSlide.dump();
        }
        else {
            operationMode.linearSlide.undump();
        }
    }

    void intakeExtender() {
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

    void intakeArm() {
        if (operationMode.gamepad2.right_stick_y < 0) {
            operationMode.intake.sweeperArmOut();
        }
        else if (operationMode.gamepad2.right_stick_y > 0) {
            operationMode.intake.sweeperArmIn();
        }
    }

    void intakeSweeper() {
        if (operationMode.gamepad2.square) {
            operationMode.intake.spinSweeperOut();
        }
        else if (operationMode.gamepad2.circle) {
            operationMode.intake.spinSweeperIn();
        }
        else {
            operationMode.sweeper.setPower(0);
        }
    }
}
