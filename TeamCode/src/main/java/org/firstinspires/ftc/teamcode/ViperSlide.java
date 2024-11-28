package org.firstinspires.ftc.teamcode;

public class ViperSlide {
    Robot op;

    public ViperSlide(Robot op) {
        this.op = op;
    }

    public final static int MAX_TICKS = 4300;

    private void raise(int ticks) {
        op.sweeperRotator.setPosition(.5);
        op.viperSlideMotor.setTargetPosition(ticks);
        op.viperSlideMotor.setPower(1);
    }

    public void topBasketRaise() {
        raise(4300);
    }
//- 1650
    //2256
    //171

    //644
    public void bottomBasketRaise() {
        raise(3000);
    }

    public void topBarRaise() {
        raise(2256);
    }
    public void topBarPull() {
        raise(1650);
    }

    public void bottomBarRaise() {
        raise(644);
    }
    public void bottomBarPull() {
        raise(171);
    }

    public void lower() {
        op.sweeperRotator.setPosition(.5);
        bucketDown();
        op.viperSlideMotor.setTargetPosition(0);

        op.viperSlideMotor.setPower(-1);

        new Thread(() -> {
            waitForExtension();
            op.viperSlideMotor.setPower(0);
        }).start();
    }

    public void waitForExtension() {
        while (op.viperSlideMotor.isBusy()
                || op.viperSlideMotor.getCurrentPosition() < MAX_TICKS-10) {

        }
    }

    public void dump() {
        op.dumperServo.setPosition(0.37);
    }

    public void bucketFlat() {
        op.dumperServo.setPosition(0.17);
    }

    public void bucketDown() {
        op.dumperServo.setPosition(-0.17);
    }

    public void clawGrab() {
        op.clawLeft.setPosition(.7);
        op.clawRight.setPosition(0);
    }

    public void clawOut() {
        op.clawLeft.setPosition(0);
        op.clawRight.setPosition(.5);
    }
}