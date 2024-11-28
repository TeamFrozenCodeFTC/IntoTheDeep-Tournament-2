package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoSpecimen extends AutonomousGyroed {
    @Override
    public void runOpMode() {
        initRobot();

        viperSlide.clawGrab();

        waitForStart();

        double power = 0.2;

        viperSlide.topBarRaise();
        bumpBackward(24+(24-18)+2,power);
        viperSlide.waitForExtension();

        viperSlide.topBarPull();
        while (viperSlideMotor.isBusy()) {

        }
        viperSlide.clawOut();
        viperSlide.lower();

        goForward(4.5,power);
        turnLeft(90, power);
        goBackward(24+(24-18)-1,power);
        slideLeft((double) (24/2)+2, power);
        goBackward(6, power);
        slideRight((double) 24*2, power);

        slideLeft((double) 24*2, power);
        goBackward(8, power);

        lockedAngle = 180;
        slideRight((double) 24*2, power);

        gyro.reset();
        lockedAngle = 0;

        viperSlide.clawGrab();
        goForward(6, power);
        lockedAngle = 180;

        slideLeft(24*2.5, power);

        viperSlide.topBarRaise();
        goForward(24, power);
        viperSlide.waitForExtension();


    }
}
