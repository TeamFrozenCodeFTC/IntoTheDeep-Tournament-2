package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoBasket3 extends AutoBasket {
    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();

        double power = 0.5;

        dumpFirst();

        goForward(26, power);
        turnLeft(180-45, power);
        slideRight(8, 0.4);
        goBackward(3, power);

        intake.armOut();
        intake.spinSweeperIn();
        goForward(8, 0.15);
        intake.armIn();
        intake.stopSweeper();

        sleep(100);
        turnRight(180-45, 0.4);
        new Thread(() -> {
            intake.spinSweeperOut();
            sleep(500);
            intake.stopSweeper();
        }).start();
        sleep(100);
        slideRight(11, power);
        viperSlide.topBasketRaise();
        goBackward(30, power);
        goBackward(0.9, 0.3);

        waitAndDump();

        goForward(4, power);
        viperSlide.clawOut();
        viperSlide.lower();

        // Second Specimen

        goForward(26, power);
        turnLeft(180-45, power);
        slideRight(10.5, 0.4);

        intake.armOut();
        intake.spinSweeperIn();
        goForward(13, 0.15);
        intake.armIn();
        intake.stopSweeper();

        sleep(100);
        turnRight(180-45, 0.4);
        new Thread(() -> {
            intake.spinSweeperOut();
            sleep(500);
            intake.stopSweeper();
        }).start();
        sleep(100);
        slideRight(20, power);
        viperSlide.topBasketRaise();
        goBackward(30, 0.3);
        goBackward(1, 0.3);

        waitAndDump();
        goForward(4, power);
        viperSlide.clawOut();
        viperSlide.lower();
        level1Ascent();
//
//        turnLeft(90+45, power);
//        intake.spinSweeperOut();
//        slideRight(8, power);
//        intake.stopSweeper();
//        goBackward(24, power);
//
//        viperSlide.topBasketRaise();
//        goBackwardsSeconds(0.5, power);
//        waitAndDump();
//
//        level1Ascent();
    }
}
