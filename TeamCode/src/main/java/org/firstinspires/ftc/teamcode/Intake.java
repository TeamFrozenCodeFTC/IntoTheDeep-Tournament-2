package org.firstinspires.ftc.teamcode;

public class Intake {
    Robot operationMode;

    public Intake(Robot operationMode) {
        this.operationMode = operationMode;
    }

    static final double ARM_MIN_POSITION = .25;
    static final double ARM_MAX_POSITION = .92;

    static final int MAX_TICKS = 3500;
    static final int TICKS_MARGIN = 100;

    static final int TICKS_PER_INCH = 445;

    static final int MIN_TICKS = TICKS_MARGIN;

    boolean completedExtension = true;

    void moveExtender(double power) {
        new Thread(() -> {
            while (operationMode.intakeExtender.getCurrentPosition() < MAX_TICKS
                    && operationMode.intakeExtender.getCurrentPosition() > MIN_TICKS) {
                operationMode.intakeExtender.setPower(power);
            }
            operationMode.intakeExtender.setPower(0);
        }).start();
    }

    void stopExtender() {
        operationMode.intakeExtender.setPower(0);
    }

    void moveExtenderInches(double inches) {
        completedExtension = false;

        new Thread(() -> {
            double targetTicks = Math.max(Math.min(inches * TICKS_PER_INCH, MAX_TICKS), MIN_TICKS);

            int powerDirection = (
                    operationMode.intakeExtender.getCurrentPosition() < targetTicks ? 1 : -1);

            while (operationMode.intakeExtender.getCurrentPosition() > targetTicks) {
                operationMode.intakeExtender.setPower(powerDirection);
            }
            operationMode.intakeExtender.setPower(0);
            completedExtension = true;
        }).start();
    }

    void moveExtenderBack() {
        completedExtension = false;

        new Thread(() -> {
            while (operationMode.intakeExtender.getCurrentPosition() > MIN_TICKS) {
                operationMode.intakeExtender.setPower(-1);
            }
            operationMode.intakeExtender.setPower(0);
            completedExtension = true;
        }).start();
    }

    void waitForExtension() {
        while (!completedExtension) {

        }
    }

    void armOut() {
        operationMode.sweeperRotator.setPosition(ARM_MAX_POSITION);
    }

    void armIn() {
        operationMode.sweeperRotator.setPosition(ARM_MIN_POSITION);
    }

    void waitForArm() {
        while (operationMode.sweeperRotator.getPosition() != ARM_MIN_POSITION
                || operationMode.sweeperRotator.getPosition() != ARM_MAX_POSITION) {

        }
    }

    void spinSweeperIn() {
        operationMode.sweeper.setPower(0.5);
    }

    void spinSweeperOut() {
        operationMode.sweeper.setPower(-1);
    }

    void stopSweeper() {
        operationMode.sweeper.setPower(0);
    }
}
