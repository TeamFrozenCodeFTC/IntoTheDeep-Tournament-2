package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

/*
Moves the robot via the main motors
 */

public class MovementControls {
    DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");;
    DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
    DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
    DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "backRight");

    frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);

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
            frontLeftPower += control[0][0] * factor; // reversed
            backLeftPower += control[1][0] * factor;
            backRightPower += control[1][1] * factor;
            frontRightPower += control[0][1] * factor;
        }

        boostControl();
        slowControl();

        // Set Motor Wheel Power
        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);

        controls.clear();
    }

    void slowControl() {
        boolean slow = gamepad1.left_bumper;
        if (slow) {
            frontLeftPower *= slowerFactor;
            frontRightPower *= slowerFactor;
            backLeftPower *= slowerFactor;
            backRightPower *= slowerFactor;
        }
    }

    void boostControl() {
        boolean boost = gamepad1.right_bumper;
        if (boost) {
            frontLeftPower *= boostFactor;
            frontRightPower *= boostFactor;
            backLeftPower *= boostFactor;
            backRightPower *= boostFactor;
        }
    }

    void pivotControl() {
        double pivot = gamepad1.right_stick_x;

        controls.add(new Double[][] {
                {pivot, -pivot},
                {pivot, -pivot}
        });
    }

    void verticalControl() {
        double y = -gamepad1.left_stick_y;

        controls.add(new Double[][] {
                {y, y},
                {y, y}
        });
    }

    void horizontalSlideControl() {
        double leftTrigger = gamepad1.left_trigger;
        double rightTrigger = gamepad1.right_trigger;

        double slide = leftTrigger - rightTrigger;

        controls.add(new Double[][] {
                {-slide, +slide},
                {+slide, -slide}
        });
    }
}