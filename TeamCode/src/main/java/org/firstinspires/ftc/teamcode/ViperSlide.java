package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class ViperSlide {
    Robot operationMode;

    public ViperSlide(Robot operationMode) {
        this.operationMode = operationMode;
    }

    static final int TOP_BASKET = 1000;
    static final int BOTTOM_BASKET = 1000;
    static final int BOTTOM_BAR = 1000;
    static final int TOP_BAR = 1000;

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
        operationMode.linearSlideMotor.setTargetPosition(0);

        operationMode.linearSlideMotor.setPower(-0.4);

        new Thread(() -> {
            waitForExtension();
            operationMode.linearSlideMotor.setPower(0);
        }).start();
    }

    public void waitForExtension() {
        while (operationMode.linearSlideMotor.isBusy()) {

        }
    }

    public void dump() {
        operationMode.dumperServo.setPosition(0.7);
        //operationMode.dumperServo.setPosition(0.2); slanted
    }

    public void undump() {
        operationMode.dumperServo.setPosition(0.4);
    }
}