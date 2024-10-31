package org.firstinspires.ftc.teamcode;

public class  LinearSlide {
    Robot operationMode;

    public LinearSlide(Robot operationMode) {
        this.operationMode = operationMode;
    }

    static final int MAX_TICKS = 4746;
    static final int TICKS_MARGIN = 26;

    static final int TICKS_TO_EQUATION = MAX_TICKS - 426;

    static final int MIN = TICKS_MARGIN;
    static final int MAX = MAX_TICKS - TICKS_MARGIN;

    boolean extend = false;
    boolean isExtended = false;

    void dump() {
        operationMode.dumperServo.setPosition(0.2);
    }

    void undump() {
        operationMode.dumperServo.setPosition(0);
    }

    void waitForExtend() throws InterruptedException {
        if (extend) {
            while (!isExtended) {
                wait();
            }
        }
        else {
            while (isExtended) {
                wait();
            }
        }
    }

    void moveSlide() {
        if (extend) {
            int ticks = Math.abs(operationMode.linearSlideMotor.getCurrentPosition());

            operationMode.telemetry.addData("moving slide", ticks);
            operationMode.telemetry.update();

            if (ticks < 4320) {
                operationMode.linearSlideMotor.setPower(1);
            }
            else {
                if (ticks >= 4720) {
                    isExtended = true;
                }
                double power = ((-1 / 400.0) * (ticks) + 11.8) / 4;
                operationMode.linearSlideMotor.setPower(power);
            }
        }
        else if (operationMode.linearSlideMotor.getCurrentPosition() > 26) {
            operationMode.linearSlideMotor.setPower(-0.3);
            isExtended = false;
        }
        else {
            operationMode.linearSlideMotor.setPower(0);
        }
    }

    void extend() {
        extend = true;
    }

//        new Thread(() -> {
//            isExtended = true;
//            while (isExtended) {
//                int ticks = operationMode.linearSlide.getCurrentPosition();
//
//                if (ticks < TICKS_TO_EQUATION) {
//                    operationMode.linearSlide.setPower(1);
//                }
//                else {
//                    // (MIN, 1) (MAX, 0)
//                    // y-y_2/x-x_2
//                    int x_1 = MIN;
//                    int y_1 = 1;
//                    int x_2 = MAX;
//                    int y_2 = 0;
//
//                    double slope = (double) (y_1 - y_2) / (x_1 - x_2);
//                    // y = mx + b
//                    // 1 = slope*MIN + b
//                    // b = 1 - slope * MIN
//                    double y_int = 1 - slope * MIN;
//
//                    double power = (slope * ticks + y_int) / 4;
//                    operationMode.linearSlide.setPower(power);
//                }
//            }
//        }).start();
//    }
//
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
    void retract() {
        extend = false;
    }

}

class LinearSlideControls {
    OperationMode operationMode;
    LinearSlide linearSlide;

    public LinearSlideControls(OperationMode operationMode) {
        this.operationMode = operationMode;
        this.linearSlide = new LinearSlide(operationMode);
    }

    void control() {
        if (operationMode.gamepad2.right_bumper) {
            linearSlide.extend();
        }
        else if (operationMode.gamepad2.left_bumper) {
            linearSlide.retract();
        }

        if (operationMode.gamepad2.dpad_up) {
            linearSlide.dump();
        }
        else {
            linearSlide.undump();
        }
    }
}
