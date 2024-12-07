package org.firstinspires.ftc.teamcode.autonomous;

public abstract class AutoBasket extends Autonomous {
    public void waitAndDump() {
        viperSlide.waitForExtension();
        viperSlide.dump();
        sleep(1000);
    }

    public void dumpFirst() {
        double power = 0.5;
        goForward(7, power);
        slideLeft(10, power);
        viperSlide.topBasketRaise();
        turnRight(45, power);
        goBackwardsSeconds(1, 0.3);
        goBackward(0.5, 0.3);

        waitAndDump();

        goForward(4, power);
        viperSlide.clawOut();
        viperSlide.lower();
    }

    public void level1Ascent() {
        double power = 0.5;
        goForward(24, power);
        turnLeft(45, power);
        goForward(28, power);
        turnLeft(90, power);
        viperSlide.topBarRaise();
        goBackward(10, power);
        goBackwardsSeconds(1, 0.3);
        sleep(30000);
    }
}
