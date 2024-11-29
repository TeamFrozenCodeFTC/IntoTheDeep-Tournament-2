package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoSpecimen1 extends AutoSpecimen {
    @Override
    public void runOpMode() {
        initRobot();

        viperSlide.clawGrab();

        waitForStart();

        hookFirst();

        double power = 0.7;

        goForward(24*2, power);
        slideLeft(24*3, power);
    }
}
