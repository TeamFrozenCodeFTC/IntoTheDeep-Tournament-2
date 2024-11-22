package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot;

public abstract class Autonomous extends Robot {
    static final double TICKS_TO_INCHES = ((13303-9360)/92.9);

    @Override
    public void initRobot() {
        super.initRobot();
        super.runToPositionMode(intakeExtender);
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

        backLeftWheel.setPower(power);
        backRightWheel.setPower(power);
        frontLeftWheel.setPower(power);
        frontRightWheel.setPower(power);

        double targetTics = inchesToTics(inches);

        while (Math.abs(backRightWheel.getCurrentPosition()) < targetTics) {

        }

        stopWheels();
    }

    double inchesToTics(double inches){
        return inches*TICKS_TO_INCHES;
    }

    public void goBackward(double inches, double power) {
        resetEncoder();

        backLeftWheel.setPower(-power);
        backRightWheel.setPower(-power);
        frontLeftWheel.setPower(-power);
        frontRightWheel.setPower(-power);
        double targetTics = inchesToTics(inches);
        while (Math.abs(backRightWheel.getCurrentPosition()) < targetTics) {

        }
        stopWheels();
    }

    public void slideLeft(double inches, double power) {
        resetEncoder();

        backLeftWheel.setPower(power);
        backRightWheel.setPower(power);
        frontLeftWheel.setPower(-power);
        frontRightWheel.setPower(-power);
        double targetTics = inchesToTics(inches);
        while (backRightWheel.getCurrentPosition() < targetTics) {

        }
        stopWheels();
    }

    public void slideRight(double inches, double power) {
        resetEncoder();

        backLeftWheel.setPower(-power);
        backRightWheel.setPower(-power);
        frontLeftWheel.setPower(power);
        frontRightWheel.setPower(power);

        double targetTics = inchesToTics(inches);
        while (backRightWheel.getCurrentPosition() < targetTics) {

        }
        stopWheels();
    }

    public void turnLeft(double degrees, double power) {
        gyro.reset();

        backLeftWheel.setPower(-power);
        backRightWheel.setPower(power);
        frontLeftWheel.setPower(-power);
        frontRightWheel.setPower(power);

        telemetry.addData("angle", gyro.getAngle());
        telemetry.update();

        while (Math.abs(gyro.getAngle()) < degrees) {

        }

        stopWheels();
    }

    public void turnRight(double degrees, double power) {
        gyro.reset();

        backLeftWheel.setPower(power);
        backRightWheel.setPower(-power);
        frontLeftWheel.setPower(power);
        frontRightWheel.setPower(-power);

        while (Math.abs(gyro.getAngle()) < degrees) {

        }

        stopWheels();
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
