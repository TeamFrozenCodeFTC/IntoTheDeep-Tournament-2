package org.firstinspires.ftc.teamcode.autonomous;

public abstract class AutonomousRelative extends Autonomous {
    private static final double TICKS_PER_INCH = 42.5;

    @Override
    public void initRobot() {
        super.initRobot();
        super.runToPositionMode(intakeExtender);
    }

    double targetAngle = 0;

    private void move(double x, double y, double distanceInches, double speed) {
        int targetTicks = (int) (distanceInches * TICKS_PER_INCH);

        while (Math.abs(backRightWheel.getCurrentPosition()) < targetTicks) {
            double currentAngle = gyro.read();
            double error = targetAngle - currentAngle;
            error = (error + 180) % 360 - 180;
            double rotationCorrection = 0.04 * error;

            double headingRad = Math.toRadians(currentAngle);
            double rotatedX = x * Math.cos(-headingRad) - y * Math.sin(-headingRad);
            double rotatedY = x * Math.sin(-headingRad) + y * Math.cos(-headingRad);

            double flPower = rotatedY + rotatedX + rotationCorrection;
            double frPower = rotatedY - rotatedX - rotationCorrection;
            double blPower = rotatedY - rotatedX + rotationCorrection;
            double brPower = rotatedY + rotatedX - rotationCorrection;

            double maxPower = Math.max(1.0, Math.abs(flPower));
            maxPower = Math.max(maxPower, Math.abs(frPower));
            maxPower = Math.max(maxPower, Math.abs(blPower));
            maxPower = Math.max(maxPower, Math.abs(brPower));

            flPower /= maxPower;
            frPower /= maxPower;
            blPower /= maxPower;
            brPower /= maxPower;

            flPower *= speed;
            frPower *= speed;
            blPower *= speed;
            brPower *= speed;

            frontLeftWheel.setPower(flPower);
            frontRightWheel.setPower(frPower);
            backLeftWheel.setPower(blPower);
            backRightWheel.setPower(brPower);
        }

        // Stop motors
        stopMotors();
    }

    public void moveUp(double distanceInches, double speed) {
        move(0, 1, distanceInches, speed); // Field-forward
    }

    public void moveDown(double distanceInches, double speed) {
        move(0, -1, distanceInches, speed); // Field-backward
    }

    public void moveLeft(double distanceInches, double speed) {
        move(-1, 0, distanceInches, speed); // Field-left
    }

    public void moveRight(double distanceInches, double speed) {
        move(1, 0, distanceInches, speed); // Field-right
    }

    public void stopMotors() {
        frontLeftWheel.setPower(0);
        frontRightWheel.setPower(0);
        backLeftWheel.setPower(0);
        backRightWheel.setPower(0);
    }
}
