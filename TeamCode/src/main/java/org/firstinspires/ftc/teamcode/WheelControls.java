package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;


public class WheelControls {
    OperationMode operationMode;

    public WheelControls(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    ArrayList<Double[][]> controls = new ArrayList<>();

    // Must allow negative numbers (must be odd)
    final static int SPEED_FACTOR = 3;

    double powerEquation(double power) {
        return Math.pow(power, SPEED_FACTOR);
    }

    void control() {
        horizontalSlide();
        forwardAndBackward();
        pivot();
        submerseableLock();

        double frontLeftPower  = 0;
        double backLeftPower   = 0;
        double backRightPower  = 0;
        double frontRightPower = 0;
        for (Double[][] control : controls) {
            frontLeftPower  += powerEquation(control[0][0]);
            backLeftPower   += powerEquation(control[1][0]);
            backRightPower  += powerEquation(control[1][1]);
            frontRightPower += powerEquation(control[0][1]);
        }

        operationMode.frontLeftWheel.setPower(frontLeftPower);
        operationMode.backLeftWheel.setPower(backLeftPower);
        operationMode.frontRightWheel.setPower(frontRightPower);
        operationMode.backRightWheel.setPower(backRightPower);

        controls.clear();
    }

    void pivot() {
        double pivot = operationMode.gamepad1.right_stick_x;

        controls.add(new Double[][] {
                {pivot, -pivot},
                {pivot, -pivot}
        });
    }

    void forwardAndBackward() {
        double y = -operationMode.gamepad1.left_stick_y;

        controls.add(new Double[][] {
                {y, y},
                {y, y}
        });
    }

    void horizontalSlide() {
//        double leftTrigger = operationMode.gamepad1.left_trigger;
//        double rightTrigger = operationMode.gamepad1.right_trigger;
//
//        double slide = leftTrigger > rightTrigger ? leftTrigger : -rightTrigger;

        double slide = operationMode.gamepad1.left_stick_x;

        controls.add(new Double[][] {
                {-slide, +slide},
                {+slide, -slide}
        });
    }

    final static double CREEP_SPEED = 0.05;
    boolean isLockedOnSubmersible = false;

    void submerseableLock() {
        if (operationMode.gamepad1.dpad_up) {
            isLockedOnSubmersible = !isLockedOnSubmersible;
        }

        if (!isLockedOnSubmersible) {
            return;
        }

        controls.add(new Double[][]{
                {CREEP_SPEED, CREEP_SPEED},
                {CREEP_SPEED, CREEP_SPEED}
        });

        double power = -operationMode.gamepad1.left_stick_y;

        if (power != 0) {
            operationMode.intake.moveExtender(power);
        }
        else {
            operationMode.intake.stopExtender();
        }
    }
}
