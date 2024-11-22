package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot;

import java.util.ArrayList;

public abstract class AutonomousGyroed extends Robot {
    static final double TICKS_TO_INCHES = ((13303-9360)/92.9); // 42.5

    int lockedAngle = 0;

    @Override
    public void initRobot() {
        super.initRobot();
        super.runToPositionMode(intakeExtender);
    }

    private void angleLock(
        int angle,
        double frontLeft,
        double backLeft,
        double frontRight,
        double backRight
    ) {
        double angleLock = (gyro.getDegrees() - angle) * Math.PI/180;

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
        backRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void goForward(double inches, double power) {
        resetEncoder();

        double targetTics = inchesToTics(inches);

        while (backRightWheel.getCurrentPosition() < targetTics) {
            angleLock(lockedAngle,power,power,power,power);
        }

        stopWheels();
    }

    public void goBackward(double inches, double power) {
        goForward(-inches, -power);
    }

    public void slideRight(double inches, double power) {
        resetEncoder();

        double targetTics = inchesToTics(inches);

        while (backRightWheel.getCurrentPosition() < targetTics) {
            angleLock(lockedAngle,-power,power,power,-power);
        }

        stopWheels();
    }

    public void slideLeft(double inches, double power) {
        slideRight(-inches, -power);
    }

    public void turnRight(int degrees) {
        gyro.reset();
        gyro.resetDegrees();

        lockedAngle += degrees;

        while (gyro.getDegrees() != degrees) {
            angleLock(degrees,0,0,0,0);
        }

        stopWheels();
    }

    public void turnLeft(int degrees) {
        turnRight(-degrees);
    }

    double inchesToTics(double inches){
        return inches*TICKS_TO_INCHES;
    }

    public void raiseSample() {
        viperSlide.topBarRaise();
        sleep(500);
        viperSlide.waitForExtension();
    }

    public void dumpSpecimen() {
        viperSlide.dump();
        sleep(2000);
        viperSlide.bucketFlat();
    }

    public void scoreSpecimen() {
        raiseSample();
        dumpSpecimen();
        viperSlide.lower();
    }
}
