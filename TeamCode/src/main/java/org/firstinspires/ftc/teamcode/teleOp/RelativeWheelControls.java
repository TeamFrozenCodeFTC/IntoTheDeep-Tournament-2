package org.firstinspires.ftc.teamcode.teleOp;

import org.firstinspires.ftc.teamcode.Robot;

import java.util.ArrayList;

public class RelativeWheelControls {
    Robot operationMode;

    public RelativeWheelControls(Robot operationMode) {
        this.operationMode = operationMode;
    }

    ArrayList<Double[][]> controls = new ArrayList<>();

    final static double SPEED_FACTOR = 0.7;

    double powerEquation(double power) {
        return Math.pow(power, 3);
    }

    private double getRadians() {
        return operationMode.gyro.getAngle() * Math.PI/180;
    }

    void control() {
        relativeSlide();
        pivot();
        submerseableLock();

        double frontLeftPower  = 0;
        double backLeftPower   = 0;
        double backRightPower  = 0;
        double frontRightPower = 0;
        for (Double[][] control : controls) {
            frontLeftPower  += powerEquation(control[0][0]) * SPEED_FACTOR;
            backLeftPower   += powerEquation(control[1][0]) * SPEED_FACTOR;
            backRightPower  += powerEquation(control[1][1]) * SPEED_FACTOR;
            frontRightPower += powerEquation(control[0][1]) * SPEED_FACTOR;
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

    void relativeSlide() {
        double xStick = -operationMode.gamepad1.left_stick_x;
        double yStick = -operationMode.gamepad1.left_stick_y;

        double radians = getRadians();

        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double x = xStick * cos - yStick * sin;
        double y = xStick * sin + yStick * cos;

        controls.add(new Double[][] {
                {y-x, y+x},
                {y+x, y-x}
        });
    }

    final static double CREEP_SPEED = 0.5;
    boolean isLockedOnSubmersible = false;

    void submerseableLock() {
        if (operationMode.gamepad1.dpad_up) {
            isLockedOnSubmersible = !isLockedOnSubmersible;
        }

        if (!isLockedOnSubmersible) {
            return;
        }

        operationMode.telemetry.addData("creeping", CREEP_SPEED);
        operationMode.telemetry.update();

        controls.add(new Double[][]{
                {CREEP_SPEED, CREEP_SPEED},
                {CREEP_SPEED, CREEP_SPEED}
        });

//        double power = -gamepad1.left_stick_y;
//
//        if (power != 0) {
//            intake.moveExtender(power);
//        }
//        else {
//            intake.stopExtender();
//        }
    }
}
