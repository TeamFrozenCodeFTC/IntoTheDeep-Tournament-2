package org.firstinspires.ftc.teamcode.autonomous;

public abstract class AutoBasket extends Autonomous {
    public void waitAndDump() {
        viperSlide.waitForExtension();
        viperSlide.dump();
        sleep(1000);
        viperSlide.lower();
    }

    public void level1Ascent() {
        double power = 0.7;
        goForward(28, power);
        turnRight(45, power);
        goForward(20, power);
        turnLeft(90, power);
        viperSlide.topBarRaise();
        goBackward(8, power);
        goBackwardsSeconds(0.5, power);
    }
}
