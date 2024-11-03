package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;


public class WheelControls {
    OperationMode operationMode;

    public WheelControls(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    double frontLeftPower  = 0;
    double backLeftPower   = 0;
    double backRightPower  = 0;
    double frontRightPower = 0;

    ArrayList<Double[][]> controls = new ArrayList<>();

    final static double SPEED_FACTOR = 4;

    double powerEquation(double power) {
        return Math.pow(power, SPEED_FACTOR);
    }

    void wheelControls() {
        horizontalSlide();
        forwardAndBackward();
        pivot();
        submerseableLock();

        frontLeftPower  = 0;
        backLeftPower   = 0;
        backRightPower  = 0;
        frontRightPower = 0;
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

//    void slowControl() {
//        boolean slow = operationMode.gamepad1.left_bumper;
//        if (slow) {
//            frontLeftPower *= slowerFactor;
//            frontRightPower *= slowerFactor;
//            backLeftPower *= slowerFactor;
//            backRightPower *= slowerFactor;
//        }
//    }
//
//    void boostControl() {
//        boolean boost = operationMode.gamepad1.right_bumper;
//        if (boost) {
//            frontLeftPower *= boostFactor;
//            frontRightPower *= boostFactor;
//            backLeftPower *= boostFactor;
//            backRightPower *= boostFactor;
//        }
//    }

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
    boolean isLockedOnSubmerseable = false;

    void submerseableLock() {
        // maybe disable verticalControl?
        if (operationMode.gamepad1.dpad_up) {
            isLockedOnSubmerseable = !isLockedOnSubmerseable;
        }

        if (isLockedOnSubmerseable) {
            controls.add(new Double[][]{
                    {CREEP_SPEED, CREEP_SPEED},
                    {CREEP_SPEED, CREEP_SPEED}
            });
        }
    }
}
