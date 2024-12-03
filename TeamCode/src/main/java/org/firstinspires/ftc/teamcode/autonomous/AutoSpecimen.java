package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public abstract class AutoSpecimen extends Autonomous {
    double power = 0.5;

    public void hookFirst() {
        viperSlide.topBarRaise();
        goBackward(24, power);
        goBackwardsSeconds(0.7, 0.3);

        hangSpecimen();
    }

    public void observationZonePark() {
        goForward(24, power);
        slideLeft(24*2, power);
    }

    public void hangSpecimen() {
        viperSlide.waitForExtension();
        viperSlide.topBarPull();
        viperSlide.waitForExtension();
        viperSlide.clawOut();
        viperSlide.lower();
    }
}
