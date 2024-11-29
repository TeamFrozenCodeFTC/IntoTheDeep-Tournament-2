package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public abstract class AutoSpecimen extends Autonomous {
    public void hookFirst() {
        double power = 0.7;
        viperSlide.topBarRaise();
        goBackward(24 + (24 - 18), power);
        goBackwardsSeconds(0.1, power);

        viperSlide.waitForExtension();

        viperSlide.topBarPull();
        while (viperSlideMotor.isBusy()) {

        }
        viperSlide.clawOut();
        viperSlide.lower();
    }
}
