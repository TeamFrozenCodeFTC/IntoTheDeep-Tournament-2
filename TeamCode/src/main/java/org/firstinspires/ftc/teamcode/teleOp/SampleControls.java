package org.firstinspires.ftc.teamcode.teleOp;

import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.ViperSlide;

// Uses gamepad2
public class SampleControls {
    Robot op;

    public SampleControls(Robot op) {
        this.op = op;
    }

    void control() {
        linearSlide();
        dumper();

        intakeExtender();
        intakeArm();
        intakeSweeper();

        if (op.gamepad1.triangle) {
            op.viperSlide.clawGrab();
        }
        if (op.gamepad1.cross) {
            op.viperSlide.clawOut();
        }
    }

    void run() {
        control();
    }

    void linearSlide() {
//        if (op.gamepad2.left_trigger > 0.5) {
//            op.viperSlideMotor.setTargetPosition(
//                    Math.min(ViperSlide.MAX_TICKS, op.viperSlideMotor.getCurrentPosition() + 10)
//            );
//            op.viperSlideMotor.setPower(1);
//        }
//        else if (op.gamepad2.right_trigger > 0.5) {
//            op.viperSlideMotor.setTargetPosition(
//                    Math.max(0, op.viperSlideMotor.getCurrentPosition() - 10)
//            );
//            op.viperSlideMotor.setPower(1);
//        }
        if (op.gamepad2.left_trigger > 0) {
            op.viperSlide.topBarRaise();
        }
        else if (op.gamepad2.right_trigger > 0) {
            op.viperSlide.topBarPull();
        }
        else if (op.gamepad2.right_bumper) {
            op.viperSlide.topBasketRaise();
        }
        else if (op.gamepad2.left_bumper) {
            op.viperSlide.lower();
        }
    }

    void dumper() {
        if (op.gamepad2.dpad_up) {
            op.viperSlide.dump();
        }
        else {
            op.viperSlide.bucketDown();
        }
    }

    void intakeExtender() {
        double power = -op.gamepad2.left_stick_y;
        double position = op.intakeExtender.getCurrentPosition();

        if (position > Intake.MAX_TICKS && power > 0) {
            power = 0;
        }
        if (position < Intake.MIN_TICKS && power < 0) {
            power = 0;
        }
        op.intakeExtender.setPower(power);
    }

    void intakeArm() {
        if (op.gamepad2.right_stick_y < 0) {
            op.intake.armOut();
        }
        else if (op.gamepad2.right_stick_y > 0) {
            op.intake.armIn();
        }
    }

    void intakeSweeper() {
        if (op.gamepad2.square) {
            op.intake.spinSweeperOut();
        }
        else if (op.gamepad2.circle) {
            op.intake.spinSweeperIn();
        }
        else {
            op.sweeper.setPower(0);
        }
    }
}
