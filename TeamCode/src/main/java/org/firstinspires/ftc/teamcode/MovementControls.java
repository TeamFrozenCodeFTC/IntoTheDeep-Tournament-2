package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.ArrayList;


public class MovementControls {
    Main op;

    public MovementControls(Main op) {
        this.op = op;
    }
//    frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

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
            frontLeftPower -= control[0][0] * factor; // reversed
            backLeftPower += control[1][0] * factor;
            backRightPower += control[1][1] * factor;
            frontRightPower += control[0][1] * factor;
        }

        boostControl();
        slowControl();

        // Set Motor Wheel Power
        //frontLeftMotor.getCurrentPosition(); // 1x

        op.frontLeftMotor.setPower(frontLeftPower);
        op.backLeftMotor.setPower(backLeftPower);
        op.frontRightMotor.setPower(frontRightPower);
        op.backRightMotor.setPower(backRightPower);

        controls.clear();
    }

    void slowControl() {
        boolean slow = op.gamepad1.left_bumper;
        if (slow) {
            frontLeftPower *= slowerFactor;
            frontRightPower *= slowerFactor;
            backLeftPower *= slowerFactor;
            backRightPower *= slowerFactor;
        }
    }

    void boostControl() {
        boolean boost = op.gamepad1.right_bumper;
        if (boost) {
            frontLeftPower *= boostFactor;
            frontRightPower *= boostFactor;
            backLeftPower *= boostFactor;
            backRightPower *= boostFactor;
        }
    }

    void pivotControl() {
        double pivot = op.gamepad1.right_stick_x;

        controls.add(new Double[][] {
                {pivot, -pivot},
                {pivot, -pivot}
        });
    }

    void verticalControl() {
        double y = -op.gamepad1.left_stick_y;

        controls.add(new Double[][] {
                {y, y},
                {y, y}
        });
    }

    void horizontalSlideControl() {
        double leftTrigger = op.gamepad1.left_trigger;
        double rightTrigger = op.gamepad1.right_trigger;

        double slide = leftTrigger - rightTrigger;

        controls.add(new Double[][] {
                {-slide, +slide},
                {+slide, -slide}
        });
    }
}