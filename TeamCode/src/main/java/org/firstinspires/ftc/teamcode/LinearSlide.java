package org.firstinspires.ftc.teamcode;

public class LinearSlide {
    Robot operationMode;

    public LinearSlide(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    static final int MAX_TICKS = 4746;
    static final int TICKS_MARGIN = 25;
//    static final int FORMULA_TICKS_MARGIN = 400;

//    boolean isExtended = false;

    void extend() {
        operationMode.linearSlide.setPower(1);
        operationMode.linearSlide.setTargetPosition(MAX_TICKS-TICKS_MARGIN);
//        new Thread(() -> {
//            isExtended = true;
//            while (isExtended) {
//                int ticks = operationMode.linearSlide.getCurrentPosition();
//
//                if (ticks < 4320) {
//                    operationMode.linearSlide.setPower(1);
//                }
//                else {
//                    //double power = ((-1/400.0) * (ticks) + 11.8) / 4;
//                    operationMode.linearSlide.setPower(power);
//                }
//            }
//        }).start();
    }

    void retract() {
        operationMode.linearSlide.setPower(-1);
        operationMode.linearSlide.setTargetPosition(TICKS_MARGIN);
//        new Thread(() -> {
//            isExtended = false;
//
//            while (!isExtended && operationMode.linearSlide.getCurrentPosition() > 20) {
//                operationMode.linearSlide.setPower(-0.15);
//            }
//        }).start();
    }

}

class LinearSlideControls {
    OperationMode operationMode;
    LinearSlide linearSlide;

    public LinearSlideControls(OperationMode operationMode) {
        this.operationMode = operationMode;
        this.linearSlide = new LinearSlide(operationMode);
    }

//    void linearSlide() {
//        if (operationMode.gamepad2.right_bumper) {
//            pressed = true;
//        }
//        else if (operationMode.gamepad2.left_bumper) {
//            pressed = false;
//        }
//
//        int ticks = operationMode.linearSlide.getCurrentPosition();
//
//        if (pressed && ticks > 4320) {
//            // (4320, 0) (4720, 1)
//            double power = ((-1/400.0) * (ticks) + 11.8) / 4;
//            operationMode.linearSlide.setPower(power);
//        }
//        else if (pressed && ticks < 4720) {
//            operationMode.linearSlide.setPower(1);
//        }
//        else if (!pressed && ticks > 20) {
//            operationMode.linearSlide.setPower(-0.15);
//        }
//        else {
//            operationMode.linearSlide.setPower(0);
//        }
//    }

    void control() {
        if (operationMode.gamepad2.right_bumper) {
            linearSlide.extend();
        }
        else if (operationMode.gamepad2.left_bumper) {
            linearSlide.retract();
        }
    }
}
