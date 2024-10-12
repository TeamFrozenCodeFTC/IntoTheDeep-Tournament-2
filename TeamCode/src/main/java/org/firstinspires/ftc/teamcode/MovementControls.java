package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;


public class MovementControls {

    // TODO straight forward movement

    OperationMode operationMode;

    public MovementControls(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    double frontLeftPower = 0;
    double backLeftPower = 0;
    double backRightPower = 0;
    double frontRightPower = 0;

    ArrayList<Double[][]> controls = new ArrayList<>();

    double slowerFactor = 0.25;
    double factor = 0.75;
    double boostFactor = 10;

    void movementControls() {
        horizontalSlideControl();
        verticalControl();
        pivotControl();

        frontLeftPower = 0;
        backLeftPower = 0;
        backRightPower = 0;
        frontRightPower = 0;
        for (Double[][] control : controls) {
            frontLeftPower += control[0][0] * factor;
            backLeftPower += control[1][0] * factor;
            backRightPower += control[1][1] * factor;
            frontRightPower += control[0][1] * factor;
        }

        boostControl();
        slowControl();

        operationMode.frontLeftMotor.setPower(frontLeftPower);
        operationMode.backLeftMotor.setPower(backLeftPower);
        operationMode.frontRightMotor.setPower(frontRightPower);
        operationMode.backRightMotor.setPower(backRightPower);

        controls.clear();
    }

    void slowControl() {
        boolean slow = operationMode.gamepad1.left_bumper;
        if (slow) {
            frontLeftPower *= slowerFactor;
            frontRightPower *= slowerFactor;
            backLeftPower *= slowerFactor;
            backRightPower *= slowerFactor;
        }
    }

    void boostControl() {
        boolean boost = operationMode.gamepad1.right_bumper;
        if (boost) {
            frontLeftPower *= boostFactor;
            frontRightPower *= boostFactor;
            backLeftPower *= boostFactor;
            backRightPower *= boostFactor;
        }
    }

    void pivotControl() {
        double pivot = operationMode.gamepad1.right_stick_x;

        controls.add(new Double[][] {
                {pivot, -pivot},
                {pivot, -pivot}
        });
    }

    void verticalControl() {
        double y = -operationMode.gamepad1.left_stick_y;

        controls.add(new Double[][] {
                {y, y},
                {y, y}
        });
    }

    void horizontalSlideControl() {
        double leftTrigger = operationMode.gamepad1.left_trigger;
        double rightTrigger = operationMode.gamepad1.right_trigger;

        double slide = leftTrigger > rightTrigger ? leftTrigger : -rightTrigger;

        controls.add(new Double[][] {
                {-slide, +slide},
                {+slide, -slide}
        });
    }
}
