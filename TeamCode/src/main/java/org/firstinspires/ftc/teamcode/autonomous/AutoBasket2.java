package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoBasket2 extends AutoBasket {
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

        goForward(28, power);
        turnLeft(180-45, power);
        intake.armOut();
        intake.spinSweeperIn();
        goForward(8, power);
        intake.armOut();
        intake.stopSweeper();

        turnLeft(90+45, power);
        intake.spinSweeperOut();
        slideRight(8, power);
        intake.stopSweeper();
        goBackward(24, power);

        viperSlide.topBasketRaise();
        goBackwardsSeconds(0.5, power);
        waitAndDump();

        level1Ascent();
    }
}
