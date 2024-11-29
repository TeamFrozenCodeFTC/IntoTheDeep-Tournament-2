package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoBasket1 extends AutoBasket {
    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();

        double power = 0.7;

        goForward(7, power);
        slideLeft(17, power);
        turnRight(45, power);
        viperSlide.topBasketRaise();
        //goBackward(2.5, power);
        goBackwardsSeconds(0.5, power);

        waitAndDump();

        level1Ascent();
    }
}
