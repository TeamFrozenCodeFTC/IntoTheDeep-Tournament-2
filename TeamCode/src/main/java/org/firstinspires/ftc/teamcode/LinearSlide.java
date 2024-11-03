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

    void dump() {
        operationMode.dumperServo.setPosition(0.4);
    }

    void undump() {
        operationMode.dumperServo.setPosition(0.25);
    }

    boolean completedExtension = false;
    boolean isRaised = false;

    void waitForExtension() throws InterruptedException {
        while (!completedExtension) {
            wait();
        }
    }

    void raise(int level) {
        int targetTicks = level >= 2 ? MAX : FIRST_LEVEL;
        completedExtension = false;
        isRaised = true;

        new Thread(() -> {
            while (isRaised) {
                int x1 = TICKS_TO_EQUATION, y1 = 1;
                int x2 = targetTicks,       y2 = 0;

                double slope = (double) (y1 - y2) / (x1 - x2);
                double y_int = 1 - slope * TICKS_TO_EQUATION;

                double power = Math.min(
                        (slope * operationMode.linearSlideMotor.getCurrentPosition() + y_int), 1);

                operationMode.linearSlideMotor.setPower(power);
            }
            operationMode.linearSlideMotor.setPower(0);
            completedExtension = true;
        }).start();
    }

    void lower() {
        completedExtension = false;
        isRaised = false;

        new Thread(() -> {
            while (!isRaised && operationMode.linearSlideMotor.getCurrentPosition() > MIN) {
                operationMode.linearSlideMotor.setPower(-0.15);
            }
            operationMode.linearSlideMotor.setPower(0);
            completedExtension = true;
        }).start();
    }

// TODO convert to nice example for portfolio

//                // (TICKS_TO_EQUATION, 1), (MAX_TICKS, 0)
//
//                int x1 = 4320, y1 = 1;
//                int x2 = 4720, y2 = 0;
//
//                double slope = (double) (y1 - y2) / (x1 - x2);
//
//                double y_int = y1 - slope * x1;
//
//                double power = (slope * (ticks) + y_int) / 4;
//
//                operationMode.linearSlide.setPower(power);
//
//                x2 += 1;
//                x1 += 1;
//                TICKS_TO_START_EQUATION += 1;
//                // y = mx + b
//                // b = y - mx
}

// Old code
//void waitForExtension() throws InterruptedException {
//    if (extend) {
//        while (!isExtended) {
//            wait();
//        }
//    }
//    else {
//        while (isExtended) {
//            wait();
//        }
//    }
//}
//
//void moveSlide() {
//    int ticks = operationMode.linearSlideMotor.getCurrentPosition();
//
//    if (extend) {
//        if (ticks < 4800) {
//            operationMode.linearSlideMotor.setPower(1);
//        }
//        else {
//            if (ticks >= MAX) {
//                isExtended = true;
//            }
////                double power = ((-1 / 400.0) * (ticks) + 11.8) / 4;
////                operationMode.linearSlideMotor.setPower(power);
//
//            // (MIN, 1) (MAX, 0)
//            // y-y_2/x-x_2
//            int x_1 = MIN;
//            int y_1 = 1;
//            int x_2 = MAX;
//            int y_2 = 0;
//
//            double slope = (double) (y_1 - y_2) / (x_1 - x_2);
//            // y = mx + b
//            // 1 = slope*MIN + b
//            // b = 1 - slope * MIN
//            double y_int = 1 - slope * MIN;
//
//            double power = (slope * ticks + y_int) / 8;
//            operationMode.telemetry.addData("slope", slope);
//            operationMode.telemetry.addData("y_int", y_int);
//            operationMode.telemetry.update();
//
//            operationMode.linearSlideMotor.setPower(power);
//        }
//    }
//    else if (ticks > 26) {
//        operationMode.linearSlideMotor.setPower(-0.15);
//        isExtended = false;
//    }
//    else {
//        operationMode.linearSlideMotor.setPower(0);
//    }
//}