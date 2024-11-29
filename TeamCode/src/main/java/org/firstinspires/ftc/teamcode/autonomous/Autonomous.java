package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot;

public abstract class Autonomous extends Robot {
    static final double TICKS_TO_INCHES = ((13303-9360)/92.9); // 42.5

    public int lockedAngle = 0;

    @Override
    public void initRobot() {
        super.initRobot();
        super.runToPositionMode(intakeExtender);
    }

    public void angleLock(
        double frontLeft,
        double backLeft,
        double frontRight,
        double backRight
    ) {
        double angleLock = (gyro.getAngle() - lockedAngle) / 360 * 17;

        frontLeftWheel.setPower(frontLeft+angleLock);
        backLeftWheel.setPower(backLeft+angleLock);
        frontRightWheel.setPower(frontRight-angleLock);
        backRightWheel.setPower(backRight-angleLock);
    }

    void stopWheels() {
        backLeftWheel.setPower(0);
        backRightWheel.setPower(0);
        frontLeftWheel.setPower(0);
        frontRightWheel.setPower(0);
    }

    void resetEncoder() {
        backRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void goForward(double inches, double power) {
        resetEncoder();

        double targetTics = inchesToTics(inches);

        while (backRightWheel.getCurrentPosition() < targetTics) {
            angleLock(power,power,power,power);
        }

        stopWheels();
    }

    public void goBackward(double inches, double power) {
        resetEncoder();

        double targetTics = inchesToTics(inches);

        while (backRightWheel.getCurrentPosition() > -targetTics) {
            angleLock(-power,-power,-power,-power);
        }

        stopWheels();
    }

    public void slideRight(double inches, double power) {
        resetEncoder();

        double targetTics = inchesToTics(inches);

        while (backRightWheel.getCurrentPosition() > -targetTics) {
            angleLock(power,-power,-power,-power);
        }

        stopWheels();
    }

    public void slideLeft(double inches, double power) {
        resetEncoder();

        double targetTics = inchesToTics(inches);

        while (backRightWheel.getCurrentPosition() < targetTics) {
            angleLock(-power,power,power,-power);
        }

        stopWheels();
    }

    public void turnLeft(int degrees, double power) {
        lockedAngle += degrees;

        while (gyro.getAngle() < lockedAngle) {
            frontLeftWheel.setPower(-power);
            backLeftWheel.setPower(-power);
            frontRightWheel.setPower(power);
            backRightWheel.setPower(power);
        }

        stopWheels();
    }

    public void turnRight(int degrees, double power) {
        lockedAngle -= degrees;

        while (gyro.getAngle() > lockedAngle) {
            frontLeftWheel.setPower(power);
            backLeftWheel.setPower(power);
            frontRightWheel.setPower(-power);
            backRightWheel.setPower(-power);
        }

        stopWheels();
    }

    private void moveSeconds(
        double seconds,
        double frontLeft,
        double backLeft,
        double frontRight,
        double backRight
    ) {
        resetEncoder();

        double startingTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startingTime < seconds * 1000) {
            angleLock(frontLeft, backLeft, frontRight, backRight);
        }

        stopWheels();
    }

    public void goBackwardsSeconds(double seconds, double power) {
        moveSeconds(seconds, -power,-power,-power,-power);
    }

    public void goForwardsSeconds(double seconds, double power) {
        moveSeconds(seconds, power,power,power,power);
    }

    public void slideRightSeconds(double seconds, double power) {
        moveSeconds(seconds, power,-power,-power,power);
    }

    public void slideLeftSeconds(double seconds, double power) {
        moveSeconds(seconds, -power,power,power,-power);
    }

    double inchesToTics(double inches){
        return inches*TICKS_TO_INCHES;
    }

//    private void moveTimeout(
//            double inches,
//            double frontLeft,
//            double backLeft,
//            double frontRight,
//            double backRight
//    ) {
//        resetEncoder();
//
//        double targetTics = inchesToTics(inches);
//
//        double startingTime = System.currentTimeMillis();
//
//        while (Math.abs(backRightWheel.getCurrentPosition()) < targetTics
//                && System.currentTimeMillis() - startingTime < inches * 100) {
//
//            angleLock(lockedAngle, frontLeft, backLeft, frontRight, backRight);
//        }
//
//        stopWheels();
//    }
}
