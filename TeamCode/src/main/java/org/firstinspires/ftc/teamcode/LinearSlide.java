package org.firstinspires.ftc.teamcode;

public class  LinearSlide {
    Robot operationMode;

    public LinearSlide(Robot operationMode) {
        this.operationMode = operationMode;
    }

    static final int MAX_TICKS = 4975;
    static final int TICKS_MARGIN = 26;

    static final int TICKS_TO_EQUATION = MAX_TICKS - 175;

    static final int MIN = TICKS_MARGIN;
    static final int MAX = MAX_TICKS - TICKS_MARGIN;
    static final int FIRST_LEVEL = MAX_TICKS / 2;

    public void dump() {
        operationMode.dumperServo.setPosition(0.4);
    }

    public void undump() {
        operationMode.dumperServo.setPosition(0.25);
    }

    boolean completedExtension = false;
    boolean isRaised = false;

    public void waitForExtension() {
        while (!completedExtension) {

        }
    }

    public void raise(int level) {
        int targetTicks = level >= 2 ? MAX : FIRST_LEVEL;
        completedExtension = false;
        isRaised = true;

        new Thread(() -> {
            while (isRaised) {
                int x1 = TICKS_TO_EQUATION, y1 = 1;
                int x2 = targetTicks,       y2 = 0;

                double slope = (double) (y1 - y2) / (x1 - x2);
                double y_int = 1 - slope * TICKS_TO_EQUATION;

                double ticks = operationMode.linearSlideMotor.getCurrentPosition();

                double power = Math.min(
                        (slope * ticks + y_int), 1);

                operationMode.linearSlideMotor.setPower(power);

                if (ticks >= targetTicks) {
                    completedExtension = true;
                }
            }
            operationMode.linearSlideMotor.setPower(0);
        }).start();
    }

    public void lower() {
        completedExtension = false;
        isRaised = false;

        new Thread(() -> {
            while (!isRaised && operationMode.linearSlideMotor.getCurrentPosition() > MIN) {
                operationMode.linearSlideMotor.setPower(-0.25);
            }
            operationMode.linearSlideMotor.setPower(0);
            completedExtension = true;
        }).start();
    }
}