package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoSpecimen2 extends AutoSpecimen {
    @Override
    public void runOpMode() {
        initRobot();

        viperSlide.clawGrab();

        waitForStart();

        hookFirst();

        double power = 0.5;

        // Goes over to samples
        goForward(4.5, power);
        turnLeft(90, power);
        goForward(30, power);
        slideLeft(24, power);

        // Move sample 1
        goForward(6, power);
        slideRight(24*2, power);
        slideLeft((double) 24*1.7, power);

        // Move sample 2
        goForward(6, power);
        slideRight(24*2, power);

//        slideLeft(6, power);
//        turnLeft(90, power);
//        goForward(3, power);

        // Move out of observation Zone
        slideLeft(9, power);

        // Turn to face with claw
        turnLeft(90, power);

        // Go into wall
        goBackward(10, power);
        goBackwardsSeconds(1, 0.3);

        // Grab specimen
        viperSlide.clawGrab();
        sleep(250);
        viperSlide.topBarPull();

        sleep(250);
        //viperSlide.waitForExtension();

        // Hang Specimen
        goForward(8, power);
        slideLeft(24*2.5, power);

        viperSlide.topBarRaise();
        turnRight(180, power);

        goBackward(15, power);
        goBackward(1, 0.3);

        hangSpecimen();

        observationZonePark();

//        viperSlide.waitForExtension();
//
//        goBackward(8, power);
//
//        turnRight(90, power);
//        goBackward(24*2, power);
    }
}
