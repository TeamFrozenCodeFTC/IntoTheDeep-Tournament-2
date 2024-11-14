package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class LinearSlide {
    Robot operationMode;

    public LinearSlide(Robot operationMode) {
        this.operationMode = operationMode;
    }

    static final int MAX_TICKS = 4975;
    static final int TICKS_MARGIN = 26;

    static final int TICKS_TO_EQUATION = MAX_TICKS - 175;

    static final int MIN = TICKS_MARGIN;

    static final int TOP_BASKET = MAX_TICKS - TICKS_MARGIN;
    static final int BOTTOM_BASKET = 3000;
    static final int BOTTOM_BAR = 0;
    static final int TOP_BAR = 3500;

    public void dump() {
        operationMode.dumperServo.setPosition(0.7);
        //operationMode.dumperServo.setPosition(0.2); slanted
    }

    public void undump() {
        operationMode.dumperServo.setPosition(0.4);
    }

    public void waitForExtension() {
        while (operationMode.linearSlideMotor.isBusy()) {

        }
    }

    private void raise(int ticks) {
        operationMode.linearSlideMotor.setTargetPosition(ticks);
        operationMode.linearSlideMotor.setPower(0.5);
    }

    public void topBasketRaise() {
        raise(TOP_BASKET);
    }

    public void bottomBasketRaise() {
        raise(BOTTOM_BASKET);
    }

    public void topBarRaise() {
        raise(TOP_BAR);
    }

    public void bottomBarRaise() {
        raise(BOTTOM_BAR);
    }

    public void lower() {
        operationMode.linearSlideMotor.setTargetPosition(MIN);

        operationMode.linearSlideMotor.setPower(-0.4); // Set power

        new Thread(() -> {
            waitForExtension();
            operationMode.linearSlideMotor.setPower(0);
        }).start();
    }
}