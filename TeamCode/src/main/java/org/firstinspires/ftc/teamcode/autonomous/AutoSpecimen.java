package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoSpecimen extends AutonomousGyroed {
    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();

        double power = 0.2;

        viperSlide.topBasketRaise();
        goBackward(24+(24-18)+2,power);
        viperSlide.waitForExtension();

        goForward(2,power);
        turnLeft(90);
        goBackward(24+(24-18)-1,power);
        slideRight((double) (24/2)+2, power);
    }
}
