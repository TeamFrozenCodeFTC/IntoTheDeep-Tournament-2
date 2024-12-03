package org.firstinspires.ftc.teamcode.autonomous;

public abstract class AutoBasket extends Autonomous {
    public void waitAndDump() {
        viperSlide.waitForExtension();
        viperSlide.dump();
        sleep(1000);
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
    }
}
