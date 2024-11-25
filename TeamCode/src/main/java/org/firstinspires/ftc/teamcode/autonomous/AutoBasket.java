package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoBasket extends AutonomousGyroed {
    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();

        double power = 0.2;

        lockedAngle = 45;
        slideLeft(18, power);

        viperSlide.topBasketRaise();
        viperSlide.waitForExtension();
        viperSlide.dump();
        sleep(2000);
        viperSlide.lower();

        lockedAngle = -90;
        goForward(24*2, power);
    }
}
