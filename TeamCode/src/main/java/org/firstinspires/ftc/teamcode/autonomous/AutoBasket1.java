package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoBasket1 extends AutoBasket {
    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();

        double power = 0.5;

        goForward(7, power);
        slideLeft(10, power);
        viperSlide.topBasketRaise();
        turnRight(45, power);
        //goBackward(2.5, power);
        goBackwardsSeconds(1, 0.3);

        waitAndDump();

        goForward(4, power);
        viperSlide.clawOut();
        viperSlide.lower();

        level1Ascent();
    }
}
